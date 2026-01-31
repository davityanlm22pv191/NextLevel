package com.example.tutorplace.ui.screens.mycourses

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.example.tutorplace.navigation.Navigator
import com.example.tutorplace.ui.screens.mycourses.presentation.MyCoursesViewModel
import com.example.tutorplace.ui.theme.Black16
import com.example.tutorplace.ui.theme.ScreenColor
import com.example.tutorplace.ui.theme.Typography

@Composable
fun MyCoursesScreen(navigator: Navigator) {
	val viewModel = hiltViewModel<MyCoursesViewModel>()
	MyCoursesScreen()
}

@Composable
private fun MyCoursesScreen() {
	Text(
		modifier = Modifier
			.fillMaxSize()
			.background(ScreenColor)
			.padding(vertical = 100.dp),
		text = "Это стартовый экрана таба Мои курсы\n\nОн ещё не сделан",
		textAlign = TextAlign.Center,
		style = Typography.titleLarge.copy(Black16)
	)
}

@Preview
@Composable
private fun MyCoursesPreview() {
	MyCoursesScreen()
}