package com.example.tutorplace.ui.screens.fortunewheel.fortunewheelinformation.presentation

import com.example.tutorplace.ui.base.BaseEffect

sealed interface FortuneWheelInformationEffect : BaseEffect {
	data object Dismiss : FortuneWheelInformationEffect
}
