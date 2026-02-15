package com.example.nextlevel.ui.common.textfield

import androidx.annotation.DrawableRes
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.focusable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.input.KeyboardActionHandler
import androidx.compose.foundation.text.input.TextObfuscationMode
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.foundation.text.input.setTextAndPlaceCursorAtEnd
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDefaults
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedSecureTextField
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.SelectableDates
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.ImeAction.Companion.Done
import androidx.compose.ui.text.input.ImeAction.Companion.Next
import androidx.compose.ui.text.input.KeyboardType.Companion.Email
import androidx.compose.ui.text.input.KeyboardType.Companion.Password
import androidx.compose.ui.text.input.KeyboardType.Companion.Phone
import androidx.compose.ui.text.input.KeyboardType.Companion.Text
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.nextlevel.R
import com.example.nextlevel.extension.toEpochMillis
import com.example.nextlevel.extension.toLocalDate
import com.example.nextlevel.helpers.FormatHelper
import com.example.nextlevel.ui.theme.Black16
import com.example.nextlevel.ui.theme.ContainerColor
import com.example.nextlevel.ui.theme.Grey82
import com.example.nextlevel.ui.theme.GreyD5
import com.example.nextlevel.ui.theme.PurpleCC
import com.example.nextlevel.ui.theme.Red1D
import com.example.nextlevel.ui.theme.Typography
import com.example.nextlevel.ui.theme.White
import java.time.LocalDate

private val outlinedTextFieldColors: TextFieldColors
	@Composable get() = OutlinedTextFieldDefaults.colors(
		focusedContainerColor = ContainerColor,
		unfocusedContainerColor = ContainerColor,
		focusedBorderColor = PurpleCC,
		unfocusedBorderColor = GreyD5,
		errorBorderColor = Red1D,
		focusedLabelColor = Grey82,
		unfocusedLabelColor = Grey82,
		errorLabelColor = Red1D,
		errorContainerColor = White,
		errorTrailingIconColor = Red1D
	)

@Composable
fun EmailTextField(
	modifier: Modifier = Modifier,
	value: String,
	label: String,
	isError: Boolean = false,
	onNextClicked: () -> Unit,
	onValueChanged: (String) -> Unit,
) {
	val textStyle = remember { Typography.labelMedium.copy(color = Black16) }
	OutlinedTextField(
		modifier = modifier.fillMaxWidth(),
		shape = RoundedCornerShape(12.dp),
		value = value,
		onValueChange = { onValueChanged(it) },
		singleLine = true,
		textStyle = textStyle,
		colors = outlinedTextFieldColors,
		isError = isError,
		label = {
			Text(
				modifier = Modifier
					.clip(RoundedCornerShape(20.dp))
					.padding(horizontal = 2.dp),
				text = label,
				style = Typography.labelSmall,
				color = Grey82
			)
		},
		keyboardOptions = KeyboardOptions(keyboardType = Email, imeAction = Next),
		keyboardActions = KeyboardActions(onNext = { onNextClicked() })
	)
}

@Composable
fun PasswordTextField(
	modifier: Modifier = Modifier,
	value: String,
	label: String,
	isError: Boolean = false,
	onDoneClicked: () -> Unit,
	onValueChanged: (String) -> Unit,
) {
	var passwordVisible by remember { mutableStateOf(false) }
	val textStyle = remember { Typography.labelMedium.copy(color = Black16) }
	val state = rememberTextFieldState(initialText = value)
	LaunchedEffect(state) {
		snapshotFlow { state.text }.collect { onValueChanged(it.toString()) }
	}

	OutlinedSecureTextField(
		modifier = modifier.fillMaxWidth(),
		state = state,
		shape = RoundedCornerShape(12.dp),
		isError = isError,
		textStyle = textStyle,
		colors = outlinedTextFieldColors,
		label = {
			Text(
				modifier = Modifier
					.clip(RoundedCornerShape(20.dp))
					.padding(horizontal = 2.dp),
				text = label,
				style = Typography.labelSmall,
				color = Grey82
			)
		},
		trailingIcon = {
			if (value.isNotBlank()) {
				@DrawableRes val iconResId = if (passwordVisible) {
					R.drawable.ic_eye_hidden
				} else {
					R.drawable.ic_eye
				}
				IconButton(onClick = { passwordVisible = !passwordVisible }) {
					Image(
						painter = painterResource(iconResId),
						contentDescription = null,
						colorFilter = ColorFilter.tint(Red1D).takeIf { isError }
					)
				}
			}
		},
		keyboardOptions = KeyboardOptions(keyboardType = Password, imeAction = Done),
		textObfuscationMode = if (passwordVisible) {
			TextObfuscationMode.Visible
		} else {
			TextObfuscationMode.Hidden
		},
		textObfuscationCharacter = '*',
		onKeyboardAction = KeyboardActionHandler { onDoneClicked() },
	)
}

