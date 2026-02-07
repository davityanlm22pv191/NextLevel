package com.example.tutorplace.ui.screens.matrixoffate.detailed.presentation

import com.example.tutorplace.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MatrixOfFateDetailedViewModel @Inject constructor() :
	BaseViewModel<MatrixOfFateDetailedEvent, MatrixOfFateDetailedState, MatrixOfFateDetailedEffect>() {

	override fun initialState() = MatrixOfFateDetailedState

	override fun onEvent(event: MatrixOfFateDetailedEvent) {
		when (event) {
			else -> {}
		}
	}
}