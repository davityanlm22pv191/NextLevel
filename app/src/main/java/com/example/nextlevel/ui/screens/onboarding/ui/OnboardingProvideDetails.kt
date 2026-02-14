package com.example.nextlevel.ui.screens.onboarding.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.nextlevel.R
import com.example.nextlevel.data.onboarding.model.OnboardingInfo
import com.example.nextlevel.domain.model.DataInfo
import com.example.nextlevel.domain.model.Sex
import com.example.nextlevel.ui.common.SexChoosingMenu
import com.example.nextlevel.ui.common.textfield.NameTextField
import com.example.nextlevel.ui.common.textfield.PasswordTextField
import com.example.nextlevel.ui.common.textfield.TextFieldState
import com.example.nextlevel.ui.screens.onboarding.presentation.OnboardingState

@Composable
fun OnboardingProvideDetails(
	state: OnboardingState,
	columnScope: ColumnScope,
	onUserNameChanged: (String) -> Unit,
	onPasswordChanged: (String) -> Unit,
	onRepeatedPasswordChanged: (String) -> Unit,
	onSexChosen: (Sex) -> Unit,
) = with(columnScope) {
	NameTextField(
		modifier = Modifier
			.fillMaxWidth()
			.padding(horizontal = 16.dp),
		label = stringResource(R.string.common_user_name),
		value = state.userName.value,
		isError = state.userName.isError,
		onNextClicked = {},
		onValueChanged = { onUserNameChanged(it) },
	)
	PasswordTextField(
		modifier = Modifier
			.fillMaxWidth()
			.padding(horizontal = 16.dp)
			.padding(top = 6.dp),
		value = state.password.value,
		label = stringResource(R.string.common_password),
		isError = state.password.isError,
		onDoneClicked = {},
		onValueChanged = { onPasswordChanged(it) }
	)
	PasswordTextField(
		modifier = Modifier
			.fillMaxWidth()
			.padding(horizontal = 16.dp)
			.padding(top = 6.dp),
		value = state.repeatedPassword.value,
		label = stringResource(R.string.common_repeat_password),
		isError = state.repeatedPassword.isError,
		onDoneClicked = {},
		onValueChanged = { onRepeatedPasswordChanged(it) }
	)
	SexChoosingMenu(
		modifier = Modifier
			.padding(horizontal = 16.dp)
			.padding(top = 12.dp)
			.fillMaxWidth()
			.padding(bottom = 16.dp),
		selectedSex = state.sex,
		isError = state.isSexError,
		onSexChosen = { onSexChosen(it) }
	)
}

@Preview(showSystemUi = false, showBackground = true, backgroundColor = 0xFFFFFFFF)
@Composable
private fun OnboardingProvideDetailsPreview() {
	Column {
		OnboardingProvideDetails(
			columnScope = this,
			state = OnboardingState(
				onboardingInfo = DataInfo.Success(OnboardingInfo.empty()),
				step = OnboardingState.Step.PROVIDE_DETAILS,
				password = TextFieldState(value = "123456", isError = true),
				sex = Sex.MALE
			),
			onUserNameChanged = {},
			onPasswordChanged = {},
			onRepeatedPasswordChanged = {},
			onSexChosen = {}
		)
	}
}