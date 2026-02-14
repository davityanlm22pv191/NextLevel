package com.example.nextlevel.ui.screens.onboarding.presentation

import com.example.nextlevel.data.onboarding.model.OnboardingInfo
import com.example.nextlevel.domain.model.Sex
import com.example.nextlevel.ui.base.BaseEvent

sealed interface OnboardingEvent : BaseEvent {

	data object NextStepClicked : OnboardingEvent
	data object PreviousStepClicked : OnboardingEvent
	data object SkipButtonClicked : OnboardingEvent

	data class NameValueChanged(val name: String) : OnboardingEvent
	data object NameValidError : OnboardingEvent
	data class PasswordValueChanged(val password: String) : OnboardingEvent
	data object PasswordValidError : OnboardingEvent
	data class RepeatPasswordValueChanged(val repeatPassword: String) : OnboardingEvent
	data object RepeatPasswordValidError : OnboardingEvent
	data class SexChosen(val sex: Sex) : OnboardingEvent
	data object SexError : OnboardingEvent
	data class InterestSelected(val interestId: Int) : OnboardingEvent
	data class PhoneNumberValueChanged(val phoneNumber: String) : OnboardingEvent
	data class NotificationStartTimeSelected(val time: String) : OnboardingEvent
	data class NotificationEndTimeSelected(val time: String) : OnboardingEvent

	data object OnboardingInfoLoading : OnboardingEvent
	data class OnboardingInfoLoaded(val onboardingInfo: OnboardingInfo) : OnboardingEvent
	data class OnboardingInfoLoadFail(val throwable: Throwable) : OnboardingEvent
}