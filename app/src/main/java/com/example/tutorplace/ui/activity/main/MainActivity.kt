package com.example.tutorplace.ui.activity.main

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.SideEffect
import androidx.core.view.WindowInsetsControllerCompat
import androidx.navigation3.runtime.NavKey
import com.example.tutorplace.helpers.isLight
import com.example.tutorplace.navigation.AppNavigationGraph
import com.example.tutorplace.navigation.Destinations
import com.example.tutorplace.ui.theme.ScreenColor
import com.example.tutorplace.ui.theme.TutorPlaceTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		val startDestination = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
			intent.getParcelableExtra("start_route", NavKey::class.java) ?: Destinations.Auth
		} else {
			@Suppress("DEPRECATION") // remove when SDK will more 33 (SDK 33)
			intent.getParcelableExtra("start_route") ?: Destinations.Auth
		}
		enableEdgeToEdge()
		setContent {
			TutorPlaceTheme {
				val isLightScreenColor = ScreenColor.isLight()
				SideEffect {
					val windowInsetsController =
						WindowInsetsControllerCompat(window, window.decorView)
					windowInsetsController.isAppearanceLightStatusBars = isLightScreenColor
					windowInsetsController.isAppearanceLightNavigationBars = isLightScreenColor
				}

				AppNavigationGraph(startDestination)
			}
		}
	}
}