package com.example.tutorplace.ui.activity.splash

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.tutorplace.ui.activity.main.MainActivity
import dagger.hilt.android.AndroidEntryPoint

@SuppressLint("CustomSplashScreen")
@AndroidEntryPoint
class SplashActivity : ComponentActivity() {

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		val splashScreen = installSplashScreen()
		splashScreen.setOnExitAnimationListener { splashScreenView ->
			splashScreenView.view
				.animate()
				.alpha(0f)
				.scaleX(0.9f)
				.scaleY(0.9f)
				.setDuration(500L)
				.withEndAction { startActivity(Intent(this, MainActivity::class.java)) }
				.start()
		}
	}
}