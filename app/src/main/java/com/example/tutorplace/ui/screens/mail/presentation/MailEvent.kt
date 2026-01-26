package com.example.tutorplace.ui.screens.mail.presentation

import com.example.tutorplace.data.mail.model.Mail
import com.example.tutorplace.data.profile.model.ProfileShortInfo
import com.example.tutorplace.ui.base.BaseEvent

interface MailEvent : BaseEvent {
	data object MailsLoading : MailEvent
	data class MailsLoaded(val mails: List<Mail>) : MailEvent
	data class MailsFailed(val throwable: Throwable) : MailEvent
}