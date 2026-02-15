package com.example.nextlevel.ui.activity.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.nextlevel.ui.screens.main.MainScreen
import com.example.nextlevel.ui.theme.NextLevelTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		val userIsAuthorized = intent.getBooleanExtra("userIsAuthorized", false)
		enableEdgeToEdge()
		setContent {
			NextLevelTheme {
				MainScreen(userIsAuthorized)
			}
		}
	}
}