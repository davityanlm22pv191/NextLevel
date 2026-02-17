package com.example.nextlevel.ui.screens.main.presentation

import androidx.lifecycle.viewModelScope
import com.example.nextlevel.data.credentials.CredentialsStorage
import com.example.nextlevel.data.profile.storage.ProfileStorage
import com.example.nextlevel.domain.usecases.profile.UpdateProfileShortInfoUseCase
import com.example.nextlevel.network.error.ErrorEventBus
import com.example.nextlevel.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainScreenViewModel @Inject constructor(
	private val credentialsStorage: CredentialsStorage,
	private val updateProfileShortInfoUseCase: UpdateProfileShortInfoUseCase,
	private val profileStorage: ProfileStorage,
	val errorEventBus: ErrorEventBus,
) : BaseViewModel<MainScreenEvent, MainScreenState, MainScreenEffect>() {

	init {
		collectAuthorized()
	}

	override fun initialState(): MainScreenState = MainScreenState()

	override fun onEvent(event: MainScreenEvent) {
		setState(MainScreenReducer.reduce(state.value, event))
	}

	private fun collectAuthorized() {
		viewModelScope.launch {
			credentialsStorage.collectAuthorized().collect {isAuthorized ->
				if (isAuthorized) {
					updateProfileShortInfo()
					collectProfileShortInfo()
				}
			}
		}
	}

	private fun collectProfileShortInfo() {
		viewModelScope.launch {
			profileStorage.profileShortInfo.collect { value ->
				value?.let {
					onEvent(MainScreenEvent.ProfileInfoLoaded(it))
				}
			}
		}
	}

	private fun updateProfileShortInfo() {
		viewModelScope.launch {
			updateProfileShortInfoUseCase.execute()
		}
	}
}