package com.example.tutorplace.ui.screens.mail.presentation

import com.example.tutorplace.domain.model.DataInfo
import com.example.tutorplace.ui.base.BaseReducer

object MailReducer : BaseReducer<MailState, MailEvent> {
	override fun reduce(
		oldState: MailState,
		event: MailEvent
	): MailState {
		return when (event) {
			is MailEvent.MailsFailed -> oldState.copy(mails = DataInfo.Error(event.throwable))
			is MailEvent.MailsLoading -> oldState.copy(mails = DataInfo.Loading)
			is MailEvent.MailsLoaded -> oldState.copy(mails = DataInfo.Success(event.mails))
			is MailEvent.UnreadMessageCountLoaded -> oldState.copy(
				skeletonItemsCount = DataInfo.Success(event.count)
			)
			else -> oldState
		}
	}
}