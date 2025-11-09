package com.example.tutorplace.ui.screens.tasks.presentation

import com.example.tutorplace.ui.base.BaseViewModel

class TasksViewModel : BaseViewModel<TasksEvent, TasksState, TasksEffect>() {

	private var navigator: TasksNavigator? = null

	fun setNavigator(navigator: TasksNavigator) {
		this.navigator = navigator
	}

	override fun initialState() = TasksState()

	override fun onEvent(event: TasksEvent) = Unit
}