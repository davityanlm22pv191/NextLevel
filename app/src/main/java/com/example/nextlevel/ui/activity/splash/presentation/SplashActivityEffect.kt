package com.example.nextlevel.ui.activity.splash.presentation

import com.example.nextlevel.ui.base.BaseEffect

sealed interface SplashActivityEffect : BaseEffect {
	data class CredentialDataLoaded(val userIsAuthorized: Boolean) : SplashActivityEffect
}