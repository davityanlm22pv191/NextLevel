package com.example.nextlevel.ui.screens.stub

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.nextlevel.ui.theme.Black16
import com.example.nextlevel.ui.theme.ScreenColor
import com.example.nextlevel.ui.theme.Typography

@Composable
fun StubScreen() {
	Scaffold(
		modifier = Modifier.fillMaxSize(),
		containerColor = ScreenColor
	) { paddingValues ->
		Box(
			modifier = Modifier
				.fillMaxSize()
				.padding(paddingValues),
			contentAlignment = Alignment.Center
		) {
			Text(
				"Этот функционал ещё не реализован",
				style = Typography.titleLarge.copy(Black16)
			)
		}
	}
}

@Preview
@Composable
private fun StubPreview() {
	StubScreen()
}