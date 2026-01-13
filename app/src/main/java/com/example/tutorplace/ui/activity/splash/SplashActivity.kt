package com.example.tutorplace.ui.activity.splash

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.viewModels
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.tutorplace.navigation.Destinations
import com.example.tutorplace.ui.activity.main.MainActivity
import com.example.tutorplace.ui.activity.splash.presentation.SplashActivityEffect
import com.example.tutorplace.ui.activity.splash.presentation.SplashActivityEffect.NavigateToAuthFlow
import com.example.tutorplace.ui.activity.splash.presentation.SplashActivityEffect.NavigateToMain
import com.example.tutorplace.ui.activity.splash.presentation.SplashActivityEvent.SplashAnimationEnded
import com.example.tutorplace.ui.activity.splash.presentation.SplashActivityViewModel
import com.example.tutorplace.ui.screens.main.model.MainScreenParams
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@SuppressLint("CustomSplashScreen")
@AndroidEntryPoint
class SplashActivity : ComponentActivity() {

	private val viewModel by viewModels<SplashActivityViewModel>()

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		val splashScreen = installSplashScreen()
		observeViewModelEffects()
		viewModel.onEvent(SplashAnimationEnded)
		splashScreen.setOnExitAnimationListener { splashScreenView ->
			splashScreenView.view
				.animate()
				.alpha(0f)
				.scaleX(0.9f)
				.scaleY(0.9f)
				.setDuration(500L)
				.withEndAction { viewModel.onEvent(SplashAnimationEnded) }
				.start()
		}
	}

	private fun observeViewModelEffects() {
		lifecycleScope.launch {
			repeatOnLifecycle(Lifecycle.State.STARTED) {
				viewModel.effect.collect { effect ->
					handlingViewModelEffect(effect)
				}
			}
		}
	}

	private fun handlingViewModelEffect(effect: SplashActivityEffect) {
		val startRoute = when (effect) {
			NavigateToAuthFlow -> Destinations.Authorization
			NavigateToMain -> Destinations.MainScreen(MainScreenParams(isShouldShowOnboarding = false))
		}
		val intent = Intent(this, MainActivity::class.java).apply {
			putExtra("start_route", startRoute)
		}
		startActivity(intent)
		finish()
	}
}