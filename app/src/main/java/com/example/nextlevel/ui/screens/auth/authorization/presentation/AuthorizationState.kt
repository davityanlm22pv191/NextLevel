package com.example.nextlevel.ui.screens.auth.authorization.presentation

import com.example.nextlevel.ui.base.BaseState

data class AuthorizationState(
	val isLoading: Boolean = false,
	val throwable: Throwable? = null,
	val email: String = "",
	val isEmailError: Boolean = false,
	val password: String = "",
	val isPasswordError: Boolean = false,
) : BaseState