package com.example.nextlevel.ui.screens.fortunewheel.fortunewheelinformation.presentation

import com.example.nextlevel.ui.base.BaseEvent

sealed interface FortuneWheelInformationEvent : BaseEvent {
	data object NextClick : FortuneWheelInformationEvent
	data object MoreAboutPromotionClick: FortuneWheelInformationEvent
}