@Composable
fun NameTextField(
	modifier: Modifier = Modifier,
	value: String,
	label: String,
	isError: Boolean = false,
	onNextClicked: () -> Unit,
	onValueChanged: (String) -> Unit,
) {
	OutlinedTextField(
		modifier = modifier
			.fillMaxWidth()
			.focusable(),
		shape = RoundedCornerShape(12.dp),
		value = value,
		label = {
			Text(
				modifier = Modifier
					.clip(RoundedCornerShape(20.dp))
					.padding(horizontal = 2.dp),
				text = label,
				style = Typography.labelSmall,
				color = Grey82
			)
		},
		onValueChange = { onValueChanged(it) },
		singleLine = true,
		textStyle = Typography.labelMedium.copy(color = Black16),
		colors = outlinedTextFieldColors,
		isError = isError,
		keyboardOptions = KeyboardOptions(keyboardType = Text, imeAction = Next),
		keyboardActions = KeyboardActions(onNext = { onNextClicked() }),
	)
}

@Composable
fun PhoneTextField(
	modifier: Modifier = Modifier,
	value: String,
	label: String,
	isError: Boolean = false,
	onNextClicked: () -> Unit,
	onValueChanged: (String) -> Unit,
) {
	val textFieldValue = remember(value) {
		val formattedPhone = formatRussianPhone(value)
		TextFieldValue(
			text = formattedPhone,
			selection = TextRange(formattedPhone.length)
		)
	}
	OutlinedTextField(
		modifier = modifier
			.fillMaxWidth()
			.focusable(),
		value = textFieldValue,
		shape = RoundedCornerShape(12.dp),
		label = {
			Text(
				modifier = Modifier
					.clip(RoundedCornerShape(20.dp))
					.padding(horizontal = 2.dp),
				text = label,
				style = Typography.labelSmall,
				color = Grey82
			)
		},
		onValueChange = { newValue ->
			val filteredPhone = newValue.text
				.filter { it.isDigit() }
				.take(11)
				.replaceFirstChar { if (it == '8') '7' else it }
			onValueChanged(filteredPhone)
		},
		singleLine = true,
		textStyle = Typography.labelMedium.copy(color = Black16),
		colors = outlinedTextFieldColors,
		isError = isError,
		keyboardOptions = KeyboardOptions(keyboardType = Phone, imeAction = Next),
		keyboardActions = KeyboardActions(onNext = { onNextClicked() }),
	)
}

private fun formatRussianPhone(phone: String): String {
	if (phone.isEmpty()) return ""
	// Оставляем только цифры
	var digits = phone.filter { it.isDigit() }

	// Если начинается на 8, меняем на 7
	if (digits.startsWith("8")) {
		digits = "7" + digits.drop(1)
	}

	// Если начинается на 9 или 1 и т.п., добавляем 7 в начало
	if (digits.isNotEmpty() && digits[0] != '7') {
		digits = "7$digits"
	}

	// Если пусто — возвращаем "+7"
	if (digits.isEmpty()) return "+7"

	// Формируем результат
	val sb = StringBuilder("+")
	for (i in digits.indices) {
		when (i) {
			0 -> sb.append(digits[i])                  // +7
			1 -> sb.append(" (").append(digits[i])     // +7 (9
			3 -> sb.append(digits[i]).append(") ")     // +7 (99)
			6 -> sb.append(digits[i]).append(" ")      // +7 (999) 999
			8 -> sb.append(digits[i]).append("-")      // +7 (999) 999 99-
			else -> sb.append(digits[i])
		}
	}

	return sb.dropLastWhile { !it.isDigit() }.toString()
}

@Composable
fun TelegramTextField(
	modifier: Modifier = Modifier,
	value: String,
	label: String,
	isError: Boolean = false,
	onDoneClicked: () -> Unit,
	onValueChanged: (String) -> Unit,
) {
	OutlinedTextField(
		modifier = modifier
			.fillMaxWidth()
			.focusable(),
		value = value,
		shape = RoundedCornerShape(12.dp),
		label = {
			Text(
				modifier = Modifier
					.clip(RoundedCornerShape(20.dp))
					.padding(horizontal = 2.dp),
				text = label,
				style = Typography.labelSmall,
				color = Grey82
			)
		},
		onValueChange = { onValueChanged(it) },
		prefix = {
			Text(
				text = "@",
				style = Typography.labelMedium,
				color = Black16
			)
		},
		singleLine = true,
		textStyle = Typography.labelMedium.copy(color = Black16),
		colors = outlinedTextFieldColors,
		isError = isError,
		keyboardOptions = KeyboardOptions(keyboardType = Email, imeAction = Next),
		keyboardActions = KeyboardActions(onNext = { onDoneClicked() }),
	)
}

