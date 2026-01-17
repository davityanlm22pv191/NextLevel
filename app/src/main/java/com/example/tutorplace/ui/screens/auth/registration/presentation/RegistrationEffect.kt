package com.example.tutorplace.ui.screens.auth.registration.presentation

import com.example.tutorplace.ui.base.BaseEffect

sealed interface RegistrationEffect : BaseEffect {
	data object NavigateToHome : RegistrationEffect
	data object NavigateToYandexAuthorization : RegistrationEffect
	data object NavigateToOffer : RegistrationEffect
	data object NavigateToTerms : RegistrationEffect
}