package com.example.tutorplace.ui.activity.splash.presentation

import androidx.lifecycle.viewModelScope
import com.example.tutorplace.data.credentials.CredentialsStorage
import com.example.tutorplace.ui.activity.splash.presentation.SplashActivityEvent.SplashAnimationStarted
import com.example.tutorplace.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashActivityViewModel @Inject constructor(
	private val credentialsStorage: CredentialsStorage
) : BaseViewModel<SplashActivityEvent, SplashActivityState, SplashActivityEffect>() {

	override fun initialState() = SplashActivityState

	override fun onEvent(event: SplashActivityEvent) = when (event) {
		is SplashAnimationStarted -> resolveNextScreen()
	}

	private fun resolveNextScreen() {
		viewModelScope.launch {
			val isAuthorized = async { credentialsStorage.isAuthorized().firstOrNull() }.await()
			if (isAuthorized == true) {
				sendEffect(SplashActivityEffect.NavigateToHome)
			} else {
				sendEffect(SplashActivityEffect.NavigateToAuthFlow)
			}
		}
	}
}