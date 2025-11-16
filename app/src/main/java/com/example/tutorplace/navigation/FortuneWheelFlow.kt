package com.example.tutorplace.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.tutorplace.navigation.Destinations.FortuneWheelFlow
import com.example.tutorplace.ui.screens.fortunewheel.fortunewheel.FortuneWheelScreen
import com.example.tutorplace.ui.screens.fortunewheel.fortunewheelinformation.FortuneWheelInformationScreen

fun NavGraphBuilder.fortuneWheelFlow(navController: NavHostController) {
	navigation(
		startDestination = FortuneWheelFlow.FortuneWheel.route,
		route = FortuneWheelFlow.FLOW_ROUTE
	) {
		composable(route = FortuneWheelFlow.FortuneWheel.route) {
			FortuneWheelScreen(navController)
		}
		composable(FortuneWheelFlow.FortuneWheelInformation.route) {
			FortuneWheelInformationScreen(navController)
		}
	}
}