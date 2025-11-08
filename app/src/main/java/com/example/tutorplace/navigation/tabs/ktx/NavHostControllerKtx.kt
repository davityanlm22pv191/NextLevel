package com.example.tutorplace.navigation.tabs.ktx

import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import com.example.tutorplace.navigation.Destinations

fun NavHostController.switchTab(route: String) {
	navigate(route) {
		popUpTo(graph.findStartDestination().id) { saveState = true }
		launchSingleTop = true
		restoreState = true
	}
}

fun NavHostController.navigateTo(destination: Destinations) {
	navigate(destination.route)
}