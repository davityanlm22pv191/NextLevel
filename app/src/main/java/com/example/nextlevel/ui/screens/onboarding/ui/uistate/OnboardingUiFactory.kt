package com.example.nextlevel.ui.screens.onboarding.ui.uistate

import androidx.compose.ui.unit.dp
import com.example.nextlevel.R
import com.example.nextlevel.ui.common.header.HeaderLogoType.Image
import com.example.nextlevel.ui.common.header.HeaderLogoType.Text
import com.example.nextlevel.ui.screens.onboarding.presentation.OnboardingEvent.InterestSelected
import com.example.nextlevel.ui.screens.onboarding.presentation.OnboardingEvent.NameValueChanged
import com.example.nextlevel.ui.screens.onboarding.presentation.OnboardingEvent.NotificationEndTimeSelected
import com.example.nextlevel.ui.screens.onboarding.presentation.OnboardingEvent.NotificationStartTimeSelected
import com.example.nextlevel.ui.screens.onboarding.presentation.OnboardingEvent.PasswordValueChanged
import com.example.nextlevel.ui.screens.onboarding.presentation.OnboardingEvent.PhoneNumberValueChanged
import com.example.nextlevel.ui.screens.onboarding.presentation.OnboardingEvent.RepeatPasswordValueChanged
import com.example.nextlevel.ui.screens.onboarding.presentation.OnboardingEvent.SexChosen
import com.example.nextlevel.ui.screens.onboarding.presentation.OnboardingState
import com.example.nextlevel.ui.screens.onboarding.presentation.OnboardingState.Step.CONGRATULATIONS
import com.example.nextlevel.ui.screens.onboarding.presentation.OnboardingState.Step.HELP_YOU_STAY
import com.example.nextlevel.ui.screens.onboarding.presentation.OnboardingState.Step.KNOWLEDGE_FROM_MASTERS
import com.example.nextlevel.ui.screens.onboarding.presentation.OnboardingState.Step.MORE_OPPORTUNITIES
import com.example.nextlevel.ui.screens.onboarding.presentation.OnboardingState.Step.PROVIDE_DETAILS
import com.example.nextlevel.ui.screens.onboarding.presentation.OnboardingState.Step.SPEND_YOUR_TIME_PRODUCTIVELY
import com.example.nextlevel.ui.screens.onboarding.presentation.OnboardingState.Step.TELL_US_ABOUT_INTERESTS
import com.example.nextlevel.ui.screens.onboarding.presentation.OnboardingState.Step.WELCOME
import com.example.nextlevel.ui.screens.onboarding.presentation.OnboardingViewModel
import com.example.nextlevel.ui.screens.onboarding.ui.OnboardingCongratulations
import com.example.nextlevel.ui.screens.onboarding.ui.OnboardingHelpYouStay
import com.example.nextlevel.ui.screens.onboarding.ui.OnboardingKnowledgeFromMasters
import com.example.nextlevel.ui.screens.onboarding.ui.OnboardingMoreOpportunities
import com.example.nextlevel.ui.screens.onboarding.ui.OnboardingProvideDetails
import com.example.nextlevel.ui.screens.onboarding.ui.OnboardingSpendYourTimeProductively
import com.example.nextlevel.ui.screens.onboarding.ui.OnboardingTellUsAboutInterests
import com.example.nextlevel.ui.screens.onboarding.ui.OnboardingWelcome

object OnboardingUiFactory {
	fun create(
		state: OnboardingState,
		viewModel: OnboardingViewModel
	): OnboardingUiState = when (state.step) {
		CONGRATULATIONS -> congratulations(state)
		WELCOME -> welcome(state)
		PROVIDE_DETAILS -> provideDetails(state, viewModel)
		MORE_OPPORTUNITIES -> moreOpportunities(state)
		KNOWLEDGE_FROM_MASTERS -> knowledgeFromMasters(state)
		TELL_US_ABOUT_INTERESTS -> tellUsAboutInterests(state, viewModel)
		HELP_YOU_STAY -> helpYouStay(state, viewModel)
		SPEND_YOUR_TIME_PRODUCTIVELY -> spendYourTimeProductively(state)
	}

	private fun congratulations(state: OnboardingState) = OnboardingUiState(
		header = Image(R.drawable.ic_letter_n),
		title = R.string.onboarding_congratulations_welcome_to_next_level,
		description = R.string.onboarding_congratulations_description,
		contentSeparatorHeightDp = 12.dp,
		mainButtonTitle = R.string.onboarding_next_step,
		isBackButtonVisible = false,
		isSkipButtonVisible = false,
		content = { OnboardingCongratulations(state, this) }
	)

