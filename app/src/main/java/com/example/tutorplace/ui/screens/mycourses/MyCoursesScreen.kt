package com.example.tutorplace.ui.screens.mycourses

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.example.tutorplace.navigation.Navigator

import com.example.tutorplace.ui.screens.mycourses.presentation.MyCoursesNavigator
import com.example.tutorplace.ui.screens.mycourses.presentation.MyCoursesViewModel

@Composable
fun MyCoursesScreen(navigator: Navigator) {
	val viewModel = hiltViewModel<MyCoursesViewModel>()
	LaunchedEffect(Unit) { viewModel.attachNavigator(MyCoursesNavigator(navigator)) }
	MyCoursesScreen()
}

@Composable
private fun MyCoursesScreen() {
	Box(
		Modifier.fillMaxSize()
	) {
		Text(text = "This is My Courses Screen")
	}
}

@Preview
@Composable
private fun MyCoursesPreview() {
	MyCoursesScreen()
}