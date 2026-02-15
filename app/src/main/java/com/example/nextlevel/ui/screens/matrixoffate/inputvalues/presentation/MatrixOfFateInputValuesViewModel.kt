package com.example.nextlevel.ui.screens.matrixoffate.inputvalues.presentation

import androidx.lifecycle.viewModelScope
import com.example.nextlevel.data.profile.storage.ProfileStorage
import com.example.nextlevel.ui.base.BaseViewModel
import com.example.nextlevel.ui.screens.matrixoffate.inputvalues.presentation.MatrixOfFateInputValuesEvent.CalculateButtonClicked
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MatrixOfFateInputValuesViewModel @Inject constructor(
	private val profileStorage: ProfileStorage,
) : BaseViewModel<MatrixOfFateInputValuesEvent, MatrixOfFateInputValuesState, MatrixOfFateInputValuesEffect>() {

	init {
		loadUserName()
	}

	override fun initialState() = MatrixOfFateInputValuesState()

	override fun onEvent(event: MatrixOfFateInputValuesEvent) {
		when (event) {
			is CalculateButtonClicked -> {}
			else -> setState(MatrixOfFateInputValuesReducer.reduce(state.value, event))
		}
	}

	private fun loadUserName() {
		viewModelScope.launch {
			val profile = profileStorage.profileShortInfo.value ?: return@launch
			onEvent(MatrixOfFateInputValuesEvent.NameValueChanged(profile.userName))
		}
	}
}