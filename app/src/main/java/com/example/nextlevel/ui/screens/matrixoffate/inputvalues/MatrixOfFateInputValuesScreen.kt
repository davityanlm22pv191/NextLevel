package com.example.nextlevel.ui.screens.matrixoffate.inputvalues

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.ime
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.nextlevel.R
import com.example.nextlevel.domain.model.Sex
import com.example.nextlevel.helpers.FormatHelper
import com.example.nextlevel.navigation.Navigator
import com.example.nextlevel.ui.common.PurpleButton
import com.example.nextlevel.ui.common.RoundedTopCornerShape
import com.example.nextlevel.ui.common.SelectableDatesPast
import com.example.nextlevel.ui.common.SexChoosingMenu
import com.example.nextlevel.ui.common.header.Header
import com.example.nextlevel.ui.common.header.HeaderLogoType
import com.example.nextlevel.ui.common.textfield.DateChoosePicker
import com.example.nextlevel.ui.common.textfield.NameTextField
import com.example.nextlevel.ui.screens.matrixoffate.inputvalues.presentation.MatrixOfFateInputValuesEffect
import com.example.nextlevel.ui.screens.matrixoffate.inputvalues.presentation.MatrixOfFateInputValuesEvent.BirthDateSelected
import com.example.nextlevel.ui.screens.matrixoffate.inputvalues.presentation.MatrixOfFateInputValuesEvent.CalculateButtonClicked
import com.example.nextlevel.ui.screens.matrixoffate.inputvalues.presentation.MatrixOfFateInputValuesEvent.NameValueChanged
import com.example.nextlevel.ui.screens.matrixoffate.inputvalues.presentation.MatrixOfFateInputValuesEvent.SexChosen
import com.example.nextlevel.ui.screens.matrixoffate.inputvalues.presentation.MatrixOfFateInputValuesState
import com.example.nextlevel.ui.screens.matrixoffate.inputvalues.presentation.MatrixOfFateInputValuesViewModel
import com.example.nextlevel.ui.theme.ContainerColor
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate

@Composable
fun MatrixOfFateInputValuesScreen(navigator: Navigator) {
	val viewModel = hiltViewModel<MatrixOfFateInputValuesViewModel>()
	val state by viewModel.state.collectAsStateWithLifecycle()
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
	Column(
		modifier = Modifier
			.fillMaxWidth()
			.fillMaxHeight(fraction = 0.75f)
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
			modifier = Modifier.padding(horizontal = 16.dp),
			label = stringResource(R.string.common_user_name),
			value = state.userName.value,
			isError = state.userName.isError,
			onNextClicked = {},
			onValueChanged = { onUserNameChanged(it) },
		)
		DateChoosePicker(
			modifier = Modifier
				.padding(horizontal = 16.dp)
				.padding(top = 6.dp),
			date = state.birthDate,
			format = FormatHelper.DATE_MONTH_YEAR,
			label = stringResource(R.string.matrix_of_fate_field_birthdate_hint),
			onValueChanged = { selectedDate -> onBirthDateSelected(selectedDate) },
			isError = state.isBirthDateError,
			selectableDates = SelectableDatesPast()
		)
		SexChoosingMenu(
			modifier = Modifier
				.padding(horizontal = 16.dp)
				.padding(top = 12.dp),
			selectedSex = state.sex,
			isError = state.isSexError,
			onSexChosen = { sex -> onSexChosen(sex) }
		)
		Spacer(
			modifier = Modifier
				.weight(1f, fill = true)
				.heightIn(max = 128.dp, min = 16.dp)
		)
		Box(
			modifier = Modifier
				.fillMaxWidth()
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

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
@Composable
private fun MatrixOfFateInputValuesPreview() {
	val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
	ModalBottomSheet(
		sheetState = sheetState,
		onDismissRequest = {},
		contentWindowInsets = { WindowInsets.ime }
	) {
		MatrixOfFateInputValuesContent(
			state = MatrixOfFateInputValuesState(),
			onDismissRequest = {},
			onUserNameChanged = {},
			onSexChosen = {},
			onCalculateButtonClicked = {},
			onBirthDateSelected = {}
		)
	}
}