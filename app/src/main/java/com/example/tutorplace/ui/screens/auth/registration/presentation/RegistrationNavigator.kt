package com.example.tutorplace.ui.screens.auth.registration.presentation

import com.example.tutorplace.navigation.Destinations
import com.example.tutorplace.navigation.Navigator
import com.example.tutorplace.navigation.ViewModelNavigator
import com.example.tutorplace.ui.screens.main.model.MainScreenParams

class RegistrationNavigator(
	private val navigator: Navigator
) : ViewModelNavigator(navigator) {

	fun navigateToHome() {
		navigator.navigate(Destinations.MainScreen(MainScreenParams(isShouldShowOnboarding = true)))
	}

	fun navigateToOffer() = Unit
	fun navigateToTerms() = Unit
	fun navigateToYandexAuthorization() = Unit
}