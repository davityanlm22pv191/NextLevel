package com.example.tutorplace.ui.screens.main.presentation

import androidx.lifecycle.viewModelScope
import com.example.tutorplace.data.credentials.CredentialsStorage
import com.example.tutorplace.data.profile.storage.ProfileStorage
import com.example.tutorplace.domain.usecases.FetchInitialDataUseCase
import com.example.tutorplace.navigation.Destinations
import com.example.tutorplace.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainScreenViewModel @Inject constructor(
	private val fetchInitialDataUseCase: FetchInitialDataUseCase,
	private val credentialsStorage: CredentialsStorage,
	private val profileStorage: ProfileStorage,
) : BaseViewModel<MainScreenEvent, MainScreenState, MainScreenEffect>() {

	init {
		checkCredentials()
	}

	override fun initialState(): MainScreenState = MainScreenState()

	override fun onEvent(event: MainScreenEvent) {
		setState(MainScreenReducer.reduce(state.value, event))
	}

	private fun checkCredentials() {
		viewModelScope.launch {
			val isAuthorized = async { credentialsStorage.isAuthorized().firstOrNull() }.await()
			if (isAuthorized == true) {
				onEvent(MainScreenEvent.UserIsAuthorized(Destinations.Home))
				fetchInitialDataUseCase.execute()
				collectProfileShortInfo()
			} else {
				onEvent(MainScreenEvent.UserIsNotAuthorized(Destinations.Authorization))
			}
		}
	}


	private suspend fun collectProfileShortInfo() {
		profileStorage.profileShortInfo.collect { value ->
			value?.let { onEvent(MainScreenEvent.ProfileInfoLoaded(it)) }
		}
	}
}