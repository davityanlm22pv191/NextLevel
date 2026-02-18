package com.example.nextlevel.ui.screens.onboarding.presentation

import com.example.nextlevel.data.onboarding.model.OnboardingInfo
import com.example.nextlevel.domain.model.DataInfo
import com.example.nextlevel.helpers.FormatHelper
import com.example.nextlevel.ui.base.BaseReducer
import com.example.nextlevel.ui.common.textfield.TextFieldState
import com.example.nextlevel.ui.screens.onboarding.presentation.OnboardingEvent.InterestSelected
import com.example.nextlevel.ui.screens.onboarding.presentation.OnboardingEvent.NameValidError
import com.example.nextlevel.ui.screens.onboarding.presentation.OnboardingEvent.NameValueChanged
import com.example.nextlevel.ui.screens.onboarding.presentation.OnboardingEvent.NextStepClicked
import com.example.nextlevel.ui.screens.onboarding.presentation.OnboardingEvent.NotificationEndTimeSelected
import com.example.nextlevel.ui.screens.onboarding.presentation.OnboardingEvent.NotificationStartTimeSelected
import com.example.nextlevel.ui.screens.onboarding.presentation.OnboardingEvent.OnboardingInfoLoadFail
import com.example.nextlevel.ui.screens.onboarding.presentation.OnboardingEvent.OnboardingInfoLoaded
import com.example.nextlevel.ui.screens.onboarding.presentation.OnboardingEvent.OnboardingInfoLoading
import com.example.nextlevel.ui.screens.onboarding.presentation.OnboardingEvent.PasswordValidError
import com.example.nextlevel.ui.screens.onboarding.presentation.OnboardingEvent.PasswordValueChanged
import com.example.nextlevel.ui.screens.onboarding.presentation.OnboardingEvent.PhoneNumberValueChanged
import com.example.nextlevel.ui.screens.onboarding.presentation.OnboardingEvent.PreviousStepClicked
import com.example.nextlevel.ui.screens.onboarding.presentation.OnboardingEvent.RepeatPasswordValidError
import com.example.nextlevel.ui.screens.onboarding.presentation.OnboardingEvent.RepeatPasswordValueChanged
import com.example.nextlevel.ui.screens.onboarding.presentation.OnboardingEvent.SexChosen
import com.example.nextlevel.ui.screens.onboarding.presentation.OnboardingEvent.SexError
import com.example.nextlevel.ui.screens.onboarding.presentation.OnboardingEvent.SkipButtonClicked
import com.example.nextlevel.ui.screens.onboarding.presentation.OnboardingState.Step

object OnboardingReducer : BaseReducer<OnboardingState, OnboardingEvent> {

	override fun reduce(
		oldState: OnboardingState,
		event: OnboardingEvent
	): OnboardingState = when (event) {
		is NextStepClicked, is SkipButtonClicked -> reduceNextStep(oldState)
		is PreviousStepClicked -> reducePreviousStep(oldState)
		is OnboardingInfoLoading -> oldState.copy(onboardingInfo = DataInfo.Loading)
		is OnboardingInfoLoadFail -> reduceOnboardingInfoLoadFail(oldState)
		is OnboardingInfoLoaded -> reduceOnboardingInfoLoaded(oldState, event.onboardingInfo)
		is NameValidError -> reduceNameValidError(oldState)
		is NameValueChanged -> reduceNameValueChanged(oldState, event)
		is PasswordValidError -> reducePasswordValidError(oldState)
		is PasswordValueChanged -> reducePasswordValueChanged(oldState, event)
		is RepeatPasswordValidError -> reduceRepeatPasswordError(oldState)
		is RepeatPasswordValueChanged -> reduceRepeatPasswordValueChanged(oldState, event)
		is SexChosen -> reduceSexChosen(oldState, event)
		is SexError -> reduceSexError(oldState)
		is InterestSelected -> reduceInterestSelected(oldState, event)
		is PhoneNumberValueChanged -> reducePhoneNumberValueChanged(oldState, event)
		is NotificationEndTimeSelected -> reduceNotificationEndTimeSelected(oldState, event)
		is NotificationStartTimeSelected -> reduceNotificationStartTimeSelected(oldState, event)
	}

	private fun reduceNotificationStartTimeSelected(
		oldState: OnboardingState,
		event: NotificationStartTimeSelected
	): OnboardingState {
		return oldState.copy(notificationStartTime = event.time).let { state ->
			state.copy(
				isMainButtonEnabled = FormatHelper.isValidPhone(oldState.phoneNumber.value) &&
						state.notificationStartTime != null && state.notificationEndTime != null
			)
		}
	}

	private fun reduceNotificationEndTimeSelected(
		oldState: OnboardingState,
		event: NotificationEndTimeSelected
	): OnboardingState {
		return oldState.copy(notificationEndTime = event.time).let { state ->
			state.copy(
				isMainButtonEnabled = FormatHelper.isValidPhone(oldState.phoneNumber.value) &&
						state.notificationStartTime != null && state.notificationEndTime != null
			)
		}
	}

	private fun reducePhoneNumberValueChanged(
		oldState: OnboardingState,
		event: PhoneNumberValueChanged
	): OnboardingState {
		return oldState.copy(
			phoneNumber = TextFieldState(event.phoneNumber, isError = false),
			isMainButtonEnabled = FormatHelper.isValidPhone(event.phoneNumber) &&
					oldState.notificationStartTime != null && oldState.notificationEndTime != null
		)
	}


