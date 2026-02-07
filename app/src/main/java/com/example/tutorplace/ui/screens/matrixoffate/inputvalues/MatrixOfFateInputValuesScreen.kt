package com.example.tutorplace.ui.screens.matrixoffate.inputvalues

import androidx.compose.foundation.background
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.example.tutorplace.R
import com.example.tutorplace.domain.model.Sex
import com.example.tutorplace.helpers.FormatHelper
import com.example.tutorplace.navigation.Navigator
import com.example.tutorplace.ui.common.DataChoosePicker
import com.example.tutorplace.ui.common.PurpleButton
import com.example.tutorplace.ui.common.RoundedTopCornerShape
import com.example.tutorplace.ui.common.SexChoosingMenu
import com.example.tutorplace.ui.common.header.Header
import com.example.tutorplace.ui.common.header.HeaderLogoType
import com.example.tutorplace.ui.common.textfield.NameTextField
import com.example.tutorplace.ui.screens.matrixoffate.inputvalues.presentation.MatrixOfFateInputValuesEffect
import com.example.tutorplace.ui.screens.matrixoffate.inputvalues.presentation.MatrixOfFateInputValuesEvent.BirthDateSelected
import com.example.tutorplace.ui.screens.matrixoffate.inputvalues.presentation.MatrixOfFateInputValuesEvent.CalculateButtonClicked
import com.example.tutorplace.ui.screens.matrixoffate.inputvalues.presentation.MatrixOfFateInputValuesEvent.NameValueChanged
import com.example.tutorplace.ui.screens.matrixoffate.inputvalues.presentation.MatrixOfFateInputValuesEvent.SexChosen
import com.example.tutorplace.ui.screens.matrixoffate.inputvalues.presentation.MatrixOfFateInputValuesState
import com.example.tutorplace.ui.screens.matrixoffate.inputvalues.presentation.MatrixOfFateInputValuesViewModel
import com.example.tutorplace.ui.theme.ContainerColor
import com.example.tutorplace.ui.theme.ScreenColor
import com.example.tutorplace.ui.theme.Transparent
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate

@Composable
fun MatrixOfFateInputValuesScreen(navigator: Navigator) {
	val viewModel = hiltViewModel<MatrixOfFateInputValuesViewModel>()
	val state by viewModel.state.collectAsState()
	CollectEffects(viewModel.effect, navigator)
	MatrixOfFateInputValuesContent(
		state,
		onDismissRequest = { navigator.goBack() },
		onUserNameChanged = { userName -> viewModel.onEvent(NameValueChanged(userName)) },
		onSexChosen = { sex -> viewModel.onEvent(SexChosen(sex)) },
		onCalculateButtonClicked = { viewModel.onEvent(CalculateButtonClicked) },
		onBirthDateSelected = { selectedDate -> viewModel.onEvent(BirthDateSelected(selectedDate)) }
	)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun MatrixOfFateInputValuesContent(
	state: MatrixOfFateInputValuesState,
	onDismissRequest: () -> Unit,
	onUserNameChanged: (String) -> Unit,
	onSexChosen: (Sex) -> Unit,
	onCalculateButtonClicked: () -> Unit,
	onBirthDateSelected: (LocalDate) -> Unit
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
		onDismissRequest = onDismissRequest
	) {
		Column(
			modifier = Modifier
				.fillMaxWidth()
				.verticalScroll(rememberScrollState())
				.imePadding()
				.navigationBarsPadding()
		) {
			Header(
				logo = HeaderLogoType.Text(R.string.matrix_of_fate_input_data_title_logo),
				title = stringResource(R.string.matrix_of_fate_input_data_title),
				description = null,
				throwable = null,
				onBackButtonClicked = onDismissRequest
			)
			Spacer(modifier = Modifier.height(20.dp))
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
			DataChoosePicker(
				modifier = Modifier
					.fillMaxWidth()
					.height(48.dp)
					.padding(horizontal = 16.dp),
				value = state.birthDate,
				format = FormatHelper.DATE_MONTH_YEAR,
				label = stringResource(R.string.matrix_of_fate_field_birthdate_hint),
				onValueChange = { selectedDate -> onBirthDateSelected(selectedDate) },
				isError = state.isBirthDateError
			)
			SexChoosingMenu(
				modifier = Modifier
					.padding(horizontal = 16.dp)
					.padding(top = 12.dp)
					.fillMaxWidth()
					.padding(bottom = 16.dp),
				selectedSex = state.sex,
				isError = state.isSexError,
				onSexChosen = { sex -> onSexChosen(sex) }
			)
			Box(
				modifier = Modifier
					.fillMaxWidth()
					.padding(top = 128.dp)
					.shadow(8.dp, RoundedTopCornerShape(16.dp))
					.background(ContainerColor, RoundedTopCornerShape(16.dp))
					.padding(16.dp)
			) {
				PurpleButton(
					modifier = Modifier
						.fillMaxWidth()
						.height(45.dp),
					text = stringResource(R.string.common_calculate),
					isEnabled = state.isCalculateButtonEnabled,
					isLoading = state.isLoading,
					onClick = { onCalculateButtonClicked() }
				)
			}
		}
	}
}

@Composable
private fun CollectEffects(effect: Flow<MatrixOfFateInputValuesEffect>, navigator: Navigator) {
	LaunchedEffect(Unit) {
		effect.collect { mailEffect ->
			when (mailEffect) {
				else -> navigator
			}
		}
	}
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
@Composable
private fun MatrixOfFateInputValuesPreview() {
	MatrixOfFateInputValuesContent(
		state = MatrixOfFateInputValuesState(),
		onDismissRequest = {},
		onUserNameChanged = {},
		onSexChosen = {},
		onCalculateButtonClicked = {},
		onBirthDateSelected = {}
	)
}