package com.example.tutorplace.ui.screens.main.presentation

import androidx.lifecycle.viewModelScope
import com.example.tutorplace.domain.usecases.FetchInitialDataUseCase
import com.example.tutorplace.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainScreenViewModel @Inject constructor(
	private val fetchInitialDataUseCase: FetchInitialDataUseCase
) : BaseViewModel<MainScreenEvent, MainScreenState, MainScreenEffect>() {

	init {
		viewModelScope.launch {
			fetchInitialDataUseCase.execute()
		}
	}

	override fun initialState(): MainScreenState = MainScreenState()
	override fun onEvent(event: MainScreenEvent) = Unit
}