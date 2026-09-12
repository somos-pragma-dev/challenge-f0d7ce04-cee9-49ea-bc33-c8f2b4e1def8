package com.example.offlinefirst.presentation.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.offlinefirst.domain.model.User
import com.example.offlinefirst.domain.model.SyncStatus
import com.example.offlinefirst.presentation.viewmodel.UserViewModel
import com.example.offlinefirst.presentation.ui.components.SyncStatusIndicator

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserScreen(
    viewModel: UserViewModel,
    onNavigateToPreferences: () -> Unit,
    onNavigateToContent: () -> Unit,
    modifier: Modifier = Modifier
) {
    val uiState by viewModel.uiState.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Users") },
                actions = {
                    IconButton(onClick = { viewModel.syncData() }) {
                        Icon(Icons.Default.Sync, contentDescription = "Sync")
                    }
                    IconButton(onClick = onNavigateToPreferences) {
                        Icon(Icons.Default.Settings, contentDescription = "Preferences")
                    }
                    IconButton(onClick = onNavigateToContent) {
                        Icon(Icons.Default.Folder, contentDescription = "Content")
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            SyncStatusIndicator(
                syncStatus = uiState.syncStatus,
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = uiState.searchQuery,
                onValueChange = { viewModel.onSearchQueryChanged(it) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                placeholder = { Text("Search users...") },
                leadingIcon = { Icon(Icons.Default.Search, contentDescription = null) },
                singleLine = true
            )

            if (uiState.isLoading) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            } else if (uiState.error != null) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(
                            text = uiState.error ?: "Unknown error",
                            color = MaterialTheme.colorScheme.error
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Button(onClick = { viewModel.loadUsers() }) {
                            Text("Retry")
                        }
                    }
                }
            } else {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(16.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(uiState.users) { user ->
                        UserCard(
                            user = user,
                            onClick = { viewModel.selectUser(user) }
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun UserCard(
    user: User,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        onClick = onClick,
        modifier = modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Default.Person,
                contentDescription = null,
                modifier = Modifier.size(40.dp),
                tint = MaterialTheme.colorScheme.primary
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = user.displayName ?: user.username,
                    style = MaterialTheme.typography.titleMedium
                )
                Text(
                    text = user.email,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
            SyncStatusBadge(syncStatus = user.syncStatus)
        }
    }
}

@Composable
private fun SyncStatusBadge(syncStatus: SyncStatus) {
    val (icon, color) = when (syncStatus) {
        SyncStatus.SYNCED -> Icons.Default.CloudDone to MaterialTheme.colorScheme.primary
        SyncStatus.PENDING -> Icons.Default.CloudUpload to MaterialTheme.colorScheme.tertiary
        SyncStatus.CONFLICT -> Icons.Default.Warning to MaterialTheme.colorScheme.error
        SyncStatus.ERROR -> Icons.Default.Error to MaterialTheme.colorScheme.error
        SyncStatus.SYNCING -> Icons.Default.Sync to MaterialTheme.colorScheme.secondary
    }
    Icon(
        imageVector = icon,
        contentDescription = syncStatus.name,
        tint = color,
        modifier = Modifier.size(20.dp)
    )
}