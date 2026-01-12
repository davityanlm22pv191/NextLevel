package com.example.tutorplace.ui.screens.main

import android.Manifest
import android.os.Build
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.example.tutorplace.navigation.Destinations
import com.example.tutorplace.navigation.Navigator
import com.example.tutorplace.ui.common.RequestPermission
import com.example.tutorplace.ui.screens.main.model.MainScreenParams
import com.example.tutorplace.ui.screens.main.presentation.MainScreenViewModel

@Composable
fun MainScreen(
	navigator: Navigator,
	params: MainScreenParams,
) {
	val viewModel = hiltViewModel<MainScreenViewModel>()
	OpenOnboardingIfNeeded(navigator, params.isShouldShowOnboarding)
	MainContent()
}

@Composable
private fun MainContent() {
	Scaffold(
		contentWindowInsets = WindowInsets(0, 0, 0, 0),
		bottomBar = {}
	) { paddingValues ->
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
			RequestPermission(Manifest.permission.POST_NOTIFICATIONS) {}
		}
	}
}

@Composable
private fun OpenOnboardingIfNeeded(
	navigator: Navigator,
	isShouldShowOnboarding: Boolean
) {
	var onboardingNavigated by rememberSaveable { mutableStateOf(false) }
	LaunchedEffect(isShouldShowOnboarding) {
		if (isShouldShowOnboarding && !onboardingNavigated) {
			onboardingNavigated = true
			navigator.navigate(Destinations.Onboarding)
		}
	}
}

@Preview
@Composable
private fun MainScreenPreview() {
	MainContent()
}