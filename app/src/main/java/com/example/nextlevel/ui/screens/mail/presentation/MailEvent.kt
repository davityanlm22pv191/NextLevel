package com.example.nextlevel.ui.screens.mail.presentation

import com.example.nextlevel.data.mail.model.Mail
import com.example.nextlevel.ui.base.BaseEvent

interface MailEvent : BaseEvent {
	data object MailsLoading : MailEvent
	data class MailsLoaded(val mails: List<Mail>) : MailEvent
	data class MailsFailed(val throwable: Throwable) : MailEvent

	data class UnreadMessageCountLoaded(val count: Int) : MailEvent
}