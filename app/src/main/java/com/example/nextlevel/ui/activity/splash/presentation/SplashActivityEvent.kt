package com.example.nextlevel.ui.activity.splash.presentation

import com.example.nextlevel.ui.base.BaseEvent

sealed interface SplashActivityEvent: BaseEvent {
	data object SplashAnimationStarted: SplashActivityEvent
}