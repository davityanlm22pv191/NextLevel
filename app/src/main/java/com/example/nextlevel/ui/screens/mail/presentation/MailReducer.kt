package com.example.nextlevel.ui.screens.mail.presentation

import com.example.nextlevel.domain.model.DataInfo
import com.example.nextlevel.ui.base.BaseReducer

object MailReducer : BaseReducer<MailState, MailEvent> {

	override fun reduce(
		oldState: MailState,
		event: MailEvent
	): MailState {
		return when (event) {
			is MailEvent.MailsFailed -> oldState.copy(mails = DataInfo.Error)
			is MailEvent.MailsLoading -> oldState.copy(mails = DataInfo.Loading)
			is MailEvent.MailsLoaded -> oldState.copy(mails = DataInfo.Success(event.mails))
			is MailEvent.UnreadMessageCountLoaded -> oldState.copy(
				skeletonItemsCount = DataInfo.Success(event.count)
			)
			else -> oldState
		}
	}
}