@Composable
fun DateChoosePicker(
	modifier: Modifier = Modifier,
	date: LocalDate?,
	format: String,
	onValueChanged: (LocalDate) -> Unit,
	label: String,
	isError: Boolean,
	selectableDates: SelectableDates = DatePickerDefaults.AllDates,
) {
	val textState = rememberTextFieldState(
		initialText = date
			?.let { localDate -> FormatHelper.formatDate(localDate, format) }
			.orEmpty()
	)
	val datePickerState = rememberDatePickerState(
		initialSelectedDateMillis = date.toEpochMillis(),
		selectableDates = selectableDates,
	)
	var showDialog by remember { mutableStateOf(false) }
	val labelColor by animateColorAsState(if (isError) Red1D else Grey82)
	val textStyle = remember { Typography.labelMedium.copy(color = Black16) }
	Box(modifier = modifier.fillMaxWidth()) {
		OutlinedTextField(
			modifier = Modifier.fillMaxWidth(),
			state = textState,
			readOnly = true,
			label = {
				Text(
					text = label,
					style = Typography.labelSmall,
					color = labelColor
				)
			},
			textStyle = textStyle,
			colors = outlinedTextFieldColors,
			shape = RoundedCornerShape(12.dp),
			isError = isError,
		)
		Box(
			modifier = Modifier
				.matchParentSize()
				.clickable(
					indication = null,
					interactionSource = remember { MutableInteractionSource() }
				) { showDialog = true }
		)
	}

	if (showDialog) {
		DatePickerDialog(
			onDismissRequest = { showDialog = false },
			confirmButton = {
				TextButton(
					onClick = {
						datePickerState.selectedDateMillis?.let { millis ->
							val localDate = millis.toLocalDate()
							textState.setTextAndPlaceCursorAtEnd(
								FormatHelper.formatDate(localDate, format)
							)
							onValueChanged(localDate)
						}
						showDialog = false
					},
					content = { Text(stringResource(R.string.common_ok)) }
				)
			},
			dismissButton = {
				TextButton(
					onClick = { showDialog = false },
					content = { Text(stringResource(R.string.common_cancel)) }
				)
			},
			content = { DatePicker(state = datePickerState) }
		)
	}
}

@Preview
@Composable
private fun TextFieldsPreview() {
	Column(
		Modifier
			.background(White)
			.focusable()
			.padding(16.dp),
		verticalArrangement = Arrangement.spacedBy(8.dp)
	) {
		EmailTextField(
			modifier = Modifier,
			value = "example@google.com",
			label = stringResource(R.string.common_auth_your_email),
			isError = true,
			onValueChanged = {},
			onNextClicked = {}
		)
		PasswordTextField(
			modifier = Modifier,
			value = "123456",
			label = stringResource(R.string.authorization_your_password),
			isError = false,
			onValueChanged = {},
			onDoneClicked = {}
		)
		NameTextField(
			modifier = Modifier,
			value = "Name",
			label = stringResource(R.string.registration_your_name),
			isError = false,
			onValueChanged = {},
			onNextClicked = {}
		)
		PhoneTextField(
			modifier = Modifier,
			value = "79999999999",
			label = stringResource(R.string.registration_your_phone_number),
			isError = false,
			onNextClicked = {},
			onValueChanged = {}
		)
		TelegramTextField(
			modifier = Modifier,
			value = "someTelegramAddress",
			label = stringResource(R.string.registration_your_telegram),
			isError = false,
			onValueChanged = {},
			onDoneClicked = {}
		)
		DateChoosePicker(
			modifier = Modifier,
			date = LocalDate.now(),
			format = FormatHelper.DATE_MONTH_YEAR,
			onValueChanged = {},
			label = "Дата рождения",
			isError = false
		)
		DateChoosePicker(
			modifier = Modifier,
			date = null,
			format = FormatHelper.DATE_MONTH_YEAR,
			onValueChanged = {},
			label = "Дата рождения",
			isError = false
		)
		DateChoosePicker(
			modifier = Modifier,
			date = null,
			format = FormatHelper.DATE_MONTH_YEAR,
			onValueChanged = {},
			label = "Дата рождения",
			isError = true
		)
	}
}