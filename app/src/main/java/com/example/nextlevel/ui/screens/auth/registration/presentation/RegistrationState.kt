package com.example.nextlevel.ui.screens.auth.registration.presentation

import com.example.nextlevel.ui.base.BaseState
import com.example.nextlevel.ui.screens.auth.registration.presentation.RegistrationState.RegistrationStep.FirstStep
import com.example.nextlevel.ui.screens.auth.registration.presentation.RegistrationState.RegistrationStep.SecondStep
import kotlin.reflect.KClass

data class RegistrationState(
	val isLoading: Boolean = false,
	val throwable: Throwable? = null,
	val firstStep: FirstStep = FirstStep(),
	val secondStep: SecondStep = SecondStep(),
	val currentStep: KClass<out RegistrationStep> = FirstStep::class
) : BaseState {

	sealed interface RegistrationStep {
		data class FirstStep(
			val name: String = "",
			val isNameError: Boolean = false,
			val phoneNumber: String = "",
			val isPhoneNumberError: Boolean = false,
			val telegram: String = "",
			val isTelegramError: Boolean = false,
		) : RegistrationStep

		data class SecondStep(
			val email: String = "",
			val isEmailError: Boolean = false,
			val password: String = "",
			val isPasswordError: Boolean = false,
			val confirmPassword: String = "",
			val isConfirmPasswordError: Boolean = false,
		) : RegistrationStep
	}
}