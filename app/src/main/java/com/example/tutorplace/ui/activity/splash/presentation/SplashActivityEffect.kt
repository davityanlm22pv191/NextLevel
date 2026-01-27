package com.example.tutorplace.ui.activity.splash.presentation

import com.example.tutorplace.ui.base.BaseEffect

sealed interface SplashActivityEffect: BaseEffect {
	data object NavigateToAuthFlow: SplashActivityEffect
	data object NavigateToHome: SplashActivityEffect
}