package com.example.tutorplace.ui.screens.fortunewheel.fortunewheelinformation.presentation

import com.example.tutorplace.ui.base.BaseEvent

sealed interface FortuneWheelInformationEvent : BaseEvent {
	data object NextClick : FortuneWheelInformationEvent
	data object MoreAboutPromotionClick: FortuneWheelInformationEvent
}
