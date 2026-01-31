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
import com.example.tutorplace.ui.activity.main.MainActivity
import com.example.tutorplace.ui.activity.splash.presentation.SplashActivityEffect.CredentialDataLoaded
import com.example.tutorplace.ui.activity.splash.presentation.SplashActivityEvent.SplashAnimationStarted
import com.example.tutorplace.ui.activity.splash.presentation.SplashActivityViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@SuppressLint("CustomSplashScreen")
@AndroidEntryPoint
class SplashActivity : ComponentActivity() {

	private val viewModel by viewModels<SplashActivityViewModel>()

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		val splashScreen = installSplashScreen()
		collectViewModelEffects()
		viewModel.onEvent(SplashAnimationStarted)
		splashScreen.setOnExitAnimationListener { splashScreenView ->
			splashScreenView.view
				.animate()
				.alpha(0f)
				.scaleX(0.9f)
				.scaleY(0.9f)
				.start()
		}
	}

	private fun collectViewModelEffects() {
		lifecycleScope.launch {
			repeatOnLifecycle(Lifecycle.State.STARTED) {
				viewModel.effect.collect { effect ->
					when (effect) {
						is CredentialDataLoaded -> navigateToNextScreen(effect.userIsAuthorized)
					}
				}
			}
		}
	}

	private fun navigateToNextScreen(userIsAuthorized: Boolean) {
		val intent = Intent(this, MainActivity::class.java).apply {
			putExtra("userIsAuthorized", userIsAuthorized)
		}
		startActivity(intent)
		finish()
	}
}