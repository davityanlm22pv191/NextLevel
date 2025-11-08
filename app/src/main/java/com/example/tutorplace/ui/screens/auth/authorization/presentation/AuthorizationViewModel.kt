package com.example.tutorplace.ui.screens.auth.authorization.presentation

import androidx.lifecycle.viewModelScope
import com.example.tutorplace.domain.usecases.auth.AuthorizeUseCase
import com.example.tutorplace.helpers.FormatHelper
import com.example.tutorplace.navigation.ViewModelNavigator
import com.example.tutorplace.ui.base.BaseViewModel
import com.example.tutorplace.ui.screens.auth.authorization.presentation.AuthorizationEvent.CheckEnteredValues
import com.example.tutorplace.ui.screens.auth.authorization.presentation.AuthorizationEvent.EnterToProfileRequested
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthorizationViewModel @Inject constructor(
	private val authorizeUseCase: AuthorizeUseCase,
) : BaseViewModel<AuthorizationEvent, AuthorizationState, AuthorizationEffect>() {

	private var navigator: AuthorizationNavigator? = null

	fun attachNavigator(navigator: AuthorizationNavigator) {
		this.navigator = navigator
	}

	override fun initialState() = AuthorizationState()

	override fun onEvent(event: AuthorizationEvent) {
		setState(AuthorizationReducer.reduce(state.value, event))
	}

	fun onRestoreClicked() {
		navigator?.navigateToRestorePassword()
	}

	fun onRegistrationClicked() {
		navigator?.navigateToRegistration()
	}

	fun onSupportClicked() {
		navigator?.navigateToSupport()
	}

	fun onEnterClicked() {
		setState(AuthorizationReducer.reduce(state.value, CheckEnteredValues))
		if (!FormatHelper.isValidEmail(state.value.email)) return
		if (!FormatHelper.isValidPassword(state.value.password)) return
		setState(AuthorizationReducer.reduce(state.value, EnterToProfileRequested))
		viewModelScope.launch {
			val isAuthorizedSuccess = authorizeUseCase.execute(
				email = state.value.email,
				password = state.value.password
			)
			if (isAuthorizedSuccess) {
				navigator?.navigateToHome()
			}
		}
	}



	fun onYandexClicked() {
		navigator?.navigateToYandexAuthorization()
	}
}