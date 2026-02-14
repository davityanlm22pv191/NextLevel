package com.example.nextlevel.ui.screens.tasks.presentation

import com.example.nextlevel.ui.base.BaseState

data class TasksState(
	val isLoading: Boolean = false,
	val throwable: Throwable? = null
) : BaseState