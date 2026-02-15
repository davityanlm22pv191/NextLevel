package com.example.nextlevel.ui.screens.onboarding

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetValue
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.nextlevel.R
import com.example.nextlevel.domain.model.DataInfo
import com.example.nextlevel.navigation.Navigator
import com.example.nextlevel.ui.common.PurpleButton
import com.example.nextlevel.ui.common.RoundedTopCornerShape
import com.example.nextlevel.ui.common.TransparentButton
import com.example.nextlevel.ui.common.header.Header
import com.example.nextlevel.ui.common.header.HeaderLogoType.Image
import com.example.nextlevel.ui.common.header.HeaderLogoType.Text
import com.example.nextlevel.ui.screens.onboarding.presentation.OnboardingEvent
import com.example.nextlevel.ui.screens.onboarding.presentation.OnboardingEvent.NextStepClicked
import com.example.nextlevel.ui.screens.onboarding.presentation.OnboardingEvent.SkipButtonClicked
import com.example.nextlevel.ui.screens.onboarding.presentation.OnboardingState
import com.example.nextlevel.ui.screens.onboarding.presentation.OnboardingState.Step.TELL_US_ABOUT_INTERESTS
import com.example.nextlevel.ui.screens.onboarding.presentation.OnboardingViewModel
import com.example.nextlevel.ui.screens.onboarding.ui.OnboardingCongratulations
import com.example.nextlevel.ui.screens.onboarding.ui.OnboardingHelpYouStay
import com.example.nextlevel.ui.screens.onboarding.ui.OnboardingKnowledgeFromMasters
import com.example.nextlevel.ui.screens.onboarding.ui.OnboardingMoreOpportunities
import com.example.nextlevel.ui.screens.onboarding.ui.OnboardingProvideDetails
import com.example.nextlevel.ui.screens.onboarding.ui.OnboardingSpendYourTimeProductively
import com.example.nextlevel.ui.screens.onboarding.ui.OnboardingTellUsAboutInterests
import com.example.nextlevel.ui.screens.onboarding.ui.OnboardingWelcome
import com.example.nextlevel.ui.screens.onboarding.ui.uistate.OnboardingUiState
import com.example.nextlevel.ui.theme.ContainerColor
import com.example.nextlevel.ui.theme.ScreenColor
import com.example.nextlevel.ui.theme.Transparent

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OnboardingScreen(navigator: Navigator) {
	val viewModel = hiltViewModel<OnboardingViewModel>()
	val state by viewModel.state.collectAsStateWithLifecycle()
	OnboardingContent(
		state,
		OnboardingUiState(state, viewModel),
		onDismissRequest = { navigator.goBack() },
		onPreviousStepClicked = { viewModel.onEvent(OnboardingEvent.PreviousStepClicked) },
		onNextStepClicked = { viewModel.onEvent(NextStepClicked) },
		onSkipClicked = { viewModel.onEvent(SkipButtonClicked) }
	)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun OnboardingContent(
	state: OnboardingState,
	uiState: OnboardingUiState,
	onDismissRequest: () -> Unit,
	onPreviousStepClicked: () -> Unit,
	onNextStepClicked: () -> Unit,
	onSkipClicked: () -> Unit,
) {
	val sheetState = rememberModalBottomSheetState(
		skipPartiallyExpanded = true,
		confirmValueChange = { sheetValue -> sheetValue != SheetValue.Hidden }
	)
	ModalBottomSheet(
		modifier = Modifier
			.fillMaxWidth()
			.wrapContentHeight(),
		containerColor = ScreenColor,
		sheetState = sheetState,
		scrimColor = Transparent,
		onDismissRequest = { onDismissRequest() }
	) {
		Column(
			modifier = Modifier
				.fillMaxWidth()
				.verticalScroll(rememberScrollState())
				.imePadding()
				.navigationBarsPadding()
		) {
			Header(
				logo = uiState.header,
				title = stringResource(uiState.title),
				description = uiState.description?.let { resId -> stringResource(resId) },
				onBackButtonClicked = { onPreviousStepClicked() }.takeIf { uiState.isBackButtonVisible }
			)

			Spacer(modifier = Modifier.height(uiState.contentSeparatorHeightDp))

			@SuppressLint("UnusedContentLambdaTargetStateParameter")
			AnimatedContent(targetState = state.step) {
				Column { uiState.content.invoke(this@ModalBottomSheet) }
			}

			Box(
				modifier = Modifier
					.fillMaxWidth()
					.padding(top = if (state.step != TELL_US_ABOUT_INTERESTS) 20.dp else 0.dp)
					.shadow(8.dp, RoundedTopCornerShape(16.dp))
					.background(ContainerColor, RoundedTopCornerShape(16.dp))
					.padding(16.dp)
			) {
				Column(
					modifier = Modifier.fillMaxWidth(),
					verticalArrangement = Arrangement.spacedBy(8.dp),
					horizontalAlignment = Alignment.CenterHorizontally
				) {
					PurpleButton(
						modifier = Modifier
							.fillMaxWidth()
							.height(45.dp),
						text = stringResource(uiState.mainButtonTitle),
						isEnabled = state.isMainButtonEnabled,
						isLoading = state.onboardingInfo is DataInfo.Loading,
						onClick = { onNextStepClicked() }
					)
					AnimatedVisibility(
						visible = uiState.isSkipButtonVisible
					) {
						TransparentButton(
							modifier = Modifier
								.fillMaxWidth()
								.height(45.dp),
							text = stringResource(R.string.common_skip),
							onClick = { onSkipClicked() }
						)
					}
				}
			}
		}
	}
}

@Preview
@Composable
fun OnboardingContentCongratulationsPreview() {
	val state = OnboardingState(step = OnboardingState.Step.CONGRATULATIONS)
	OnboardingContent(
		state,
		uiState = OnboardingUiState(
			header = Image(R.drawable.ic_letter_n),
			title = R.string.onboarding_congratulations_welcome_to_next_level,
			description = R.string.onboarding_congratulations_description,
			contentSeparatorHeightDp = 12.dp,
			mainButtonTitle = R.string.onboarding_next_step,
			isBackButtonVisible = false,
			isSkipButtonVisible = false,
			content = { OnboardingCongratulations(state, this) }
		),
		onDismissRequest = {},
		onPreviousStepClicked = {},
		onNextStepClicked = {},
		onSkipClicked = {}
	)
}

@Preview
@Composable
fun OnboardingContentWelcomePreview() {
	val state = OnboardingState(step = OnboardingState.Step.WELCOME)
	OnboardingContent(
		state,
		uiState = OnboardingUiState(
			header = Image(R.drawable.ic_letter_n),
			title = R.string.onboarding_congratulations_welcome_to_next_level,
			description = R.string.onboarding_welcome_description,
			contentSeparatorHeightDp = 40.dp,
			mainButtonTitle = R.string.onboarding_next_step,
			isBackButtonVisible = false,
			isSkipButtonVisible = false,
			content = { OnboardingWelcome(state, this) }
		),
		onDismissRequest = {},
		onPreviousStepClicked = {},
		onNextStepClicked = {},
		onSkipClicked = {}
	)
}

@Preview
@Composable
fun OnboardingContentProvideDetailsPreview() {
	val state = OnboardingState(step = OnboardingState.Step.PROVIDE_DETAILS)
	OnboardingContent(
		state,
		uiState = OnboardingUiState(
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
					onUserNameChanged = {},
					onPasswordChanged = {},
					onRepeatedPasswordChanged = {},
					onSexChosen = {}
				)
			}
		),
		onDismissRequest = {},
		onPreviousStepClicked = {},
		onNextStepClicked = {},
		onSkipClicked = {}
	)
}

