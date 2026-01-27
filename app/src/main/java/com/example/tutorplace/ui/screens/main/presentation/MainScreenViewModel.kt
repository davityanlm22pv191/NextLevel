package com.example.tutorplace.ui.screens.main.presentation

import androidx.lifecycle.viewModelScope
import com.example.tutorplace.data.profile.storage.ProfileStorage
import com.example.tutorplace.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainScreenViewModel @Inject constructor(
	private val profileStorage: ProfileStorage,
) : BaseViewModel<MainScreenEvent, MainScreenState, MainScreenEffect>() {

	init {
		collectProfileShortInfo()
	}

	override fun initialState(): MainScreenState = MainScreenState()

	override fun onEvent(event: MainScreenEvent) {
		setState(MainScreenReducer.reduce(state.value, event))
	}

	private fun collectProfileShortInfo() {
		viewModelScope.launch {
			profileStorage.profileShortInfo.collect { value ->
				value?.let { onEvent(MainScreenEvent.ProfileInfoLoaded(it)) }
			}
		}
	}
}