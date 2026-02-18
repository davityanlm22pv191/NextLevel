package com.example.nextlevel.ui.screens.auth.restorepassword.presentation

import androidx.lifecycle.viewModelScope
import com.example.nextlevel.domain.usecases.auth.RestorePasswordUseCase
import com.example.nextlevel.helpers.FormatHelper
import com.example.nextlevel.ui.base.BaseViewModel
import com.example.nextlevel.ui.screens.auth.restorepassword.presentation.RestorePasswordEvent.EmailErrorSending
import com.example.nextlevel.ui.screens.auth.restorepassword.presentation.RestorePasswordEvent.EmailIsNotValid
import com.example.nextlevel.ui.screens.auth.restorepassword.presentation.RestorePasswordEvent.EmailSending
import com.example.nextlevel.ui.screens.auth.restorepassword.presentation.RestorePasswordEvent.EmailSent
import com.example.nextlevel.ui.screens.auth.restorepassword.presentation.RestorePasswordEvent.RetrySendTimeUpdated
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RestorePasswordViewModel @Inject constructor(
	private val restorePasswordUseCase: RestorePasswordUseCase,
) : BaseViewModel<RestorePasswordEvent, RestorePasswordState, RestorePasswordEffect>() {

	private val timer = MutableStateFlow(60)
	private var timerJob: Job? = null

	override fun initialState() = RestorePasswordState()
	override fun onEvent(event: RestorePasswordEvent) = when (event) {
		is RestorePasswordEvent.EmailChanged,
		is EmailErrorSending,
		is EmailIsNotValid,
		is RetrySendTimeUpdated,
		is EmailSending,
		is EmailSent -> setState(RestorePasswordReducer.reduce(state.value, event))
		is RestorePasswordEvent.RestoreClicked -> sendNewPasswordToEmail()
		is RestorePasswordEvent.RetrySendClicked -> sendNewPasswordToEmail()
	}

	private fun sendNewPasswordToEmail() {
		val isEmailValid = FormatHelper.isValidEmail(state.value.email.trim())
		if (!isEmailValid) {
			setState(RestorePasswordReducer.reduce(state.value, EmailIsNotValid))
			return
		}
		viewModelScope.launch {
			setState(RestorePasswordReducer.reduce(state.value, EmailSending))
			restorePasswordUseCase
				.execute(state.value.email)
				.onSuccess {
					setState(RestorePasswordReducer.reduce(state.value, EmailSent))
					startTimer()
				}
				.onError {
					setState(RestorePasswordReducer.reduce(state.value, EmailErrorSending))
				}
		}
	}

	private fun startTimer() {
		timerJob?.cancel()
		timer.value = 60
		timerJob = viewModelScope.launch {
			while (timer.value >= 0) {
				setState(
					RestorePasswordReducer.reduce(state.value, RetrySendTimeUpdated(timer.value))
				)
				delay(1000)
				timer.value--
			}

			timerJob?.cancel()
			timer.value = 0
		}
	}

	override fun onCleared() {
		super.onCleared()
		timerJob?.cancel()
	}
}