	private fun reduceInterestSelected(
		oldState: OnboardingState,
		event: InterestSelected
	): OnboardingState {
		val selectedInterestsIds = if (event.interestId in oldState.selectedInterestsIds) {
			oldState.selectedInterestsIds - event.interestId
		} else {
			oldState.selectedInterestsIds + event.interestId
		}
		return oldState.copy(
			selectedInterestsIds = selectedInterestsIds,
			isMainButtonEnabled = selectedInterestsIds.isNotEmpty()
		)
	}

	private fun reduceSexError(oldState: OnboardingState): OnboardingState {
		return oldState.copy(isSexError = true)
	}

	private fun reduceSexChosen(
		oldState: OnboardingState,
		event: SexChosen
	): OnboardingState {
		return oldState.copy(sex = event.sex, isSexError = false)
	}

	private fun reduceRepeatPasswordError(oldState: OnboardingState): OnboardingState {
		return oldState.copy(
			repeatedPassword = TextFieldState(oldState.repeatedPassword.value, isError = true)
		)
	}

	private fun reduceRepeatPasswordValueChanged(
		oldState: OnboardingState,
		event: RepeatPasswordValueChanged
	): OnboardingState {
		return oldState.copy(
			repeatedPassword = TextFieldState(event.repeatPassword, isError = false)
		)
	}

	private fun reduceNameValueChanged(
		oldState: OnboardingState,
		event: NameValueChanged,
	): OnboardingState {
		return oldState.copy(
			userName = TextFieldState(event.name, isError = false)
		)
	}

	private fun reduceNameValidError(oldState: OnboardingState): OnboardingState {
		return oldState.copy(userName = TextFieldState(oldState.userName.value, isError = true))
	}

	private fun reducePasswordValueChanged(
		oldState: OnboardingState,
		event: PasswordValueChanged
	): OnboardingState {
		return oldState.copy(
			password = TextFieldState(event.password, isError = false)
		)
	}

	private fun reducePasswordValidError(oldState: OnboardingState): OnboardingState {
		return oldState.copy(password = TextFieldState(oldState.password.value, isError = true))
	}

	private fun reduceNextStep(oldState: OnboardingState): OnboardingState {
		val nextStep = when (oldState.step) {
			Step.CONGRATULATIONS -> Step.WELCOME
			Step.WELCOME -> Step.PROVIDE_DETAILS
			Step.PROVIDE_DETAILS -> Step.MORE_OPPORTUNITIES
			Step.MORE_OPPORTUNITIES -> Step.KNOWLEDGE_FROM_MASTERS
			Step.KNOWLEDGE_FROM_MASTERS -> Step.TELL_US_ABOUT_INTERESTS
			Step.TELL_US_ABOUT_INTERESTS -> Step.HELP_YOU_STAY
			Step.HELP_YOU_STAY -> Step.SPEND_YOUR_TIME_PRODUCTIVELY
			Step.SPEND_YOUR_TIME_PRODUCTIVELY -> oldState.step // This is last step
		}
		return oldState.copy(
			step = nextStep,
			isMainButtonEnabled = when (nextStep) {
				Step.TELL_US_ABOUT_INTERESTS -> oldState.selectedInterestsIds.isNotEmpty()
				Step.HELP_YOU_STAY -> FormatHelper.isValidPhone(oldState.phoneNumber.value) &&
						oldState.notificationStartTime != null && oldState.notificationEndTime != null
				else -> true
			},
		)
	}

	private fun reducePreviousStep(oldState: OnboardingState): OnboardingState {
		val previousStep = when (oldState.step) {
			Step.CONGRATULATIONS -> oldState.step // This is first step
			Step.WELCOME -> Step.CONGRATULATIONS
			Step.PROVIDE_DETAILS -> Step.WELCOME
			Step.MORE_OPPORTUNITIES -> Step.PROVIDE_DETAILS
			Step.KNOWLEDGE_FROM_MASTERS -> Step.MORE_OPPORTUNITIES
			Step.TELL_US_ABOUT_INTERESTS -> Step.KNOWLEDGE_FROM_MASTERS
			Step.HELP_YOU_STAY -> Step.TELL_US_ABOUT_INTERESTS
			Step.SPEND_YOUR_TIME_PRODUCTIVELY -> Step.HELP_YOU_STAY
		}
		return oldState.copy(
			step = previousStep,
			isMainButtonEnabled = if (previousStep == Step.HELP_YOU_STAY) {
				FormatHelper.isValidPhone(oldState.phoneNumber.value) &&
						oldState.notificationStartTime != null && oldState.notificationEndTime != null
			} else {
				true
			},
		)
	}

	private fun reduceOnboardingInfoLoaded(
		oldState: OnboardingState,
		onboardingInfo: OnboardingInfo
	): OnboardingState = oldState.copy(
		onboardingInfo = DataInfo.Success(onboardingInfo),
		isMainButtonEnabled = true
	)

	private fun reduceOnboardingInfoLoadFail(
		oldState: OnboardingState,
	): OnboardingState = oldState.copy(
		onboardingInfo = DataInfo.Error,
		isMainButtonEnabled = true
	)
}