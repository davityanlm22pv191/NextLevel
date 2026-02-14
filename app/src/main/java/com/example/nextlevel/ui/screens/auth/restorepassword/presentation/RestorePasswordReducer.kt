package com.example.nextlevel.ui.screens.auth.restorepassword.presentation

import com.example.nextlevel.ui.base.BaseReducer
import com.example.nextlevel.ui.screens.auth.restorepassword.presentation.RestorePasswordEvent.EmailChanged
import com.example.nextlevel.ui.screens.auth.restorepassword.presentation.RestorePasswordEvent.EmailErrorSending
import com.example.nextlevel.ui.screens.auth.restorepassword.presentation.RestorePasswordEvent.EmailIsNotValid
import com.example.nextlevel.ui.screens.auth.restorepassword.presentation.RestorePasswordEvent.EmailSending
import com.example.nextlevel.ui.screens.auth.restorepassword.presentation.RestorePasswordEvent.EmailSent
import com.example.nextlevel.ui.screens.auth.restorepassword.presentation.RestorePasswordEvent.RestoreClicked
import com.example.nextlevel.ui.screens.auth.restorepassword.presentation.RestorePasswordEvent.RetrySendClicked
import com.example.nextlevel.ui.screens.auth.restorepassword.presentation.RestorePasswordEvent.RetrySendTimeUpdated

object RestorePasswordReducer : BaseReducer<RestorePasswordState, RestorePasswordEvent> {

	override fun reduce(
		oldState: RestorePasswordState,
		event: RestorePasswordEvent
	): RestorePasswordState = when (event) {
		is EmailChanged -> reduceEmailChanged(oldState, event)
		is EmailSent -> reduceEmailSent(oldState)
		is EmailSending -> oldState.copy(isLoading = true)
		is EmailIsNotValid -> oldState.copy(isEmailError = true)
		is RetrySendTimeUpdated -> oldState.copy(timerInSeconds = event.seconds)
		is EmailErrorSending -> oldState.copy(isLoading = false, throwable = null)
		is RestoreClicked,
		is RetrySendClicked -> oldState
	}

	private fun reduceEmailChanged(
		oldState: RestorePasswordState,
		event: EmailChanged
	): RestorePasswordState = oldState.copy(
		email = event.enteredEmail.trim(),
		isEmailError = false
	)

	private fun reduceEmailSent(
		oldState: RestorePasswordState
	): RestorePasswordState = oldState.copy(
		isEmailSent = true,
		isLoading = false
	)
}