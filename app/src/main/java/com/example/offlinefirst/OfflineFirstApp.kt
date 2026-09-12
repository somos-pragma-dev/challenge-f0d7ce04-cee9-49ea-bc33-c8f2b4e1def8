package com.example.offlinefirst

import android.app.Application
import androidx.hilt.work.HiltWorkerFactory
import androidx.work.Configuration
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class OfflineFirstApp : Application(), Configuration.Provider {

    @Inject
    lateinit var workerFactory: HiltWorkerFactory

    override val workManagerConfiguration: Configuration
        get() = Configuration.Builder()
            .setWorkerFactory(workerFactory)
            .setMinimumLoggingLevel(android.util.Log.INFO)
            .build()

    override fun onCreate() {
        super.onCreate()
        initializeApp()
    }

    private fun initializeApp() {
        val appVersion = packageManager.getPackageInfo(packageName, 0).versionName
        android.util.Log.i(TAG, "OfflineFirstApp v$appVersion initialized")
    }

    companion object {
        private const val TAG = "OfflineFirstApp"
    }
}