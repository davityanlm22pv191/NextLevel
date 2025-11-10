package com.example.tutorplace.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.dialog
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.tutorplace.ui.screens.main.MainScreen
import com.example.tutorplace.ui.screens.onboarding.OnboardingScreen

@Composable
fun AppNavigationGraph(startDestination: String) {
	val navController = rememberNavController()
	NavHost(navController, startDestination = startDestination) {
		authorizationFlow(navController)
		mainScreen(navController)
		dialog(Destinations.Onboarding.route) { OnboardingScreen(navController) }
	}
}

private fun NavGraphBuilder.mainScreen(navController: NavHostController) {
	composable(
		route = Destinations.MainScreen.DEFAULT_ROUTE,
		arguments = listOf(
			navArgument(name = "isShouldShowOnboarding") {
				type = NavType.BoolType
				defaultValue = false
				nullable = false
			}
		)
	) {
		val params = Destinations.MainScreen.MainScreenParams(
			isShouldShowOnboarding = requireNotNull(it.arguments?.getBoolean("isShouldShowOnboarding"))
		)
		MainScreen(navController, params)
	}
}