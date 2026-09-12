package com.example.offlinefirst.presentation.ui.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Cloud
import androidx.compose.material.icons.filled.CloudOff
import androidx.compose.material.icons.filled.Error
import androidx.compose.material.icons.filled.Sync
import androidx.compose.material.icons.filled.SyncDisabled
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.offlinefirst.domain.model.SyncStatus

@Composable
fun SyncStatusIndicator(
    syncStatus: SyncStatus,
    pendingOperationsCount: Int = 0,
    lastSyncTime: Long? = null,
    isNetworkAvailable: Boolean = true,
    modifier: Modifier = Modifier
) {
    val statusColor by animateColorAsState(
        targetValue = when {
            !isNetworkAvailable -> Color(0xFF9E9E9E)
            syncStatus.isSyncing() -> Color(0xFF2196F3)
            syncStatus.hasConflict() -> Color(0xFFFF9800)
            syncStatus.hasError() -> Color(0xFFF44336)
            syncStatus.isSynced() -> Color(0xFF4CAF50)
            else -> Color(0xFFFFC107)
        },
        animationSpec = tween(300),
        label = "statusColor"
    )

    val infiniteTransition = rememberInfiniteTransition(label = "syncRotation")
    val rotation by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween(1500, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ),
        label = "rotation"
    )

    val isAnimating = syncStatus.isSyncing()

    Surface(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        shape = RoundedCornerShape(12.dp),
        color = MaterialTheme.colorScheme.surfaceVariant,
        tonalElevation = 2.dp
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.weight(1f)
            ) {
                SyncStatusIcon(
                    syncStatus = syncStatus,
                    isNetworkAvailable = isNetworkAvailable,
                    isAnimating = isAnimating,
                    rotation = if (isAnimating) rotation else 0f,
                    tint = statusColor
                )

                Spacer(modifier = Modifier.width(12.dp))

                Column {
                    Text(
                        text = getStatusTitle(syncStatus, isNetworkAvailable),
                        style = MaterialTheme.typography.titleSmall,
                        fontWeight = FontWeight.SemiBold,
                        color = MaterialTheme.colorScheme.onSurface
                    )

                    Spacer(modifier = Modifier.height(2.dp))

                    Text(
                        text = getStatusDescription(syncStatus, pendingOperationsCount, lastSyncTime, isNetworkAvailable),
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }

            if (pendingOperationsCount > 0 && isNetworkAvailable) {
                PendingBadge(count = pendingOperationsCount)
            }
        }
    }
}

@Composable
private fun SyncStatusIcon(
    syncStatus: SyncStatus,
    isNetworkAvailable: Boolean,
    isAnimating: Boolean,
    rotation: Float,
    tint: Color
) {
    val icon = when {
        !isNetworkAvailable -> Icons.Default.CloudOff
        syncStatus.isSynced() -> Icons.Default.CheckCircle
        syncStatus.hasConflict() -> Icons.Default.Error
        syncStatus.hasError() -> Icons.Default.SyncDisabled
        syncStatus.isSyncing() -> Icons.Default.Sync
        else -> Icons.Default.Cloud
    }

    Box(
        modifier = Modifier
            .size(48.dp)
            .background(tint.copy(alpha = 0.1f), CircleShape),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            imageVector = icon,
            contentDescription = "Sync status",
            tint = tint,
            modifier = Modifier
                .size(24.dp)
                .rotate(if (isAnimating) rotation else 0f)
        )
    }
}

@Composable
private fun PendingBadge(count: Int) {
    Box(
        modifier = Modifier
            .background(
                color = Color(0xFFFFC107),
                shape = RoundedCornerShape(12.dp)
            )
            .padding(horizontal = 10.dp, vertical = 4.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "$count",
            color = Color.Black,
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

private fun getStatusTitle(syncStatus: SyncStatus, isNetworkAvailable: Boolean): String {
    return when {
        !isNetworkAvailable -> "Sin conexión"
        syncStatus.isSynced() -> "Sincronizado"
        syncStatus.isSyncing() -> "Sincronizando..."
        syncStatus.hasConflict() -> "Conflicto detectado"
        syncStatus.hasError() -> "Error de sincronización"
        syncStatus.isPending() -> "Pendiente de sincronizar"
        else -> "Estado desconocido"
    }
}

private fun getStatusDescription(
    syncStatus: SyncStatus,
    pendingCount: Int,
    lastSyncTime: Long?,
    isNetworkAvailable: Boolean
): String {
    return when {
        !isNetworkAvailable -> "Los datos se guardan localmente. Se sincronizará cuando haya conexión."
        syncStatus.isSynced() && lastSyncTime != null ->
            "Última sincronización: ${formatLastSyncTime(lastSyncTime)}"
        syncStatus.isSynced() -> "Todos los datos están actualizados"
        syncStatus.isSyncing() -> "Transfiriendo datos con el servidor..."
        syncStatus.hasConflict() -> "Se detectaron conflictos. Se requiere resolución manual."
        syncStatus.hasError() -> "La sincronización falló. Se reintentará automáticamente."
        syncStatus.isPending() -> "$pendingCount operación(es) esperando para sincronizar"
        else -> "Verificando estado de sincronización..."
    }
}

private fun formatLastSyncTime(timestamp: Long): String {
    val now = System.currentTimeMillis()
    val diff = now - timestamp

    return when {
        diff < 60_000 -> "Hace un momento"
        diff < 3_600_000 -> "Hace ${diff / 60_000} min"
        diff < 86_400_000 -> "Hace ${diff / 3_600_000} horas"
        else -> "Hace ${diff / 86_400_000} días"
    }
}

@Composable
fun CompactSyncIndicator(
    syncStatus: SyncStatus,
    isNetworkAvailable: Boolean,
    modifier: Modifier = Modifier
) {
    val statusColor by animateColorAsState(
        targetValue = when {
            !isNetworkAvailable -> Color(0xFF9E9E9E)
            syncStatus.isSynced() -> Color(0xFF4CAF50)
            syncStatus.hasConflict() -> Color(0xFFFF9800)
            syncStatus.hasError() -> Color(0xFFF44336)
            syncStatus.isSyncing() -> Color(0xFF2196F3)
            else -> Color(0xFFFFC107)
        },
        animationSpec = tween(300),
        label = "compactStatusColor"
    )

    val infiniteTransition = rememberInfiniteTransition(label = "compactSyncRotation")
    val rotation by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween(1500, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ),
        label = "compactRotation"
    )

    val icon = when {
        !isNetworkAvailable -> Icons.Default.CloudOff
        syncStatus.isSynced() -> Icons.Default.CheckCircle
        syncStatus.hasConflict() -> Icons.Default.Error
        syncStatus.hasError() -> Icons.Default.SyncDisabled
        syncStatus.isSyncing() -> Icons.Default.Sync
        else -> Icons.Default.Cloud
    }

    Icon(
        imageVector = icon,
        contentDescription = "Estado de sincronización",
        tint = statusColor,
        modifier = modifier
            .size(20.dp)
            .rotate(if (syncStatus.isSyncing()) rotation else 0f)
    )
}