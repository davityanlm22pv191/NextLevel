package com.example.tutorplace.ui.screens.fortunewheel.fortunewheel.presentation

import com.example.tutorplace.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FortuneWheelViewModel @Inject constructor() :
	BaseViewModel<FortuneWheelEvent, FortuneWheelState, FortuneWheelEffect>() {

	private var navigator: FortuneWheelNavigator? = null

	fun attachNavigator(navigator: FortuneWheelNavigator) {
		this.navigator = navigator
	}

	override fun initialState(): FortuneWheelState = FortuneWheelState()

	override fun onEvent(event: FortuneWheelEvent) = Unit
}
