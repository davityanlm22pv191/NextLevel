package com.example.tutorplace.ui.screens.fortunewheel.fortunewheelinformation.presentation

import com.example.tutorplace.ui.base.BaseViewModel
import com.example.tutorplace.ui.screens.fortunewheel.fortunewheelinformation.presentation.FortuneWheelInformationEvent.BackClick
import com.example.tutorplace.ui.screens.fortunewheel.fortunewheelinformation.presentation.FortuneWheelInformationEvent.MoreAboutPromotionClick
import com.example.tutorplace.ui.screens.fortunewheel.fortunewheelinformation.presentation.FortuneWheelInformationEvent.NextClick
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FortuneWheelInformationViewModel @Inject constructor() :
	BaseViewModel<FortuneWheelInformationEvent, FortuneWheelInformationState, FortuneWheelInformationEffect>() {

	private var navigator: FortuneWheelInformationNavigator? = null

	fun attachNavigator(navigator: FortuneWheelInformationNavigator) {
		this.navigator = navigator
	}

	override fun initialState(): FortuneWheelInformationState = FortuneWheelInformationState

	override fun onEvent(event: FortuneWheelInformationEvent) {
		when (event) {
			BackClick -> navigator?.exit()
			MoreAboutPromotionClick -> navigator?.exit()
			NextClick -> navigator?.exit()
		}
	}
}
