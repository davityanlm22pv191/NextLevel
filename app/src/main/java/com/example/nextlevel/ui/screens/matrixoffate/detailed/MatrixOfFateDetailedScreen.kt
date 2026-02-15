package com.example.nextlevel.ui.screens.matrixoffate.detailed

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.example.nextlevel.navigation.Navigator
import com.example.nextlevel.ui.screens.matrixoffate.detailed.model.MatrixOfFateDetailedParams
import com.example.nextlevel.ui.screens.matrixoffate.detailed.presentation.MatrixOfFateDetailedEffect
import com.example.nextlevel.ui.screens.matrixoffate.detailed.presentation.MatrixOfFateDetailedState
import com.example.nextlevel.ui.screens.matrixoffate.detailed.presentation.MatrixOfFateDetailedViewModel
import kotlinx.coroutines.flow.Flow

@Composable
fun MatrixOfFateDetailedScreen(params: MatrixOfFateDetailedParams, navigator: Navigator) {
	val viewModel = hiltViewModel<MatrixOfFateDetailedViewModel>()
	val state by viewModel.state.collectAsState()
	CollectEffects(viewModel.effect, navigator)
	MatrixOfFateDetailedContent(state)
}

@Composable
private fun MatrixOfFateDetailedContent(state: MatrixOfFateDetailedState) {

}

@Composable
private fun CollectEffects(effect: Flow<MatrixOfFateDetailedEffect>, navigator: Navigator) {
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
private fun MatrixOfFateDetailedPreview() {
	MatrixOfFateDetailedContent(
		state = MatrixOfFateDetailedState
	)
}