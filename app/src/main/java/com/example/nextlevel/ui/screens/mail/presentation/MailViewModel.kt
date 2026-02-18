package com.example.nextlevel.ui.screens.mail.presentation

import androidx.lifecycle.viewModelScope
import com.example.nextlevel.data.mail.storage.MailStorage
import com.example.nextlevel.data.profile.storage.ProfileStorage
import com.example.nextlevel.domain.usecases.mail.UpdateMailsUseCase
import com.example.nextlevel.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MailViewModel @Inject constructor(
	private val mailStorage: MailStorage,
	private val updateMailsUseCase: UpdateMailsUseCase,
	private val profileStorage: ProfileStorage,
) : BaseViewModel<MailEvent, MailState, MailEffect>() {

	init {
		getUnreadMessageCount()
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
			updateMailsUseCase
				.execute()
				.onError { throwable -> onEvent(MailEvent.MailsFailed(throwable.asThrowable())) }
		}
	}

	private fun collectMails() {
		viewModelScope.launch {
			mailStorage.mails.collect { mails ->
				mails?.let { onEvent(MailEvent.MailsLoaded(mails)) }
			}
		}
	}

	private fun getUnreadMessageCount() {
		viewModelScope.launch {
			profileStorage.profileShortInfo.collect { value ->
				value?.unreadMessageCount?.let { count ->
					onEvent(MailEvent.UnreadMessageCountLoaded(count))
				}
			}
		}
	}
}