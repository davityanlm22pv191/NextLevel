package com.example.nextlevel.ui.screens.matrixoffate.inputvalues.presentation

import com.example.nextlevel.ui.base.BaseViewModel
import com.example.nextlevel.ui.screens.matrixoffate.inputvalues.presentation.MatrixOfFateInputValuesEvent.CalculateButtonClicked
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MatrixOfFateInputValuesViewModel @Inject constructor() :
	BaseViewModel<MatrixOfFateInputValuesEvent, MatrixOfFateInputValuesState, MatrixOfFateInputValuesEffect>() {

	override fun initialState() = MatrixOfFateInputValuesState()

	override fun onEvent(event: MatrixOfFateInputValuesEvent) {
		when (event) {
			is CalculateButtonClicked -> TODO()
			else -> setState(MatrixOfFateInputValuesReducer.reduce(state.value, event))
		}
	}
}