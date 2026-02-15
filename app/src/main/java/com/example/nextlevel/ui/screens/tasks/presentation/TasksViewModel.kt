package com.example.nextlevel.ui.screens.tasks.presentation

import com.example.nextlevel.ui.base.BaseViewModel

class TasksViewModel : BaseViewModel<TasksEvent, TasksState, TasksEffect>() {

	override fun initialState() = TasksState()
	override fun onEvent(event: TasksEvent) = Unit
}