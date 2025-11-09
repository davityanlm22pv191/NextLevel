package com.example.tutorplace.ui.screens.mycourses.presentation

import com.example.tutorplace.ui.base.BaseViewModel

class MyCoursesViewModel : BaseViewModel<MyCoursesEvent, MyCoursesState, MyCoursesEffect>() {

	private var navigator: MyCoursesNavigator? = null

	fun attachNavigator(navigator: MyCoursesNavigator) {
		this.navigator = navigator
	}

	override fun initialState() = MyCoursesState()

	override fun onEvent(event: MyCoursesEvent) = Unit
}