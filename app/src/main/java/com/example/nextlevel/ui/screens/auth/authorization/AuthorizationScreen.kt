package com.example.nextlevel.ui.screens.auth.authorization

import androidx.activity.compose.BackHandler
import androidx.activity.compose.LocalActivity
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.LayoutDirection.Ltr
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.nextlevel.R
import com.example.nextlevel.navigation.Navigator
import com.example.nextlevel.navigation.destinations.Destinations
import com.example.nextlevel.ui.common.AuthSectionDivider
import com.example.nextlevel.ui.common.PurpleButton
import com.example.nextlevel.ui.common.YandexButton
import com.example.nextlevel.ui.common.header.Header
import com.example.nextlevel.ui.common.header.HeaderLogoType
import com.example.nextlevel.ui.common.spannabletext.SpanClickableText
import com.example.nextlevel.ui.common.spannabletext.SpanLinkData
import com.example.nextlevel.ui.common.textfield.EmailTextField
import com.example.nextlevel.ui.common.textfield.PasswordTextField
import com.example.nextlevel.ui.common.upscreenmessage.UpScreenMessages
import com.example.nextlevel.ui.common.upscreenmessage.UpScreenMessages.Companion.BANNER_DISPLAY_DURATION_MS
import com.example.nextlevel.ui.screens.auth.authorization.presentation.AuthorizationEffect
import com.example.nextlevel.ui.screens.auth.authorization.presentation.AuthorizationEffect.NavigateToHome
import com.example.nextlevel.ui.screens.auth.authorization.presentation.AuthorizationEffect.NavigateToRegistration
import com.example.nextlevel.ui.screens.auth.authorization.presentation.AuthorizationEffect.NavigateToRestorePassword
import com.example.nextlevel.ui.screens.auth.authorization.presentation.AuthorizationEffect.NavigateToSupport
import com.example.nextlevel.ui.screens.auth.authorization.presentation.AuthorizationEffect.NavigateToYandexAuthorization
import com.example.nextlevel.ui.screens.auth.authorization.presentation.AuthorizationEvent.EmailChanged
import com.example.nextlevel.ui.screens.auth.authorization.presentation.AuthorizationEvent.EnterClicked
import com.example.nextlevel.ui.screens.auth.authorization.presentation.AuthorizationEvent.PasswordChanged
import com.example.nextlevel.ui.screens.auth.authorization.presentation.AuthorizationEvent.RegistrationClicked
import com.example.nextlevel.ui.screens.auth.authorization.presentation.AuthorizationEvent.RestorePasswordClicked
import com.example.nextlevel.ui.screens.auth.authorization.presentation.AuthorizationEvent.SupportClicked
import com.example.nextlevel.ui.screens.auth.authorization.presentation.AuthorizationEvent.YandexAuthorizationClicked
import com.example.nextlevel.ui.screens.auth.authorization.presentation.AuthorizationState
import com.example.nextlevel.ui.screens.auth.authorization.presentation.AuthorizationViewModel
import com.example.nextlevel.ui.theme.Black16
import com.example.nextlevel.ui.theme.PurpleCC
import com.example.nextlevel.ui.theme.ScreenColor
import com.example.nextlevel.ui.theme.Typography
import kotlinx.coroutines.flow.Flow

@Composable
fun AuthorizationScreen(navigator: Navigator, showMessage: (UpScreenMessages) -> Unit) {
	val viewModel = hiltViewModel<AuthorizationViewModel>()
	val state by viewModel.state.collectAsStateWithLifecycle()
	CollectEffects(viewModel.effect, navigator)
	HandleBackPress(showMessage)
	AuthorizationContent(
		state,
		onEmailChanged = { email -> viewModel.onEvent(EmailChanged(email)) },
		onPasswordChanged = { password -> viewModel.onEvent(PasswordChanged(password)) },
		onRestoreClicked = { viewModel.onEvent(RestorePasswordClicked) },
		onEnterClicked = { viewModel.onEvent(EnterClicked) },
		onYandexClicked = { viewModel.onEvent(YandexAuthorizationClicked) },
		onSupportClicked = { viewModel.onEvent(SupportClicked) },
		onRegistrationClicked = { viewModel.onEvent(RegistrationClicked) }
	)
}

