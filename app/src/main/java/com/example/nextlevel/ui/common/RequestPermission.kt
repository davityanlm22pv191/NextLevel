package com.example.nextlevel.ui.common

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat

@Composable
fun RequestPermission(
	permission: String,
	onPermissionResult: (granted: Boolean) -> Unit
) {
	val context = LocalContext.current
	val launcher = rememberLauncherForActivityResult(
		contract = ActivityResultContracts.RequestPermission(),
		onResult = { granted: Boolean -> onPermissionResult(granted) }
	)
	LaunchedEffect(Unit) {
		val hasPermission = ContextCompat.checkSelfPermission(
			context,
			permission
		) == PackageManager.PERMISSION_GRANTED
		if (hasPermission) return@LaunchedEffect
		val requiredApi = minApiForPermission(permission)
		if (Build.VERSION.SDK_INT >= requiredApi) {
			launcher.launch(permission)
		} else {
			onPermissionResult(true)
		}
	}
}

private fun minApiForPermission(permission: String): Int = when (permission) {
	Manifest.permission.POST_NOTIFICATIONS -> Build.VERSION_CODES.TIRAMISU // 33
	else -> 0 // неизвестно или всегда доступно
}