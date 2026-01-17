package com.example.tutorplace.ui.screens.fortunewheel.fortunewheel

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.example.tutorplace.navigation.Navigator
import com.example.tutorplace.ui.screens.fortunewheel.fortunewheel.presentation.FortuneWheelViewModel

@Composable
fun FortuneWheelScreen(navigator: Navigator) {
	val viewModel = hiltViewModel<FortuneWheelViewModel>()
	FortuneWheelContent()
}

@Composable
private fun FortuneWheelContent() {
	Scaffold(
		modifier = Modifier.fillMaxSize()
	) { paddingValues ->
		Text(
			modifier = Modifier
				.fillMaxSize()
				.padding(paddingValues)
				.padding(vertical = 100.dp),
			text = "Это главный экран фортуны\nОн ещё не готов",
			textAlign = TextAlign.Center
		)
	}
}

@Preview
@Composable
fun FortuneWheelContentPreview() {
	FortuneWheelContent()
}