	private fun welcome(state: OnboardingState) = OnboardingUiState(
		header = Image(R.drawable.ic_letter_n),
		title = R.string.onboarding_congratulations_welcome_to_next_level,
		description = R.string.onboarding_welcome_description,
		contentSeparatorHeightDp = 40.dp,
		mainButtonTitle = R.string.onboarding_next_step,
		isBackButtonVisible = false,
		isSkipButtonVisible = false,
		content = { OnboardingWelcome(state, this) }
	)

	private fun provideDetails(
		state: OnboardingState,
		viewModel: OnboardingViewModel
	) = OnboardingUiState(
		header = Text(R.string.onboarding_provide_details_logo),
		title = R.string.onboarding_provide_details_title,
		description = null,
		contentSeparatorHeightDp = 16.dp,
		mainButtonTitle = R.string.onboarding_next_step,
		isBackButtonVisible = false,
		isSkipButtonVisible = false,
		content = {
			OnboardingProvideDetails(
				state = state,
				columnScope = this,
				onUserNameChanged = { viewModel.onEvent(NameValueChanged(it)) },
				onPasswordChanged = { viewModel.onEvent(PasswordValueChanged(it)) },
				onRepeatedPasswordChanged = { viewModel.onEvent(RepeatPasswordValueChanged(it)) },
				onSexChosen = { viewModel.onEvent(SexChosen(it)) }
			)
		}
	)

	private fun moreOpportunities(state: OnboardingState) = OnboardingUiState(
		header = Text(R.string.onboarding_more_opportunities_logo),
		title = R.string.onboarding_more_opportunities_title,
		description = R.string.onboarding_more_opportunities_description,
		contentSeparatorHeightDp = 40.dp,
		mainButtonTitle = R.string.onboarding_next_step,
		isBackButtonVisible = true,
		isSkipButtonVisible = false,
		content = { OnboardingMoreOpportunities(state, this) }
	)

	private fun knowledgeFromMasters(state: OnboardingState) = OnboardingUiState(
		header = Text(R.string.onboarding_knowledge_from_masters_logo),
		title = R.string.onboarding_knowledge_from_masters_title,
		description = R.string.onboarding_knowledge_from_masters_description,
		contentSeparatorHeightDp = 40.dp,
		mainButtonTitle = R.string.onboarding_next_step,
		isBackButtonVisible = true,
		isSkipButtonVisible = false,
		content = { OnboardingKnowledgeFromMasters(state, this) }
	)

	private fun tellUsAboutInterests(
		state: OnboardingState,
		viewModel: OnboardingViewModel
	) = OnboardingUiState(
		header = Text(R.string.onboarding_tell_us_about_interests_logo),
		title = R.string.onboarding_tell_us_about_interests_title,
		description = R.string.onboarding_tell_us_about_interests_description,
		contentSeparatorHeightDp = 12.dp,
		mainButtonTitle = R.string.onboarding_next_step,
		isBackButtonVisible = true,
		isSkipButtonVisible = false,
		content = {
			OnboardingTellUsAboutInterests(
				state = state,
				columnScope = this,
				onTagClicked = { viewModel.onEvent(InterestSelected(it.id.toInt())) }
			)
		}
	)

	private fun helpYouStay(
		state: OnboardingState,
		viewModel: OnboardingViewModel
	) = OnboardingUiState(
		header = Text(R.string.onboarding_help_you_stay_logo),
		title = R.string.onboarding_help_you_stay_title,
		description = R.string.onboarding_help_you_stay_description,
		contentSeparatorHeightDp = 24.dp,
		mainButtonTitle = R.string.onboarding_bind,
		isBackButtonVisible = true,
		isSkipButtonVisible = true,
		content = {
			OnboardingHelpYouStay(
				state = state,
				columnScope = this,
				phoneNumberChanged = { viewModel.onEvent(PhoneNumberValueChanged(it)) },
				notificationStartTimeSelected = { viewModel.onEvent(NotificationStartTimeSelected(it)) },
				notificationEndTimeSelected = { viewModel.onEvent(NotificationEndTimeSelected(it)) },
			)
		}
	)

	private fun spendYourTimeProductively(state: OnboardingState) = OnboardingUiState(
		header = Text(R.string.onboarding_spend_your_time_productively_logo),
		title = R.string.onboarding_spend_your_time_productively_title,
		description = R.string.onboarding_spend_your_time_productively_description,
		contentSeparatorHeightDp = 40.dp,
		mainButtonTitle = R.string.onboarding_start_learning,
		isBackButtonVisible = true,
		isSkipButtonVisible = false,
		content = { OnboardingSpendYourTimeProductively(state, this) }
	)
}