@Preview
@Composable
fun OnboardingContentMoreOpportunitiesPreview() {
	val state = OnboardingState(step = OnboardingState.Step.MORE_OPPORTUNITIES)
	OnboardingContent(
		state,
		uiState = OnboardingUiState(
			header = Text(R.string.onboarding_more_opportunities_logo),
			title = R.string.onboarding_more_opportunities_title,
			description = R.string.onboarding_more_opportunities_description,
			contentSeparatorHeightDp = 40.dp,
			mainButtonTitle = R.string.onboarding_next_step,
			isBackButtonVisible = true,
			isSkipButtonVisible = false,
			content = { OnboardingMoreOpportunities(state, this) }
		),
		onDismissRequest = {},
		onPreviousStepClicked = {},
		onNextStepClicked = {},
		onSkipClicked = {}
	)
}

@Preview
@Composable
fun OnboardingContentKnowledgeFromMastersPreview() {
	val state = OnboardingState(step = OnboardingState.Step.KNOWLEDGE_FROM_MASTERS)
	OnboardingContent(
		state,
		uiState = OnboardingUiState(
			header = Text(R.string.onboarding_knowledge_from_masters_logo),
			title = R.string.onboarding_knowledge_from_masters_title,
			description = R.string.onboarding_knowledge_from_masters_description,
			contentSeparatorHeightDp = 40.dp,
			mainButtonTitle = R.string.onboarding_next_step,
			isBackButtonVisible = true,
			isSkipButtonVisible = false,
			content = { OnboardingKnowledgeFromMasters(state, this) }
		),
		onDismissRequest = {},
		onPreviousStepClicked = {},
		onNextStepClicked = {},
		onSkipClicked = {}
	)
}

@Preview
@Composable
fun OnboardingContentTellUsAboutYourInterestsPreview() {
	val state = OnboardingState(step = TELL_US_ABOUT_INTERESTS)
	OnboardingContent(
		state,
		uiState = OnboardingUiState(
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
					onTagClicked = {}
				)
			}
		),
		onDismissRequest = {},
		onPreviousStepClicked = {},
		onNextStepClicked = {},
		onSkipClicked = {}
	)
}

@Preview
@Composable
fun OnboardingContentHelpYouStayPreview() {
	val state = OnboardingState(step = OnboardingState.Step.HELP_YOU_STAY)
	OnboardingContent(
		state,
		uiState = OnboardingUiState(
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
					phoneNumberChanged = {},
					notificationStartTimeSelected = {},
					notificationEndTimeSelected = {},
				)
			}
		),
		onDismissRequest = {},
		onPreviousStepClicked = {},
		onNextStepClicked = {},
		onSkipClicked = {}
	)
}

@Preview
@Composable
fun OnboardingSpendYourTimeProductivelyPreview() {
	val state = OnboardingState(step = OnboardingState.Step.SPEND_YOUR_TIME_PRODUCTIVELY)
	OnboardingContent(
		state,
		uiState = OnboardingUiState(
			header = Text(R.string.onboarding_spend_your_time_productively_logo),
			title = R.string.onboarding_spend_your_time_productively_title,
			description = R.string.onboarding_spend_your_time_productively_description,
			contentSeparatorHeightDp = 40.dp,
			mainButtonTitle = R.string.onboarding_start_learning,
			isBackButtonVisible = true,
			isSkipButtonVisible = false,
			content = { OnboardingSpendYourTimeProductively(state, this) }
		),
		onDismissRequest = {},
		onPreviousStepClicked = {},
		onNextStepClicked = {},
		onSkipClicked = {}
	)
}