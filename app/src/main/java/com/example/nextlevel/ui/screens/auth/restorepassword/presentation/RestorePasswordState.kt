package com.example.nextlevel.ui.screens.auth.restorepassword.presentation

import com.example.nextlevel.ui.base.BaseState

data class RestorePasswordState(
	val isLoading: Boolean = false,
	val throwable: Throwable? = null,
	val isRestoreButtonEnabled: Boolean = true,
	val email: String = "",
	val isEmailError: Boolean = false,
	val isEmailSent: Boolean = false,
	val timerInSeconds: Int = 0
) : BaseState