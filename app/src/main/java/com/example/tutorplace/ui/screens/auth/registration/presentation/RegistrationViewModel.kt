package com.example.tutorplace.ui.screens.auth.registration.presentation

import androidx.lifecycle.viewModelScope
import com.example.tutorplace.domain.usecases.auth.RegisterUseCase
import com.example.tutorplace.ui.base.BaseViewModel
import com.example.tutorplace.ui.screens.auth.registration.presentation.RegistrationEvent.Domain
import com.example.tutorplace.ui.screens.auth.registration.presentation.RegistrationEvent.UI
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegistrationViewModel @Inject constructor(
	private val registerUseCase: RegisterUseCase,
) : BaseViewModel<RegistrationEvent, RegistrationState, RegistrationEffect>() {

	private var navigator: RegistrationNavigator? = null

	fun attachNavigator(navigator: RegistrationNavigator) {
		this.navigator = navigator
	}

	override fun initialState() = RegistrationState()

	override fun onEvent(event: RegistrationEvent) = when (event) {
		is UI -> setState(RegistrationReducer.reduce(state.value, event))
		is Domain -> Unit
	}

	fun onFirstStepClicked() {
		setState(RegistrationReducer.reduce(state.value, Domain.SwitchToFirstStep))
	}

	fun onSecondStepClicked() {
		setState(RegistrationReducer.reduce(state.value, Domain.SwitchToSecondStep))
	}

	fun onRegisterClicked() = with(state.value) {
		setState(RegistrationReducer.reduce(state.value, Domain.Register))
		if (firstStep.isNameError) return
		if (firstStep.isPhoneNumberError) return
		if (firstStep.isTelegramError) return
		if (secondStep.isEmailError) return
		if (secondStep.isPasswordError) return
		if (secondStep.isConfirmPasswordError) return
		viewModelScope.launch {
			setState(RegistrationReducer.reduce(state.value, UI.RegisterRequested))
			val isRegisterSuccess = registerUseCase.execute(
				firstStep.name,
				firstStep.phoneNumber,
				firstStep.telegram,
				secondStep.email,
				secondStep.password
			)
			if (isRegisterSuccess) {
				navigator?.navigateToHome()
			}
		}
		return@with
	}

	fun onOfferClicked() {
		navigator?.navigateToOffer()
	}

	fun onTermsClicked() {
		navigator?.navigateToTerms()
	}

	fun onYandexClicked() {
		navigator?.navigateToYandexAuthorization()
	}
}