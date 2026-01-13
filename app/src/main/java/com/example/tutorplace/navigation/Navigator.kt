package com.example.tutorplace.navigation

import androidx.navigation3.runtime.NavKey

class Navigator(val state: NavigationState) {

	fun navigate(route: NavKey) {
		if (route in state.backStacks.keys) {
			state.topLevelRoute = route
		} else {
			state.backStacks[state.topLevelRoute]?.add(route)
		}
	}

	fun navigateAndClearBackStack(route: NavKey) {
		state.backStacks[state.topLevelRoute]?.add(route)
		val currentStack = state.backStacks[state.topLevelRoute]
			?: error("Stack for ${state.topLevelRoute} not found")
		while (currentStack.size > 1) {
			currentStack.removeFirst()
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
	}
}