package com.example.tutorplace.ui.screens.auth.authorization.presentation

import com.example.tutorplace.ui.base.BaseEvent

sealed interface AuthorizationEvent : BaseEvent {
	data class EmailChanged(val enteredEmail: String) : AuthorizationEvent
	data class PasswordChanged(val enteredPassword: String) : AuthorizationEvent
	data object CheckEnteredValues : AuthorizationEvent
	data object EnterToProfileRequested : AuthorizationEvent

	data object RestorePasswordClicked : AuthorizationEvent
	data object RegistrationClicked : AuthorizationEvent
	data object SupportClicked : AuthorizationEvent
	data object YandexAuthorizationClicked : AuthorizationEvent
	data object EnterClicked : AuthorizationEvent
}