package com.example.nextlevel.ui.screens.mycourses.presentation

import com.example.nextlevel.ui.base.BaseState

data class MyCoursesState(
	val isLoading: Boolean = false,
	val throwable: Throwable? = null
) : BaseState