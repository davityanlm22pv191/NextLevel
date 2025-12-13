package com.example.tutorplace.navigation.tabs

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.tutorplace.navigation.Destinations
import com.example.tutorplace.navigation.fortuneWheelFlow
import com.example.tutorplace.ui.screens.coursedetailed.CourseDetailedScreen
import com.example.tutorplace.ui.screens.coursedetailed.model.CourseDetailedParams

@Composable
fun TabsNavHost(modifier: Modifier, navController: NavHostController, startDestination: String) {
	NavHost(
		modifier = modifier,
		navController = navController,
		startDestination = startDestination,
	) {
		catalogGraph(navController)
		myCoursesGraph(navController)
		homeGraph(navController)
		tasksGraph(navController)

		fortuneWheelFlow(navController)
		courseDetailed(navController)
	}
}

private fun NavGraphBuilder.courseDetailed(navController: NavHostController) {
	composable(
		route = Destinations.CourseDetailed.DEFAULT_ROUTE,
		arguments = listOf(
			navArgument(name = "courseId") {
				type = NavType.StringType
				defaultValue = ""
				nullable = false
			}
		)
	) {
		val params = CourseDetailedParams(
			courseId = requireNotNull(it.arguments?.getString("courseId"))
		)
		CourseDetailedScreen(navController, params)
	}
}