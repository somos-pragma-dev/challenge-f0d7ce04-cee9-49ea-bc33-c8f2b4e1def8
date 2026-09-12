package com.example.offlinefirst.data.sync

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import android.os.Build
import com.example.offlinefirst.util.NetworkMonitor
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NetworkConnectivityManager @Inject constructor(
    @ApplicationContext private val context: Context,
    private val networkMonitor: NetworkMonitor
) {
    private val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    
    private val _isConnected = MutableStateFlow(false)
    val isConnected: StateFlow<Boolean> = _isConnected.asStateFlow()
    
    private val _connectionType = MutableStateFlow(ConnectionType.NONE)
    val connectionType: StateFlow<ConnectionType> = _connectionType.asStateFlow()
    
    private val _isSyncing = MutableStateFlow(false)
    val isSyncing: StateFlow<Boolean> = _isSyncing.asStateFlow()

    private val networkCallback = object : ConnectivityManager.NetworkCallback() {
        override fun onAvailable(network: Network) {
            _isConnected.value = true
            updateConnectionType()
        }

        override fun onLost(network: Network) {
            _isConnected.value = false
            _connectionType.value = ConnectionType.NONE
        }

        override fun onCapabilitiesChanged(
            network: Network,
            networkCapabilities: NetworkCapabilities
        ) {
            updateConnectionType()
        }
    }

    private val networkRequest = NetworkRequest.Builder()
        .addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
        .addCapability(NetworkCapabilities.NET_CAPABILITY_VALIDATED)
        .build()

    fun startMonitoring() {
        connectivityManager.registerNetworkCallback(networkRequest, networkCallback)
        updateCurrentState()
    }

    fun stopMonitoring() {
        try {
            connectivityManager.unregisterNetworkCallback(networkCallback)
        } catch (e: Exception) {
            // El callback ya fue desregistrado
        }
    }

    private fun updateCurrentState() {
        val activeNetwork = connectivityManager.activeNetwork
        val capabilities = connectivityManager.getNetworkCapabilities(activeNetwork)
        
        _isConnected.value = capabilities?.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET) == true
        updateConnectionType()
    }

    private fun updateConnectionType() {
        val activeNetwork = connectivityManager.activeNetwork
        val capabilities = connectivityManager.getNetworkCapabilities(activeNetwork)
        
        _connectionType.value = when {
            capabilities == null -> ConnectionType.NONE
            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> ConnectionType.WIFI
            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> ConnectionType.CELLULAR
            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> ConnectionType.ETHERNET
            else -> ConnectionType.OTHER
        }
    }

    fun observeConnectivity(): Flow<Boolean> = callbackFlow {
        val callback = object : ConnectivityManager.NetworkCallback() {
            override fun onAvailable(network: Network) {
                trySend(true)
            }

            override fun onLost(network: Network) {
                trySend(false)
            }
        }

        val request = NetworkRequest.Builder()
            .addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
            .build()

        connectivityManager.registerNetworkCallback(request, callback)
        
        trySend(isCurrentlyConnected())
        
        awaitClose {
            connectivityManager.unregisterNetworkCallback(callback)
        }
    }

    fun observeConnectionType(): Flow<ConnectionType> = callbackFlow {
        val callback = object : ConnectivityManager.NetworkCallback() {
            override fun onCapabilitiesChanged(
                network: Network,
                networkCapabilities: NetworkCapabilities
            ) {
                val type = getConnectionTypeFromCapabilities(networkCapabilities)
                trySend(type)
            }

            override fun onLost(network: Network) {
                trySend(ConnectionType.NONE)
            }
        }

        val request = NetworkRequest.Builder()
            .addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
            .build()

        connectivityManager.registerNetworkCallback(request, callback)
        
        trySend(getCurrentConnectionType())
        
        awaitClose {
            connectivityManager.unregisterNetworkCallback(callback)
        }
    }

    fun isCurrentlyConnected(): Boolean {
        val activeNetwork = connectivityManager.activeNetwork
        val capabilities = connectivityManager.getNetworkCapabilities(activeNetwork)
        return capabilities?.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET) == true
    }

    fun isCurrentlyConnectedViaWifi(): Boolean {
        val activeNetwork = connectivityManager.activeNetwork
        val capabilities = connectivityManager.getNetworkCapabilities(activeNetwork)
        return capabilities?.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) == true
    }

    fun isCurrentlyConnectedViaCellular(): Boolean {
        val activeNetwork = connectivityManager.activeNetwork
        val capabilities = connectivityManager.getNetworkCapabilities(activeNetwork)
        return capabilities?.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) == true
    }

    private fun getCurrentConnectionType(): ConnectionType {
        val activeNetwork = connectivityManager.activeNetwork
        val capabilities = connectivityManager.getNetworkCapabilities(activeNetwork)
        return getConnectionTypeFromCapabilities(capabilities)
    }

    private fun getConnectionTypeFromCapabilities(capabilities: NetworkCapabilities?): ConnectionType {
        return when {
            capabilities == null -> ConnectionType.NONE
            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> ConnectionType.WIFI
            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> ConnectionType.CELLULAR
            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> ConnectionType.ETHERNET
            else -> ConnectionType.OTHER
        }
    }

    fun setSyncing(syncing: Boolean) {
        _isSyncing.value = syncing
    }

    fun canSync(wifiOnly: Boolean): Boolean {
        if (!isCurrentlyConnected()) return false
        if (wifiOnly && !isCurrentlyConnectedViaWifi()) return false
        return true
    }

    enum class ConnectionType {
        NONE,
        WIFI,
        CELLULAR,
        ETHERNET,
        OTHER
    }
}