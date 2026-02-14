package com.example.nextlevel.ui.screens.matrixoffate.inputvalues.presentation

import com.example.nextlevel.domain.model.Sex
import com.example.nextlevel.ui.base.BaseState
import com.example.nextlevel.ui.common.textfield.TextFieldState
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