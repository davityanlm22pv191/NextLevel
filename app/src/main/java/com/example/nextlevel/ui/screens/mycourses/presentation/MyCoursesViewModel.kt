package com.example.nextlevel.ui.screens.mycourses.presentation

import com.example.nextlevel.ui.base.BaseViewModel

class MyCoursesViewModel : BaseViewModel<MyCoursesEvent, MyCoursesState, MyCoursesEffect>() {

	override fun initialState() = MyCoursesState()
	override fun onEvent(event: MyCoursesEvent) = Unit
}