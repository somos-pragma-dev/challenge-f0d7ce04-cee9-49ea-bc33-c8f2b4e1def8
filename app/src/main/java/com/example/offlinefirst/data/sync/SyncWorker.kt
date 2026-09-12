package com.example.offlinefirst.data.sync

import android.content.Context
import android.util.Log
import androidx.hilt.work.HiltWorker
import androidx.work.BackoffPolicy
import androidx.work.Constraints
import androidx.work.CoroutineWorker
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.NetworkType
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.WorkerParameters
import com.example.offlinefirst.domain.model.SyncStatus
import com.example.offlinefirst.domain.repository.ContentRepository
import com.example.offlinefirst.domain.repository.PreferencesRepository
import com.example.offlinefirst.domain.repository.SyncRepository
import com.example.offlinefirst.domain.repository.UserRepository
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.withContext
import java.util.concurrent.TimeUnit

@HiltWorker
class SyncWorker @AssistedInject constructor(
    @Assisted private val context: Context,
    @Assisted workerParams: WorkerParameters,
    private val userRepository: UserRepository,
    private val preferencesRepository: PreferencesRepository,
    private val contentRepository: ContentRepository,
    private val syncRepository: SyncRepository,
    private val syncManager: SyncManager,
    private val networkMonitor: NetworkMonitor
) : CoroutineWorker(context, workerParams) {

    companion object {
        private const val TAG = "SyncWorker"
        const val WORK_NAME = "periodic_sync_work"
        private const val SYNC_INTERVAL_MINUTES = 5L
        private const val FLEX_INTERVAL_MINUTES = 2L
        private const val MAX_RETRY_ATTEMPTS = 3
    }

    override suspend fun doWork(): Result = withContext(Dispatchers.IO) {
        Log.d(TAG, "Iniciando trabajo de sincronización...")

        try {
            if (!networkMonitor.isNetworkAvailable()) {
                Log.w(TAG, "No hay conexión de red. Sincronización diferida.")
                return@withContext Result.retry()
            }

            val pendingUsers = userRepository.getPendingUsers().first()
            val pendingPreferences = preferencesRepository.getPendingPreferences().first()
            val pendingContents = contentRepository.getPendingContents().first()

            val totalPending = pendingUsers.size + pendingPreferences.size + pendingContents.size
            Log.d(TAG, "Operaciones pendientes: $totalPending")

            if (totalPending == 0) {
                Log.d(TAG, "No hay datos pendientes de sincronización")
                syncRepository.updateSyncStatus(SyncStatus.SYNCED)
                return@withContext Result.success()
            }

            syncRepository.updateSyncStatus(SyncStatus.SYNCING)

            var successCount = 0
            var failureCount = 0

            for (user in pendingUsers) {
                try {
                    val syncResult = syncManager.syncUser(user)
                    if (syncResult.isSuccess) {
                        userRepository.markAsSynced(user.id)
                        successCount++
                    } else {
                        userRepository.markAsError(user.id)
                        failureCount++
                    }
                } catch (e: Exception) {
                    Log.e(TAG, "Error sincronizando usuario ${user.id}", e)
                    userRepository.markAsError(user.id)
                    failureCount++
                }
            }

            for (preference in pendingPreferences) {
                try {
                    val syncResult = syncManager.syncPreference(preference)
                    if (syncResult.isSuccess) {
                        preferencesRepository.markAsSynced(preference.id)
                        successCount++
                    } else {
                        preferencesRepository.markAsError(preference.id)
                        failureCount++
                    }
                } catch (e: Exception) {
                    Log.e(TAG, "Error sincronizando preferencia ${preference.id}", e)
                    preferencesRepository.markAsError(preference.id)
                    failureCount++
                }
            }

            for (content in pendingContents) {
                try {
                    val syncResult = syncManager.syncContent(content)
                    if (syncResult.isSuccess) {
                        contentRepository.markAsSynced(content.id)
                        successCount++
                    } else {
                        contentRepository.markAsError(content.id)
                        failureCount++
                    }
                } catch (e: Exception) {
                    Log.e(TAG, "Error sincronizando contenido ${content.id}", e)
                    contentRepository.markAsError(content.id)
                    failureCount++
                }
            }

            val remainingPending = userRepository.getPendingUsers().first().size +
                    preferencesRepository.getPendingPreferences().first().size +
                    contentRepository.getPendingContents().first().size

            syncRepository.recordSyncOperation(
                successCount = successCount,
                failureCount = failureCount,
                remainingPending = remainingPending
            )

            val finalStatus = when {
                failureCount > 0 && remainingPending > 0 -> SyncStatus.ERROR
                remainingPending > 0 -> SyncStatus.PENDING
                else -> SyncStatus.SYNCED
            }
            syncRepository.updateSyncStatus(finalStatus)

            Log.d(TAG, "Sincronización completada. Éxitos: $successCount, Fallos: $failureCount, Pendientes: $remainingPending")

            if (failureCount > 0 && remainingPending > 0) {
                return@withContext Result.retry()
            }

            Result.success()
        } catch (e: Exception) {
            Log.e(TAG, "Error crítico en sincronización", e)
            syncRepository.updateSyncStatus(SyncStatus.ERROR)

            if (runAttemptCount < MAX_RETRY_ATTEMPTS) {
                Result.retry()
            } else {
                Log.e(TAG, "Máximo de reintentos alcanzado")
                Result.failure()
            }
        }
    }

    class Scheduler(private val context: Context) {
        fun schedulePeriodicSync() {
            val constraints = Constraints.Builder()
                .setRequiredNetworkType(NetworkType.CONNECTED)
                .setRequiresBatteryNotLow(true)
                .build()

            val syncRequest = PeriodicWorkRequestBuilder<SyncWorker>(
                SYNC_INTERVAL_MINUTES, TimeUnit.MINUTES,
                FLEX_INTERVAL_MINUTES, TimeUnit.MINUTES
            )
                .setConstraints(constraints)
                .setBackoffCriteria(
                    BackoffPolicy.EXPONENTIAL,
                    30, TimeUnit.SECONDS
                )
                .addTag(WORK_NAME)
                .build()

            WorkManager.getInstance(context).enqueueUniquePeriodicWork(
                WORK_NAME,
                ExistingPeriodicWorkPolicy.KEEP,
                syncRequest
            )

            Log.d(TAG, "Sincronización periódica programada cada $SYNC_INTERVAL_MINUTES minutos")
        }

        fun cancelPeriodicSync() {
            WorkManager.getInstance(context).cancelUniqueWork(WORK_NAME)
            Log.d(TAG, "Sincronización periódica cancelada")
        }

        fun triggerImmediateSync() {
            val constraints = Constraints.Builder()
                .setRequiredNetworkType(NetworkType.CONNECTED)
                .build()

            val syncRequest = androidx.work.OneTimeWorkRequestBuilder<SyncWorker>()
                .setConstraints(constraints)
                .build()

            WorkManager.getInstance(context).enqueue(syncRequest)
            Log.d(TAG, "Sincronización inmediata iniciada")
        }
    }
}