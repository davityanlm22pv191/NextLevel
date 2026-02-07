package com.example.tutorplace.ui.screens.yournewscreen.presentation

import com.example.tutorplace.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class YourNewScreenViewModel @Inject constructor() :
	BaseViewModel<YourNewScreenEvent, YourNewScreenState, YourNewScreenEffect>() {

	override fun initialState() = YourNewScreenState

	override fun onEvent(event: YourNewScreenEvent) {
		when (event) {
			else -> {}
		}
	}
}