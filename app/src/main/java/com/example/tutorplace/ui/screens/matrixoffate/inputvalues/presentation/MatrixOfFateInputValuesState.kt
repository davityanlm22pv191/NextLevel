package com.example.tutorplace.ui.screens.matrixoffate.inputvalues.presentation

import com.example.tutorplace.domain.model.Sex
import com.example.tutorplace.ui.base.BaseState
import com.example.tutorplace.ui.common.textfield.TextFieldState
import java.time.LocalDate

data class MatrixOfFateInputValuesState(
	val userName: TextFieldState = TextFieldState(),
	val birthDate: LocalDate? = null,
	val sex: Sex? = null,
	val isSexError: Boolean = false,
	val isBirthDateError: Boolean = false,
	val isCalculateButtonEnabled: Boolean = false,
	val isLoading: Boolean = false
) : BaseState