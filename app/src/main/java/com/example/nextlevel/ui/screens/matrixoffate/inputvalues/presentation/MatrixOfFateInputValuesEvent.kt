package com.example.nextlevel.ui.screens.matrixoffate.inputvalues.presentation

import com.example.nextlevel.domain.model.Sex
import com.example.nextlevel.ui.base.BaseEvent
import java.time.LocalDate

sealed interface MatrixOfFateInputValuesEvent : BaseEvent {
	data class NameValueChanged(val name: String) : MatrixOfFateInputValuesEvent
	data class SexChosen(val sex: Sex) : MatrixOfFateInputValuesEvent
	data object SexError : MatrixOfFateInputValuesEvent
	data object CalculateButtonClicked : MatrixOfFateInputValuesEvent
	data class BirthDateSelected(val date: LocalDate) : MatrixOfFateInputValuesEvent
}