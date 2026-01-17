package com.example.tutorplace.ui.screens.auth.authorization.presentation

import androidx.lifecycle.viewModelScope
import com.example.tutorplace.domain.usecases.auth.AuthorizeUseCase
import com.example.tutorplace.helpers.FormatHelper
import com.example.tutorplace.ui.base.BaseViewModel
import com.example.tutorplace.ui.screens.auth.authorization.presentation.AuthorizationEffect.NavigateToHome
import com.example.tutorplace.ui.screens.auth.authorization.presentation.AuthorizationEffect.NavigateToRegistration
import com.example.tutorplace.ui.screens.auth.authorization.presentation.AuthorizationEffect.NavigateToRestorePassword
import com.example.tutorplace.ui.screens.auth.authorization.presentation.AuthorizationEffect.NavigateToSupport
import com.example.tutorplace.ui.screens.auth.authorization.presentation.AuthorizationEffect.NavigateToYandexAuthorization
import com.example.tutorplace.ui.screens.auth.authorization.presentation.AuthorizationEvent.CheckEnteredValues
import com.example.tutorplace.ui.screens.auth.authorization.presentation.AuthorizationEvent.EmailChanged
import com.example.tutorplace.ui.screens.auth.authorization.presentation.AuthorizationEvent.EnterClicked
import com.example.tutorplace.ui.screens.auth.authorization.presentation.AuthorizationEvent.EnterToProfileRequested
import com.example.tutorplace.ui.screens.auth.authorization.presentation.AuthorizationEvent.PasswordChanged
import com.example.tutorplace.ui.screens.auth.authorization.presentation.AuthorizationEvent.RegistrationClicked
import com.example.tutorplace.ui.screens.auth.authorization.presentation.AuthorizationEvent.RestorePasswordClicked
import com.example.tutorplace.ui.screens.auth.authorization.presentation.AuthorizationEvent.SupportClicked
import com.example.tutorplace.ui.screens.auth.authorization.presentation.AuthorizationEvent.YandexAuthorizationClicked
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthorizationViewModel @Inject constructor(
	private val authorizeUseCase: AuthorizeUseCase,
) : BaseViewModel<AuthorizationEvent, AuthorizationState, AuthorizationEffect>() {

	override fun initialState() = AuthorizationState()
	override fun onEvent(event: AuthorizationEvent) {
		when (event) {
			is CheckEnteredValues,
			is EmailChanged,
			is EnterToProfileRequested,
			is PasswordChanged -> setState(AuthorizationReducer.reduce(state.value, event))
			is EnterClicked -> validateState()
			is RegistrationClicked -> sendEffect(NavigateToRegistration)
			is RestorePasswordClicked -> sendEffect(NavigateToRestorePassword)
			is SupportClicked -> sendEffect(NavigateToSupport)
			is YandexAuthorizationClicked -> sendEffect(NavigateToYandexAuthorization)
		}
	}

	private fun validateState() {
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
				sendEffect(NavigateToHome)
			}
		}
	}
}