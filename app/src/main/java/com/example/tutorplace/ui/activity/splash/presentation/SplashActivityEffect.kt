package com.example.tutorplace.ui.activity.splash.presentation

import com.example.tutorplace.ui.base.BaseEffect

sealed interface SplashActivityEffect : BaseEffect {
	data class CredentialDataLoaded(val userIsAuthorized: Boolean) : SplashActivityEffect
}