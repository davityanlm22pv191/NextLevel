package com.example.tutorplace.ui.screens.matrixoffate.inputvalues

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.example.tutorplace.navigation.Navigator
import com.example.tutorplace.ui.screens.matrixoffate.inputvalues.model.MatrixOfFateInputValuesParams
import com.example.tutorplace.ui.screens.matrixoffate.inputvalues.presentation.MatrixOfFateInputValuesEffect
import com.example.tutorplace.ui.screens.matrixoffate.inputvalues.presentation.MatrixOfFateInputValuesState
import com.example.tutorplace.ui.screens.matrixoffate.inputvalues.presentation.MatrixOfFateInputValuesViewModel
import kotlinx.coroutines.flow.Flow

@Composable
fun MatrixOfFateInputValuesScreen(params: MatrixOfFateInputValuesParams, navigator: Navigator) {
	val viewModel = hiltViewModel<MatrixOfFateInputValuesViewModel>()
	val state by viewModel.state.collectAsState()
	CollectEffects(viewModel.effect, navigator)
	MatrixOfFateInputValuesContent(state)
}

@Composable
private fun MatrixOfFateInputValuesContent(state: MatrixOfFateInputValuesState) {

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
		state = MatrixOfFateInputValuesState
	)
}