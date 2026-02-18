package com.example.nextlevel.ui.screens.auth.authorization.presentation

import androidx.lifecycle.viewModelScope
import com.example.nextlevel.data.credentials.CredentialsStorage
import com.example.nextlevel.domain.usecases.auth.AuthorizeUseCase
import com.example.nextlevel.helpers.FormatHelper
import com.example.nextlevel.ui.base.BaseViewModel
import com.example.nextlevel.ui.screens.auth.authorization.presentation.AuthorizationEffect.NavigateToHome
import com.example.nextlevel.ui.screens.auth.authorization.presentation.AuthorizationEffect.NavigateToRegistration
import com.example.nextlevel.ui.screens.auth.authorization.presentation.AuthorizationEffect.NavigateToRestorePassword
import com.example.nextlevel.ui.screens.auth.authorization.presentation.AuthorizationEffect.NavigateToSupport
import com.example.nextlevel.ui.screens.auth.authorization.presentation.AuthorizationEffect.NavigateToYandexAuthorization
import com.example.nextlevel.ui.screens.auth.authorization.presentation.AuthorizationEvent.CheckEnteredValues
import com.example.nextlevel.ui.screens.auth.authorization.presentation.AuthorizationEvent.EmailChanged
import com.example.nextlevel.ui.screens.auth.authorization.presentation.AuthorizationEvent.EnterClicked
import com.example.nextlevel.ui.screens.auth.authorization.presentation.AuthorizationEvent.EnterToProfileRequested
import com.example.nextlevel.ui.screens.auth.authorization.presentation.AuthorizationEvent.PasswordChanged
import com.example.nextlevel.ui.screens.auth.authorization.presentation.AuthorizationEvent.RegistrationClicked
import com.example.nextlevel.ui.screens.auth.authorization.presentation.AuthorizationEvent.RestorePasswordClicked
import com.example.nextlevel.ui.screens.auth.authorization.presentation.AuthorizationEvent.SupportClicked
import com.example.nextlevel.ui.screens.auth.authorization.presentation.AuthorizationEvent.YandexAuthorizationClicked
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthorizationViewModel @Inject constructor(
	private val credentialsStorage: CredentialsStorage,
	private val authorizeUseCase: AuthorizeUseCase,
) : BaseViewModel<AuthorizationEvent, AuthorizationState, AuthorizationEffect>() {

	init {
		collectCredentials()
	}

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
		authorize()
	}

	private fun authorize() {
		viewModelScope.launch {
			setState(AuthorizationReducer.reduce(state.value, EnterToProfileRequested))
			authorizeUseCase
				.execute(email = state.value.email, password = state.value.password)
			// TODO Добавить сюда обработку ошибки
		}
	}

	private fun collectCredentials() {
		viewModelScope.launch {
			credentialsStorage.isAuthorized().collect { isAuthorized ->
				if (isAuthorized) {
					sendEffect(NavigateToHome)
				}
			}
		}
	}
}