package com.example.tutorplace.ui.screens.coursedetailed

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.example.tutorplace.ui.screens.coursedetailed.presentation.CourseDetailedState
import com.example.tutorplace.ui.screens.coursedetailed.presentation.CourseDetailedViewModel

@Composable
fun CourseDetailedScreen(navController: NavHostController) {
	val viewModel = hiltViewModel<CourseDetailedViewModel>()
	val state by viewModel.state.collectAsStateWithLifecycle()
	CourseDetailedScreen(state)
}

@Composable
private fun CourseDetailedScreen(
	state: CourseDetailedState,
) {
}

@Preview
@Composable
private fun CourseDetailedScreenPreview() {
	CourseDetailedScreen(
		state = CourseDetailedState()
	)
}