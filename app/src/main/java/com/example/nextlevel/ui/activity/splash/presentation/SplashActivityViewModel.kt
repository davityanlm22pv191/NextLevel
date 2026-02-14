package com.example.nextlevel.ui.activity.splash.presentation

import androidx.lifecycle.viewModelScope
import com.example.nextlevel.data.credentials.CredentialsStorage
import com.example.nextlevel.ui.activity.splash.presentation.SplashActivityEvent.SplashAnimationStarted
import com.example.nextlevel.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashActivityViewModel @Inject constructor(
	private val credentialsStorage: CredentialsStorage
) : BaseViewModel<SplashActivityEvent, SplashActivityState, SplashActivityEffect>() {

	override fun initialState() = SplashActivityState

	override fun onEvent(event: SplashActivityEvent) = when (event) {
		is SplashAnimationStarted -> getCredentialsData()
	}

	private fun getCredentialsData() {
		viewModelScope.launch {
			val isAuthorized = credentialsStorage
				.isAuthorized()
				.firstOrNull()
				?: false

			sendEffect(SplashActivityEffect.CredentialDataLoaded(isAuthorized))
		}
	}
}