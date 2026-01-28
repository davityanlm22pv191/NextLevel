package com.example.tutorplace.data.mail.storage

import com.example.tutorplace.data.mail.model.Mail
import kotlinx.coroutines.flow.StateFlow

interface MailStorage {

	val mails: StateFlow<List<Mail>?>

	fun setMails(mails: List<Mail>)

	fun clear()
}