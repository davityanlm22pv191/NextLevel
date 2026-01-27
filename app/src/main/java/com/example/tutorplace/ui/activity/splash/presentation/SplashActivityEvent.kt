package com.example.tutorplace.ui.activity.splash.presentation

import com.example.tutorplace.ui.base.BaseEvent

sealed interface SplashActivityEvent: BaseEvent {
	data object SplashAnimationStarted: SplashActivityEvent
}