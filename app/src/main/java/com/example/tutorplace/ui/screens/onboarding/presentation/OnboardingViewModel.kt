package com.example.tutorplace.ui.screens.onboarding.presentation

import androidx.lifecycle.viewModelScope
import com.example.tutorplace.data.onboarding.model.PlatformAccessDataBody
import com.example.tutorplace.data.onboarding.model.PostNotificationIntervalBody
import com.example.tutorplace.domain.usecases.onboarding.GetOnboardingInfoUseCase
import com.example.tutorplace.domain.usecases.onboarding.PostOnboardingInfoUseCase
import com.example.tutorplace.helpers.FormatHelper
import com.example.tutorplace.ui.base.BaseViewModel
import com.example.tutorplace.ui.screens.onboarding.presentation.OnboardingEvent.NameValidError
import com.example.tutorplace.ui.screens.onboarding.presentation.OnboardingEvent.NextStepClicked
import com.example.tutorplace.ui.screens.onboarding.presentation.OnboardingEvent.OnboardingInfoLoadFail
import com.example.tutorplace.ui.screens.onboarding.presentation.OnboardingEvent.OnboardingInfoLoaded
import com.example.tutorplace.ui.screens.onboarding.presentation.OnboardingEvent.OnboardingInfoLoading
import com.example.tutorplace.ui.screens.onboarding.presentation.OnboardingEvent.PasswordValidError
import com.example.tutorplace.ui.screens.onboarding.presentation.OnboardingEvent.RepeatPasswordValidError
import com.example.tutorplace.ui.screens.onboarding.presentation.OnboardingEvent.SexError
import com.example.tutorplace.ui.screens.onboarding.presentation.OnboardingState.Step
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OnboardingViewModel @Inject constructor(
	private val getOnboardingInfoUseCase: GetOnboardingInfoUseCase,
	private val postOnboardingInfoUseCase: PostOnboardingInfoUseCase,
) : BaseViewModel<OnboardingEvent, OnboardingState, OnboardingEffect>() {

	// THIS IS MOCK LINK FOR IMAGES
	// https://iili.io/Kk4qtK7.png
	// https://iili.io/Kkri8V2.png
	// https://iili.io/KkriSPS.png

	private var navigator: OnboardingNavigator? = null

	init {
		loadGiftProductName()
	}

	fun attachNavigator(navigator: OnboardingNavigator) {
		this.navigator = navigator
	}

	override fun initialState() = OnboardingState(step = Step.CONGRATULATIONS)

	override fun onEvent(event: OnboardingEvent) = when (event) {
		is NextStepClicked -> checkCurrentStateAndNavigateToNextStep()
		else -> setState(OnboardingReducer.reduce(state.value, event))
	}

	private fun checkCurrentStateAndNavigateToNextStep() {
		when (state.value.step) {
			Step.PROVIDE_DETAILS -> processProvideDetailsStep()
			Step.TELL_US_ABOUT_INTERESTS -> processTellUsAboutInterestsStep()
			Step.SPEND_YOUR_TIME_PRODUCTIVELY -> navigator?.exit()
			Step.HELP_YOU_STAY -> processHelpYouStayStep()
			else -> if (!state.value.onboardingInfo.isLoading) {
				setState(OnboardingReducer.reduce(state.value, NextStepClicked))
			}
		}
	}

	private fun processHelpYouStayStep() = processStep(
		isValid = FormatHelper.isValidPhone(state.value.phoneNumber.value) &&
				!state.value.notificationStartTime.isNullOrEmpty() &&
				!state.value.notificationEndTime.isNullOrEmpty(),
		postAction = {
			postOnboardingInfoUseCase.postNotificationInterval(
				PostNotificationIntervalBody(
					phoneNumber = state.value.phoneNumber.value,
					start = state.value.notificationStartTime!!,
					end = state.value.notificationEndTime!!,
				)
			)
		}
	)

	private fun processTellUsAboutInterestsStep() = processStep(
		isValid = state.value.selectedInterestsIds.isNotEmpty(),
		postAction = { postOnboardingInfoUseCase.postInterests(state.value.selectedInterestsIds) }
	)

	private fun processProvideDetailsStep() = processStep(
		isValid = checkProvideDetailsStep(),
		postAction = {
			val sex = state.value.sex ?: return@processStep Result.failure(Throwable())
			postOnboardingInfoUseCase.postPlatformAccessData(
				PlatformAccessDataBody(
					userName = state.value.userName.value,
					password = state.value.password.value,
					sex
				)
			)
		}
	)

	private fun processStep(
		isValid: Boolean,
		postAction: suspend () -> Result<Unit>
	) {
		if (!isValid) return

		viewModelScope.launch {
			setState(OnboardingReducer.reduce(state.value, OnboardingInfoLoading))
			postAction()
				.onSuccess {
					setState(
						OnboardingReducer.reduce(
							state.value,
							OnboardingInfoLoaded(state.value.onboardingInfo.data)
						)
					)
					setState(OnboardingReducer.reduce(state.value, NextStepClicked))
				}
				.onFailure { throwable ->
					setState(
						OnboardingReducer.reduce(
							state.value,
							OnboardingInfoLoadFail(throwable)
						)
					)
				}
		}
	}

	private fun checkProvideDetailsStep(): Boolean = with(state.value) {
		val errorsEvents = buildList {
			if (!FormatHelper.isValidName(userName.value)) add(NameValidError)
			if (!FormatHelper.isValidPassword(password.value)) add(PasswordValidError)
			if (password.value != repeatedPassword.value ||
				!FormatHelper.isValidPassword(repeatedPassword.value)
			) add(RepeatPasswordValidError)
			if (sex == null) add(SexError)
		}
		errorsEvents.forEach { error -> setState(OnboardingReducer.reduce(state.value, error)) }
		return errorsEvents.isEmpty()
	}

	private fun loadGiftProductName() {
		viewModelScope.launch {
			getOnboardingInfoUseCase
				.execute()
				.onSuccess { onboardingInfo ->
					setState(
						OnboardingReducer.reduce(state.value, OnboardingInfoLoaded(onboardingInfo))
					)
				}
				.onFailure { throwable ->
					setState(
						OnboardingReducer.reduce(state.value, OnboardingInfoLoadFail(throwable))
					)
				}
		}
	}
}