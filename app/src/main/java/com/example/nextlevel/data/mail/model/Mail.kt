package com.example.nextlevel.data.mail.model

data class Mail(
	val id: String,
	val title: String,
	val description: String,
	val type: MailType,
	val isViewed: Boolean,
)