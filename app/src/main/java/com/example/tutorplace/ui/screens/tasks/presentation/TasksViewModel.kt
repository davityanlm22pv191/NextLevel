package com.example.tutorplace.ui.screens.tasks.presentation

import com.example.tutorplace.ui.base.BaseViewModel

class TasksViewModel : BaseViewModel<TasksEvent, TasksState, TasksEffect>() {

	override fun initialState() = TasksState()
	override fun onEvent(event: TasksEvent) = Unit
}