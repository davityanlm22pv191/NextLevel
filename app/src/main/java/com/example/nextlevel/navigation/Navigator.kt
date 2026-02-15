package com.example.nextlevel.navigation

import android.content.Context
import android.os.Build
import androidx.navigation3.runtime.NavKey
import com.example.nextlevel.helpers.VibrationHelper

class Navigator(
	val state: NavigationState,
	private val context: Context,
) {

	fun navigateAndClearBackStack(route: NavKey) {
		state.backStacks[state.topLevelRoute]?.add(route)
		val currentStack = state.backStacks[state.topLevelRoute]
			?: error("Stack for ${state.topLevelRoute} not found")
		while (currentStack.size > 1) {
			currentStack.removeAt(0)
		}
	}

	fun navigate(vararg routes: NavKey) {
		routes.forEach { route ->
			val isClickedOnTab = route in state.backStacks.keys
			if (isClickedOnTab) {
				val isClickedOnAlreadyActiveTab = state.topLevelRoute == route
				if (isClickedOnAlreadyActiveTab) {
					navigateAndClearBackStack(route)
				} else {
					state.topLevelRoute = route
				}
			} else {
				state.backStacks[state.topLevelRoute]?.add(route)
			}
		}
	}

	fun goBack() {
		val currentStack = state.backStacks[state.topLevelRoute]
			?: error("Stack for ${state.topLevelRoute} not found")
		val currentRoute = currentStack.last()

		if (currentRoute == state.topLevelRoute) {
			state.topLevelRoute = state.startRoute
		} else {
			currentStack.removeLastOrNull()
		}
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) VibrationHelper.performTick(context)
	}
}