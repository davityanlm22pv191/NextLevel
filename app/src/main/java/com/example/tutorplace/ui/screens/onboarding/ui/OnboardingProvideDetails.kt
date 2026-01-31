package com.example.tutorplace.ui.screens.onboarding.ui

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.tutorplace.R
import com.example.tutorplace.data.onboarding.model.OnboardingInfo
import com.example.tutorplace.domain.model.DataInfo
import com.example.tutorplace.domain.model.Sex
import com.example.tutorplace.ui.common.textfield.NameTextField
import com.example.tutorplace.ui.common.textfield.PasswordTextField
import com.example.tutorplace.ui.common.textfield.TextFieldState
import com.example.tutorplace.ui.screens.onboarding.presentation.OnboardingState
import com.example.tutorplace.ui.theme.Black16
import com.example.tutorplace.ui.theme.ContainerColor
import com.example.tutorplace.ui.theme.Grey82
import com.example.tutorplace.ui.theme.GreyD5
import com.example.tutorplace.ui.theme.Red1D
import com.example.tutorplace.ui.theme.Typography
import com.example.tutorplace.ui.theme.White

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

@Composable
private fun SexChoosingMenu(
	modifier: Modifier = Modifier,
	selectedSex: Sex?,
	isError: Boolean,
	onSexChosen: (Sex) -> Unit
) {
	val sexList = listOf(Sex.MALE, Sex.FEMALE)
	var isOpen by remember { mutableStateOf(false) }
	val rotateDegrees by animateFloatAsState(targetValue = if (isOpen) 180f else 0f)

	Surface(
		modifier = modifier,
		shape = RoundedCornerShape(12.dp),
		color = if (isError) White else ContainerColor,
		border = BorderStroke(1.dp, if (isError) Red1D else GreyD5),
		onClick = { isOpen = !isOpen }
	) {
		Row(
			modifier = Modifier
				.fillMaxWidth()
				.padding(horizontal = 16.dp, vertical = 14.dp),
			verticalAlignment = Alignment.CenterVertically
		) {
			Text(
				modifier = Modifier.weight(1f),
				text = stringResource(selectedSex?.stringResId ?: R.string.common_your_sex),
				style = Typography.labelMedium.copy(color = if (selectedSex != null) Black16 else Grey82),
				maxLines = 1
			)
			Image(
				modifier = Modifier.rotate(rotateDegrees),
				contentDescription = null,
				painter = painterResource(R.drawable.ic_arrow_down_black_16),
				alpha = if (selectedSex != null) 1f else 0.5f,
			)
			DropdownMenu(
				expanded = isOpen,
				onDismissRequest = { isOpen = false },
				shape = RoundedCornerShape(24.dp),
				containerColor = ContainerColor
			) {
				sexList.forEach { sex ->
					DropdownMenuItem(
						text = {
							Text(
								text = stringResource(sex.stringResId),
								style = Typography.labelMedium.copy(Black16)
							)
						},
						onClick = {
							isOpen = false
							if (selectedSex != sex) {
								onSexChosen(sex)
							}
						},
						contentPadding = PaddingValues(horizontal = 16.dp),
					)
				}
			}
		}
	}
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