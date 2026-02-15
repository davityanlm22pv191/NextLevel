package com.example.nextlevel.ui.screens.onboarding.ui

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.nextlevel.R
import com.example.nextlevel.helpers.FormatHelper
import com.example.nextlevel.ui.common.textfield.PhoneTextField
import com.example.nextlevel.ui.screens.onboarding.presentation.OnboardingState
import com.example.nextlevel.ui.theme.Black16
import com.example.nextlevel.ui.theme.ContainerColor
import com.example.nextlevel.ui.theme.Grey82
import com.example.nextlevel.ui.theme.GreyD5
import com.example.nextlevel.ui.theme.Typography

@Composable
fun OnboardingHelpYouStay(
	state: OnboardingState,
	columnScope: ColumnScope,
	phoneNumberChanged: (String) -> Unit,
	notificationStartTimeSelected: (String) -> Unit,
	notificationEndTimeSelected: (String) -> Unit
) = with(columnScope) {
	PhoneTextField(
		modifier = Modifier
			.fillMaxWidth()
			.padding(horizontal = 16.dp),
		value = state.phoneNumber.value,
		label = stringResource(R.string.common_phone_number),
		isError = state.phoneNumber.isError,
		onNextClicked = {},
		onValueChanged = { phone -> phoneNumberChanged(phone) }
	)
	Text(
		modifier = Modifier
			.padding(top = 16.dp)
			.padding(horizontal = 16.dp),
		text = stringResource(R.string.onboarding_what_time_you_want_receive_notifications),
		style = Typography.labelMedium.copy(color = Black16)
	)
	Row(
		modifier = Modifier
			.fillMaxWidth()
			.padding(top = 12.dp)
			.padding(horizontal = 16.dp),
		horizontalArrangement = Arrangement.spacedBy(12.dp),
		verticalAlignment = Alignment.CenterVertically
	) {
		TimeSelector(
			modifier = Modifier.weight(1f),
			selectedTime = state.notificationStartTime,
			onTimeSelected = { time -> notificationStartTimeSelected(time) }
		)
		HorizontalDivider(
			modifier = Modifier.width(16.dp),
			thickness = 1.dp,
			color = Black16
		)
		TimeSelector(
			modifier = Modifier.weight(1f),
			selectedTime = state.notificationEndTime,
			onTimeSelected = { time -> notificationEndTimeSelected(time) }
		)
	}
}

@Composable
private fun TimeSelector(
	modifier: Modifier = Modifier,
	selectedTime: String?,
	onTimeSelected: (String) -> Unit
) {
	val isDropdownExpanded = remember { mutableStateOf(false) }
	val arrowDegrees = animateFloatAsState(
		targetValue = if (isDropdownExpanded.value) 180f else 0f
	)
	Box(
		modifier
			.clip(RoundedCornerShape(12.dp))
			.clickable { isDropdownExpanded.value = !isDropdownExpanded.value }) {
		Row(
			modifier = Modifier
				.background(color = ContainerColor)
				.clip(RoundedCornerShape(12.dp))
				.border(1.dp, GreyD5, RoundedCornerShape(12.dp))
				.padding(horizontal = 16.dp, vertical = 14.dp),
			verticalAlignment = Alignment.CenterVertically
		) {
			Text(
				modifier = Modifier.weight(1f),
				text = selectedTime ?: "09:00",
				style = Typography.labelMedium.copy(
					color = if (selectedTime == null) Grey82 else Black16
				)
			)
			Image(
				modifier = Modifier.rotate(arrowDegrees.value),
				painter = painterResource(R.drawable.ic_arrow_down_black_16),
				contentDescription = null,
				colorFilter = ColorFilter.tint(
					color = if (selectedTime == null) Grey82 else Black16
				)
			)
		}
		DropdownMenu(
			modifier = Modifier.heightIn(max = 200.dp),
			expanded = isDropdownExpanded.value,
			onDismissRequest = { isDropdownExpanded.value = false },
			shape = RoundedCornerShape(24.dp),
			containerColor = ContainerColor,
		) {
			val timeList = remember { generateTimeList() }
			timeList.forEach { time ->
				DropdownMenuItem(
					text = { Text(text = time, style = Typography.labelMedium.copy(Black16)) },
					onClick = {
						isDropdownExpanded.value = false
						if (time != selectedTime) {
							onTimeSelected(time)
						}
					}
				)
			}
		}
	}
}

private fun generateTimeList(stepMinutes: Int = 30): List<String> {
	require(stepMinutes > 0 && stepMinutes <= 60) {
		"stepMinutes должен быть в диапазоне от 1 до 60"
	}

	val times = mutableListOf<String>()
	var totalMinutes = 0
	while (totalMinutes < 24 * 60) {
		val hour = totalMinutes / 60
		val minute = totalMinutes % 60
		times.add(FormatHelper.formatHourAndMinutes(hour, minute))
		totalMinutes += stepMinutes
	}
	times.add(FormatHelper.formatHourAndMinutes(0, 0))

	return times
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
@Composable
private fun OnboardingHelpYouStayPreview() {
	Column {
		OnboardingHelpYouStay(
			columnScope = this,
			state = OnboardingState(
				step = OnboardingState.Step.HELP_YOU_STAY,
			),
			phoneNumberChanged = {},
			notificationStartTimeSelected = {},
			notificationEndTimeSelected = {}
		)
	}
}