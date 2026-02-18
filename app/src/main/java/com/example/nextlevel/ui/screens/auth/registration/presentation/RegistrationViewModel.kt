package com.example.nextlevel.ui.screens.auth.registration.presentation

import androidx.lifecycle.viewModelScope
import com.example.nextlevel.domain.usecases.auth.RegisterUseCase
import com.example.nextlevel.ui.base.BaseViewModel
import com.example.nextlevel.ui.screens.auth.registration.presentation.RegistrationEvent.Domain
import com.example.nextlevel.ui.screens.auth.registration.presentation.RegistrationEvent.UI
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegistrationViewModel @Inject constructor(
	private val registerUseCase: RegisterUseCase,
) : BaseViewModel<RegistrationEvent, RegistrationState, RegistrationEffect>() {

	override fun initialState() = RegistrationState()
	override fun onEvent(event: RegistrationEvent) = when (event) {
		is UI -> onUIEvent(event)
		is Domain -> onDomainEvent(event)
	}

	private fun onUIEvent(event: UI) = when (event) {
		is UI.ConfirmPasswordChanged,
		is UI.EmailChanged,
		is UI.NameChanged,
		is UI.PasswordChanged,
		is UI.PhoneChanged,
		is UI.RegisterRequested,
		is UI.TelegramChanged -> setState(RegistrationReducer.reduce(state.value, event))
		is UI.OfferClicked -> sendEffect(RegistrationEffect.NavigateToOffer)
		is UI.TermsClicked -> sendEffect(RegistrationEffect.NavigateToTerms)
		is UI.YandexAuthorizationClicked -> sendEffect(RegistrationEffect.NavigateToYandexAuthorization)
	}

	private fun onDomainEvent(event: Domain) = when (event) {
		Domain.Register -> validateStateAndNavigateToHome()
		Domain.SwitchToFirstStep -> switchToFirstStep()
		Domain.SwitchToSecondStep -> switchToSecondStep()
	}

	private fun switchToFirstStep() {
		setState(RegistrationReducer.reduce(state.value, Domain.SwitchToFirstStep))
	}

	private fun switchToSecondStep() {
		setState(RegistrationReducer.reduce(state.value, Domain.SwitchToSecondStep))
	}

	private fun validateStateAndNavigateToHome() = with(state.value) {
		setState(RegistrationReducer.reduce(state.value, Domain.Register))
		if (firstStep.isNameError) return
		if (firstStep.isPhoneNumberError) return
		if (firstStep.isTelegramError) return
		if (secondStep.isEmailError) return
		if (secondStep.isPasswordError) return
		if (secondStep.isConfirmPasswordError) return
		viewModelScope.launch {
			setState(RegistrationReducer.reduce(state.value, UI.RegisterRequested))
			val isRegisterSuccess = registerUseCase
				.execute(
					firstStep.name,
					firstStep.phoneNumber,
					firstStep.telegram,
					secondStep.email,
					secondStep.password
				)
				.isSuccess
			if (isRegisterSuccess) {
				sendEffect(RegistrationEffect.NavigateToHome)
			}
		}
		return@with
	}
}