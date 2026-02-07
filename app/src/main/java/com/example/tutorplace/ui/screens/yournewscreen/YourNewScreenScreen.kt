package com.example.tutorplace.ui.screens.yournewscreen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.example.tutorplace.navigation.Navigator
import com.example.tutorplace.ui.screens.yournewscreen.model.YourNewScreenParams
import com.example.tutorplace.ui.screens.yournewscreen.presentation.YourNewScreenEffect
import com.example.tutorplace.ui.screens.yournewscreen.presentation.YourNewScreenState
import com.example.tutorplace.ui.screens.yournewscreen.presentation.YourNewScreenViewModel
import kotlinx.coroutines.flow.Flow

@Composable
fun YourNewScreenScreen(params: YourNewScreenParams, navigator: Navigator) {
	val viewModel = hiltViewModel<YourNewScreenViewModel>()
	val state by viewModel.state.collectAsState()
	CollectEffects(viewModel.effect, navigator)
	YourNewScreenContent(state)
}

@Composable
private fun YourNewScreenContent(state: YourNewScreenState) {

}

@Composable
private fun CollectEffects(effect: Flow<YourNewScreenEffect>, navigator: Navigator) {
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
private fun YourNewScreenPreview() {
	YourNewScreenContent(
		state = YourNewScreenState
	)
}