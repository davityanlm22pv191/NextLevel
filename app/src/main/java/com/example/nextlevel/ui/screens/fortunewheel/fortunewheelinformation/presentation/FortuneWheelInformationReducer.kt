package com.example.nextlevel.ui.screens.fortunewheel.fortunewheelinformation.presentation

import com.example.nextlevel.ui.base.BaseReducer

object FortuneWheelInformationReducer :
	BaseReducer<FortuneWheelInformationState, FortuneWheelInformationEvent> {

	override fun reduce(
		oldState: FortuneWheelInformationState,
		event: FortuneWheelInformationEvent
	): FortuneWheelInformationState {
		return when (event) {
			else -> oldState
		}
	}
}
