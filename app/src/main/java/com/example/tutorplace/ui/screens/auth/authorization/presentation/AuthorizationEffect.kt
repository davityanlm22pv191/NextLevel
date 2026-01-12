package com.example.tutorplace.ui.screens.auth.authorization.presentation

import com.example.tutorplace.ui.base.BaseEffect

sealed interface AuthorizationEffect : BaseEffect {
	data object NavigateToHome : AuthorizationEffect
	data object NavigateToRestorePassword : AuthorizationEffect
	data object NavigateToRegistration : AuthorizationEffect
	data object NavigateToSupport : AuthorizationEffect
	data object NavigateToYandexAuthorization : AuthorizationEffect
}