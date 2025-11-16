package com.example.tutorplace.ui.screens.fortunewheel.fortunewheel

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.tutorplace.ui.screens.fortunewheel.fortunewheel.presentation.FortuneWheelNavigator
import com.example.tutorplace.ui.screens.fortunewheel.fortunewheel.presentation.FortuneWheelViewModel

@Composable
fun FortuneWheelScreen(navController: NavHostController) {
	val viewModel = hiltViewModel<FortuneWheelViewModel>()
	val state = viewModel.state.collectAsState()
	LaunchedEffect(Unit) { viewModel.attachNavigator(FortuneWheelNavigator(navController)) }
	FortuneWheelScreen()
}

@Composable
private fun FortuneWheelScreen() {
	Scaffold(
		modifier = Modifier.fillMaxSize()
	) { paddingValues ->
		Text(
			modifier = Modifier
				.fillMaxSize()
				.padding(paddingValues),
			text = "Fortune Wheel Screen",
			textAlign = TextAlign.Center
		)
	}
}

@Preview
@Composable
fun FortuneWheelScreenPreview() {
	FortuneWheelScreen()
}
