package com.example.offlinefirst.data.sync

import android.content.Context
import androidx.hilt.work.HiltWorker
import androidx.work.Constraints
import androidx.work.CoroutineWorker
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.NetworkType
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.WorkerParameters
import com.example.offlinefirst.data.repository.ProductRepositoryImpl
import com.example.offlinefirst.data.repository.PurchaseRepositoryImpl
import com.example.offlinefirst.data.repository.UserPreferencesRepositoryImpl
import com.example.offlinefirst.util.Resource
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.withContext
import java.util.concurrent.TimeUnit

@HiltWorker
class SyncWorker @AssistedInject constructor(
    @Assisted context: Context,
    @Assisted workerParams: WorkerParameters,
    private val productRepository: ProductRepositoryImpl,
    private val purchaseRepository: PurchaseRepositoryImpl,
    private val userPreferencesRepository: UserPreferencesRepositoryImpl,
    private val networkConnectivityManager: NetworkConnectivityManager,
    private val syncManager: SyncManager
) : CoroutineWorker(context, workerParams) {

    override suspend fun doWork(): Result = withContext(Dispatchers.IO) {
        try {
            if (!networkConnectivityManager.isCurrentlyConnected()) {
                return@withContext Result.retry()
            }

            networkConnectivityManager.setSyncing(true)
            
            val wifiOnly = try {
                val prefs = userPreferencesRepository.getUserPreferences().first()
                prefs.data?.syncOnWifiOnly == true
            } catch (e: Exception) {
                false
            }

            if (wifiOnly && !networkConnectivityManager.isCurrentlyConnectedViaWifi()) {
                return@withContext Result.retry()
            }

            var syncResult = Result.success()
            
            coroutineScope {
                val syncJobs = listOf(
                    async { syncProducts() },
                    async { syncPurchases() }
                )
                
                val results = syncJobs.awaitAll()
                if (results.any { it == Result.retry() }) {
                    syncResult = Result.retry()
                }
            }
            
            userPreferencesRepository.updateLastSyncTime(System.currentTimeMillis())
            
            networkConnectivityManager.setSyncing(false)
            
            syncResult
        } catch (e: Exception) {
            networkConnectivityManager.setSyncing(false)
            
            if (runAttemptCount < MAX_RETRY_COUNT) {
                Result.retry()
            } else {
                Result.failure()
            }
        }
    }

    private suspend fun syncProducts(): Result {
        return try {
            val pendingProducts = productRepository.getPendingProducts().first()
            if (pendingProducts.isNotEmpty()) {
                val syncResult = productRepository.syncPendingProducts()
                if (syncResult is Resource.Error) {
                    return Result.retry()
                }
            }
            
            val refreshResult = productRepository.getProducts(forceRefresh = true).first()
            when (refreshResult) {
                is Resource.Success -> Result.success()
                is Resource.Error -> Result.retry()
                is Resource.Loading -> Result.success()
            }
        } catch (e: Exception) {
            Result.retry()
        }
    }

    private suspend fun syncPurchases(): Result {
        return try {
            val pendingPurchases = purchaseRepository.getPendingPurchases().first()
            if (pendingPurchases.isNotEmpty()) {
                val syncResult = purchaseRepository.syncPendingPurchases()
                if (syncResult is Resource.Error) {
                    return Result.retry()
                }
            }
            
            val refreshResult = purchaseRepository.getPurchaseHistory().first()
            when (refreshResult) {
                is Resource.Success -> Result.success()
                is Resource.Error -> Result.retry()
                is Resource.Loading -> Result.success()
            }
        } catch (e: Exception) {
            Result.retry()
        }
    }

    companion object {
        const val WORK_NAME = "sync_worker""
        private const val MAX_RETRY_COUNT = 3
        private const val SYNC_INTERVAL_MINUTES = 15L

        fun schedule(context: Context, wifiOnly: Boolean = false) {
            val constraints = Constraints.Builder()
                .setRequiredNetworkType(
                    if (wifiOnly) NetworkType.UNMETERED else NetworkType.CONNECTED
                )
                .setRequiresBatteryNotLow(true)
                .build()

            val syncRequest = PeriodicWorkRequestBuilder<SyncWorker>(
                SYNC_INTERVAL_MINUTES, TimeUnit.MINUTES
            )
                .setConstraints(constraints)
                .build()

            WorkManager.getInstance(context).enqueueUniquePeriodicWork(
                WORK_NAME,
                ExistingPeriodicWorkPolicy.UPDATE,
                syncRequest
            )
        }

        fun cancel(context: Context) {
            WorkManager.getInstance(context).cancelUniqueWork(WORK_NAME)
        }

        fun runOnce(context: Context) {
            val constraints = Constraints.Builder()
                .setRequiredNetworkType(NetworkType.CONNECTED)
                .build()

            val syncRequest = androidx.work.OneTimeWorkRequestBuilder<SyncWorker>()
                .setConstraints(constraints)
                .build()

            WorkManager.getInstance(context).enqueue(syncRequest)
        }
    }
}