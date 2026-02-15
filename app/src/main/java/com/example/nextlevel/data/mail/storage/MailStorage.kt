package com.example.nextlevel.data.mail.storage

import com.example.nextlevel.data.mail.model.Mail
import kotlinx.coroutines.flow.StateFlow

interface MailStorage {

	val mails: StateFlow<List<Mail>?>

	fun setMails(mails: List<Mail>)

	fun clear()
}