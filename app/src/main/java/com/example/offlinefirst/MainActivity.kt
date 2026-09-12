package com.example.offlinefirst

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.offlinefirst.presentation.ui.screens.UserScreen
import com.example.offlinefirst.presentation.viewmodel.UserViewModel
import com.example.offlinefirst.presentation.ui.theme.OfflineFirstTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var userViewModelFactory: UserViewModel.Factory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        
        setContent {
            OfflineFirstTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val userViewModel: UserViewModel = viewModel(
                        factory = userViewModelFactory
                    )
                    UserScreen(
                        viewModel = userViewModel,
                        onNavigateToPreferences = { /* Navigation handled internally */ },
                        onNavigateToContent = { /* Navigation handled internally */ }
                    )
                }
            }
        }
    }

    override fun onStart() {
        super.onStart()
        logAppLifecycleEvent("onStart")
    }

    override fun onResume() {
        super.onResume()
        logAppLifecycleEvent("onResume")
    }

    override fun onPause() {
        super.onPause()
        logAppLifecycleEvent("onPause")
    }

    override fun onStop() {
        super.onStop()
        logAppLifecycleEvent("onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        logAppLifecycleEvent("onDestroy")
    }

    override fun onLowMemory() {
        super.onLowMemory()
        handleLowMemory()
    }

    override fun onTrimMemory(level: Int) {
        super.onTrimMemory(level)
        handleTrimMemory(level)
    }

    private fun logAppLifecycleEvent(event: String) {
        android.util.Log.d(TAG, "Activity lifecycle: $event")
    }

    private fun handleLowMemory() {
        android.util.Log.w(TAG, "System reported low memory")
        System.gc()
    }

    private fun handleTrimMemory(level: Int) {
        val levelDescription = when (level) {
            TRIM_MEMORY_RUNNING_LOW -> "RUNNING_LOW"
            TRIM_MEMORY_RUNNING_CRITICAL -> "RUNNING_CRITICAL"
            TRIM_MEMORY_UI_HIDDEN -> "UI_HIDDEN"
            TRIM_MEMORY_BACKGROUND -> "BACKGROUND"
            TRIM_MEMORY_MODERATE -> "MODERATE"
            TRIM_MEMORY_COMPLETE -> "COMPLETE"
            else -> "UNKNOWN_$level"
        }
        android.util.Log.d(TAG, "Memory trim level: $levelDescription")
    }

    companion object {
        private const val TAG = "MainActivity"
    }
}