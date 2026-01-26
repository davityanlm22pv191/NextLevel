package com.example.tutorplace.data.mail.storage

import com.example.tutorplace.data.mail.model.Mail
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

class MailStorageImpl @Inject constructor() : MailStorage {

	private val _mails = MutableStateFlow<List<Mail>>(emptyList())

	override val mails: StateFlow<List<Mail>>
		get() = _mails

	override fun setMails(mails: List<Mail>) {
		_mails.value = mails
	}
}