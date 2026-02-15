package com.example.nextlevel.ui.common.textfield

data class TextFieldState(
	var value: String = "",
	var isError: Boolean = false,
)

fun TextFieldState.set(value: String) = copy(value = value, isError = false)

fun TextFieldState.error() = copy(isError = false)