@Composable
private fun AuthorizationContent(
	state: AuthorizationState,
	onEmailChanged: (email: String) -> Unit,
	onPasswordChanged: (password: String) -> Unit,
	onRestoreClicked: () -> Unit,
	onEnterClicked: () -> Unit,
	onYandexClicked: () -> Unit,
	onSupportClicked: () -> Unit,
	onRegistrationClicked: () -> Unit,
) {
	val emailFocusRequester = remember { FocusRequester() }
	val passwordFocusRequester = remember { FocusRequester() }
	val scrollState = rememberScrollState()
	Scaffold(
		modifier = Modifier
			.fillMaxSize()
			.imePadding(),
		containerColor = ScreenColor
	) { paddingValues ->
		Column(
			modifier = Modifier
				.fillMaxSize()
				.padding(
					top = paddingValues.calculateTopPadding(),
					start = paddingValues.calculateStartPadding(Ltr),
					end = paddingValues.calculateEndPadding(Ltr),
					bottom = 0.dp
				)
				.verticalScroll(scrollState),
			horizontalAlignment = Alignment.CenterHorizontally
		) {
			Header(
				logo = HeaderLogoType.Image(R.drawable.ic_letter_n),
				title = stringResource(R.string.authorization_enter_to_profile),
				description = null,
				onBackButtonClicked = null,
			)
			EmailTextField(
				modifier = Modifier
					.padding(top = 18.dp)
					.padding(horizontal = 20.dp)
					.focusRequester(emailFocusRequester),
				value = state.email,
				label = stringResource(R.string.common_auth_your_email),
				isError = state.isEmailError,
				onNextClicked = { passwordFocusRequester.requestFocus() },
				onValueChanged = { value -> onEmailChanged(value) }
			)
			PasswordTextField(
				modifier = Modifier
					.padding(top = 6.dp)
					.padding(horizontal = 20.dp)
					.focusRequester(passwordFocusRequester),
				value = state.password,
				label = stringResource(R.string.authorization_your_password),
				isError = state.isPasswordError,
				onDoneClicked = { onEnterClicked() },
				onValueChanged = { value -> onPasswordChanged(value) }
			)
			TextButton(
				modifier = Modifier
					.align(Alignment.Start)
					.padding(start = 10.dp),
				colors = ButtonDefaults.textButtonColors(contentColor = PurpleCC),
				onClick = { onRestoreClicked() },
			) {
				Text(
					modifier = Modifier.padding(top = 6.dp),
					text = stringResource(R.string.authorization_restore_password),
					style = Typography.bodyMedium,
				)
			}
			Spacer(Modifier.weight(1f))
			PurpleButton(
				modifier = Modifier
					.fillMaxWidth()
					.padding(horizontal = 20.dp),
				text = stringResource(R.string.authorization_entry),
				isLoading = state.isLoading,
				isEnabled = true
			) {
				if (!state.isLoading) {
					onEnterClicked()
				}
			}
			AuthSectionDivider(
				modifier = Modifier
					.padding(top = 8.dp)
					.padding(horizontal = 20.dp)
			)
			YandexButton(
				modifier = Modifier
					.padding(top = 8.dp)
					.padding(horizontal = 20.dp),
				onClick = { onYandexClicked() }
			)
			SupportSection(
				onSupportClick = { onSupportClicked() },
				onRegisterClick = { onRegistrationClicked() }
			)
			Spacer(Modifier.height(paddingValues.calculateBottomPadding() + 16.dp))
		}
	}
}

@Composable
private fun SupportSection(onSupportClick: () -> Unit, onRegisterClick: () -> Unit) {
	Column(
		modifier = Modifier
			.padding(horizontal = 20.dp)
			.fillMaxWidth(),
		horizontalAlignment = Alignment.CenterHorizontally
	) {
		SpanClickableText(
			modifier = Modifier.padding(top = 12.dp),
			text = stringResource(R.string.authorization_email_error_support_hint),
			links = listOf(
				SpanLinkData(
					link = stringResource(R.string.authorization_email_error_support_hint_spannable),
					tag = "SUPPORT",
					SpanStyle(color = PurpleCC),
					onClick = onSupportClick
				)
			),
			textStyle = Typography.labelMedium.copy(textAlign = TextAlign.Center, color = Black16)
		)

		SpanClickableText(
			modifier = Modifier.padding(top = 32.dp),
			text = stringResource(R.string.authorization_not_yet_account),
			links = listOf(
				SpanLinkData(
					link = stringResource(R.string.auth_register),
					tag = "REGISTRATION",
					SpanStyle(color = PurpleCC),
					onClick = onRegisterClick
				)
			),
			textStyle = Typography.labelMedium.copy(textAlign = TextAlign.Center, color = Black16)
		)
	}
}

@Composable
private fun CollectEffects(
	effects: Flow<AuthorizationEffect>,
	navigator: Navigator,
) {
	LaunchedEffect(Unit) {
		effects.collect { effect ->
			when (effect) {
				NavigateToHome -> navigator.navigateAndClearBackStack(Destinations.Home)
				NavigateToRegistration -> navigator.navigate(Destinations.Registration)
				NavigateToRestorePassword -> navigator.navigate(Destinations.RestorePassword)
				NavigateToSupport -> navigator.navigate(Destinations.Support)
				NavigateToYandexAuthorization -> navigator.navigate(Destinations.YandexAuthorization)
			}
		}
	}
}

@Composable
private fun HandleBackPress(showMessage: (UpScreenMessages) -> Unit) {
	val activity = LocalActivity.current
	var lastBackPressedTime by remember { mutableLongStateOf(0L) }
	val upScreenMessageInfo = UpScreenMessages.Info(
		stringResource(R.string.common_back_again_to_close_app)
	)
	BackHandler {
		val currentTime = System.currentTimeMillis()
		if (currentTime - lastBackPressedTime <= BANNER_DISPLAY_DURATION_MS) {
			activity?.finish()
		} else {
			lastBackPressedTime = currentTime
			showMessage(upScreenMessageInfo)
		}
	}
}

@Preview
@Composable
private fun AuthorizationContentPreview() {
	AuthorizationContent(
		state = AuthorizationState(),
		onEmailChanged = {},
		onPasswordChanged = {},
		onRestoreClicked = {},
		onEnterClicked = {},
		onYandexClicked = {},
		onSupportClicked = {},
		onRegistrationClicked = {}
	)
}