package com.example.nextlevel.data.mail.storage

import com.example.nextlevel.data.mail.model.Mail
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

class MailStorageImpl @Inject constructor() : MailStorage {

	private val _mails = MutableStateFlow<List<Mail>?>(null)

	override val mails: StateFlow<List<Mail>?>
		get() = _mails

	override fun setMails(mails: List<Mail>) {
		_mails.value = mails
	}

	override fun clear() {
		_mails.value = null
	}
}