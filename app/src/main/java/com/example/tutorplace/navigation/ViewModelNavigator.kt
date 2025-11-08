package com.example.tutorplace.navigation

import androidx.navigation.NavHostController

abstract class ViewModelNavigator(
	private val navController: NavHostController
) {
	open fun exit() = navController.popBackStack()
}