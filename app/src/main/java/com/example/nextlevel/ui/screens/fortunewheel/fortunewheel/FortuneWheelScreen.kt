package com.example.nextlevel.ui.screens.fortunewheel.fortunewheel

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.example.nextlevel.navigation.Navigator
import com.example.nextlevel.ui.screens.fortunewheel.fortunewheel.presentation.FortuneWheelViewModel
import com.example.nextlevel.ui.screens.stub.StubScreen

@Composable
fun FortuneWheelScreen(navigator: Navigator) {
	val viewModel = hiltViewModel<FortuneWheelViewModel>()
	FortuneWheelContent()
}

@Composable
private fun FortuneWheelContent() {
	StubScreen()
}

@Preview
@Composable
fun FortuneWheelContentPreview() {
	FortuneWheelContent()
}
