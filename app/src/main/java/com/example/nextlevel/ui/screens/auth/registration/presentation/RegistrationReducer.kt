package com.example.nextlevel.ui.screens.auth.registration.presentation

import com.example.nextlevel.helpers.FormatHelper
import com.example.nextlevel.ui.base.BaseReducer
import com.example.nextlevel.ui.screens.auth.registration.presentation.RegistrationEvent.Domain.SwitchToFirstStep
import com.example.nextlevel.ui.screens.auth.registration.presentation.RegistrationEvent.Domain.Register
import com.example.nextlevel.ui.screens.auth.registration.presentation.RegistrationEvent.Domain.SwitchToSecondStep
import com.example.nextlevel.ui.screens.auth.registration.presentation.RegistrationEvent.UI.ConfirmPasswordChanged
import com.example.nextlevel.ui.screens.auth.registration.presentation.RegistrationEvent.UI.EmailChanged
import com.example.nextlevel.ui.screens.auth.registration.presentation.RegistrationEvent.UI.NameChanged
import com.example.nextlevel.ui.screens.auth.registration.presentation.RegistrationEvent.UI.PasswordChanged
import com.example.nextlevel.ui.screens.auth.registration.presentation.RegistrationEvent.UI.PhoneChanged
import com.example.nextlevel.ui.screens.auth.registration.presentation.RegistrationEvent.UI.RegisterRequested
import com.example.nextlevel.ui.screens.auth.registration.presentation.RegistrationEvent.UI.TelegramChanged

object RegistrationReducer : BaseReducer<RegistrationState, RegistrationEvent> {
	override fun reduce(
		oldState: RegistrationState,
		event: RegistrationEvent
	): RegistrationState = when (event) {
		is RegistrationEvent.UI -> reduceUiEvent(oldState, event)
		is RegistrationEvent.Domain -> reduceDomainEvent(oldState, event)
	}

	private fun reduceUiEvent(
		oldState: RegistrationState,
		event: RegistrationEvent.UI
	): RegistrationState = when (event) {
		is NameChanged -> reduceNameChanged(oldState, event)
		is PhoneChanged -> reducePhoneChanged(oldState, event)
		is TelegramChanged -> reduceTelegramChanged(oldState, event)
		is EmailChanged -> reduceEmailChanged(oldState, event)
		is PasswordChanged -> reducePasswordChanged(oldState, event)
		is ConfirmPasswordChanged -> reduceConfirmPasswordChanged(oldState, event)
		is RegisterRequested -> oldState.copy(isLoading = true)
		else -> oldState
	}

	private fun reduceDomainEvent(
		oldState: RegistrationState,
		event: RegistrationEvent.Domain
	): RegistrationState = when (event) {
		is SwitchToFirstStep -> oldState.copy(currentStep = RegistrationState.RegistrationStep.FirstStep::class)
		is SwitchToSecondStep -> reduceOnSecondStep(oldState)
		is Register -> reduceOnRegister(oldState)
	}

	private fun reduceNameChanged(
		oldState: RegistrationState,
		event: NameChanged
	): RegistrationState {
		return oldState.copy(
			firstStep = oldState.firstStep.copy(
				name = event.enteredName,
				isNameError = false
			)
		)
	}

	private fun reducePhoneChanged(
		oldState: RegistrationState,
		event: PhoneChanged
	): RegistrationState {
		return oldState.copy(
			firstStep = oldState.firstStep.copy(
				phoneNumber = event.enteredPhone,
				isPhoneNumberError = false
			)
		)
	}

	private fun reduceTelegramChanged(
		oldState: RegistrationState,
		event: TelegramChanged
	): RegistrationState {
		return oldState.copy(
			firstStep = oldState.firstStep.copy(
				telegram = event.enteredTelegram,
				isTelegramError = false
			)
		)
	}

	private fun reduceEmailChanged(
		oldState: RegistrationState,
		event: EmailChanged
	): RegistrationState {
		return oldState.copy(
			secondStep = oldState.secondStep.copy(
				email = event.enteredEmail,
				isEmailError = false
			)
		)
	}

	private fun reducePasswordChanged(
		oldState: RegistrationState,
		event: PasswordChanged
	): RegistrationState {
		return oldState.copy(
			secondStep = oldState.secondStep.copy(
				password = event.enteredPassword,
				isPasswordError = false
			)
		)
	}

	private fun reduceConfirmPasswordChanged(
		oldState: RegistrationState,
		event: ConfirmPasswordChanged
	): RegistrationState {
		return oldState.copy(
			secondStep = oldState.secondStep.copy(
				confirmPassword = event.enteredConfirmPassword,
				isConfirmPasswordError = false
			)
		)
	}

	private fun reduceOnSecondStep(oldState: RegistrationState): RegistrationState {
		val isNameError = FormatHelper.isValidName(oldState.firstStep.name).not()
		val isPhoneNumberError = FormatHelper.isValidPhone(oldState.firstStep.phoneNumber).not()
		val isTelegramError = FormatHelper.isValidTelegram(oldState.firstStep.telegram).not()
		return if (isNameError || isPhoneNumberError || isTelegramError) {
			oldState.copy(
				firstStep = oldState.firstStep.copy(
					isNameError = isNameError,
					isPhoneNumberError = isPhoneNumberError,
					isTelegramError = isTelegramError
				)
			)
		} else {
			oldState.copy(currentStep = RegistrationState.RegistrationStep.SecondStep::class)
		}
	}

	private fun reduceOnRegister(oldState: RegistrationState): RegistrationState {
		val isEmailError = FormatHelper.isValidEmail(oldState.secondStep.email).not()
		val isPasswordError = FormatHelper.isValidPassword(oldState.secondStep.password).not()
		val isConfirmPasswordError = FormatHelper.isValidPassword(oldState.secondStep.password)
			.not() || oldState.secondStep.password != oldState.secondStep.confirmPassword
		return oldState.copy(
			secondStep = oldState.secondStep.copy(
				isEmailError = isEmailError,
				isPasswordError = isPasswordError,
				isConfirmPasswordError = isConfirmPasswordError
			)
		)
	}
}