package com.example.tutorplace.ui.screens.tasks

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.tutorplace.ui.screens.tasks.presentation.TasksNavigator
import com.example.tutorplace.ui.screens.tasks.presentation.TasksViewModel

@Composable
fun TasksScreen(navController: NavHostController) {
	val viewModel = hiltViewModel<TasksViewModel>()
	LaunchedEffect(Unit) { viewModel.setNavigator(TasksNavigator(navController)) }
	TasksScreen()
}

@Composable
private fun TasksScreen() {
	Box(
		Modifier.fillMaxSize()
	) {
		Text(text = "This is Tasks Screen")
	}
}

@Preview
@Composable
private fun TasksPreview() {
	TasksScreen()
}