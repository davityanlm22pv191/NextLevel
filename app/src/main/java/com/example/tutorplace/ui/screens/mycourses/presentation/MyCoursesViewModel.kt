package com.example.tutorplace.ui.screens.mycourses.presentation

import com.example.tutorplace.ui.base.BaseViewModel

class MyCoursesViewModel : BaseViewModel<MyCoursesEvent, MyCoursesState, MyCoursesEffect>() {

	override fun initialState() = MyCoursesState()
	override fun onEvent(event: MyCoursesEvent) = Unit
}