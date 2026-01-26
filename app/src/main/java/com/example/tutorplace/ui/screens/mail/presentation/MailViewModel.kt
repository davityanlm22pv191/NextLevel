package com.example.tutorplace.ui.screens.mail.presentation

import androidx.lifecycle.viewModelScope
import com.example.tutorplace.data.mail.storage.MailStorage
import com.example.tutorplace.domain.usecases.mail.GetMailsUseCase
import com.example.tutorplace.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MailViewModel @Inject constructor(
	private val mailStorage: MailStorage,
	private val getMailsUseCase: GetMailsUseCase,
) : BaseViewModel<MailEvent, MailState, MailEffect>() {

	init {
		updateMails()
		collectMails()
	}

	override fun initialState() = MailState()

	override fun onEvent(event: MailEvent) {
		setState(MailReducer.reduce(state.value, event))
	}

	private fun updateMails() {
		viewModelScope.launch {
			onEvent(MailEvent.MailsLoading)
			getMailsUseCase
				.execute()
				.onFailure { throwable -> onEvent(MailEvent.MailsFailed(throwable)) }
		}
	}

	private fun collectMails() {
		viewModelScope.launch {
			mailStorage.mails.collect { mails ->
				onEvent(MailEvent.MailsLoaded(mails))
			}
		}
	}
}