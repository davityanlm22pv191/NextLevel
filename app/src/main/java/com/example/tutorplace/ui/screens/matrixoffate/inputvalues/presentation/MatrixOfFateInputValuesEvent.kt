package com.example.tutorplace.ui.screens.matrixoffate.inputvalues.presentation

import com.example.tutorplace.domain.model.Sex
import com.example.tutorplace.ui.base.BaseEvent
import java.time.LocalDate

sealed interface MatrixOfFateInputValuesEvent : BaseEvent {
	data class NameValueChanged(val name: String) : MatrixOfFateInputValuesEvent
	data class SexChosen(val sex: Sex) : MatrixOfFateInputValuesEvent
	data object SexError : MatrixOfFateInputValuesEvent
	data object CalculateButtonClicked : MatrixOfFateInputValuesEvent
	data class BirthDateSelected(val date: LocalDate) : MatrixOfFateInputValuesEvent
}