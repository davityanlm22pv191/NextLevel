package com.example.tutorplace.navigation

abstract class ViewModelNavigator(
	private val navigator: Navigator
) {
	open fun exit() = navigator.goBack()
}