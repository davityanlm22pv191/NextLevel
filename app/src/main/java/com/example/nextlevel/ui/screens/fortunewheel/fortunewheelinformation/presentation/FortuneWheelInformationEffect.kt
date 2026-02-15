package com.example.nextlevel.ui.screens.fortunewheel.fortunewheelinformation.presentation

import com.example.nextlevel.ui.base.BaseEffect

sealed interface FortuneWheelInformationEffect : BaseEffect {
	data object Dismiss : FortuneWheelInformationEffect
}
