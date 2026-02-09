package com.example.tutorplace.ui.screens.matrixoffate.inputvalues.presentation

import com.example.tutorplace.ui.base.BaseReducer
import com.example.tutorplace.ui.screens.matrixoffate.inputvalues.presentation.MatrixOfFateInputValuesEvent.BirthDateSelected
import com.example.tutorplace.ui.screens.matrixoffate.inputvalues.presentation.MatrixOfFateInputValuesEvent.NameValueChanged
import com.example.tutorplace.ui.screens.matrixoffate.inputvalues.presentation.MatrixOfFateInputValuesEvent.SexChosen
import com.example.tutorplace.ui.screens.matrixoffate.inputvalues.presentation.MatrixOfFateInputValuesEvent.SexError

object MatrixOfFateInputValuesReducer :
	BaseReducer<MatrixOfFateInputValuesState, MatrixOfFateInputValuesEvent> {

	override fun reduce(
		oldState: MatrixOfFateInputValuesState,
		event: MatrixOfFateInputValuesEvent
	): MatrixOfFateInputValuesState {
		return when (event) {
			is BirthDateSelected -> TODO()
			is NameValueChanged -> TODO()
			is SexChosen -> reduceSexChosen(event, oldState)
			is SexError -> oldState.copy(isSexError = true)
			else -> oldState
		}
	}

	private fun reduceSexChosen(
		event: SexChosen,
		oldState: MatrixOfFateInputValuesState
	): MatrixOfFateInputValuesState {
		return oldState.copy(sex = event.sex, isSexError = false)
	}
}