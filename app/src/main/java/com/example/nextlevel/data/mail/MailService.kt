package com.example.nextlevel.data.mail

import com.example.nextlevel.data.common.ItemsResponse
import com.example.nextlevel.data.mail.model.Mail
import retrofit2.Response
import retrofit2.http.GET

interface MailService {

	private companion object {
		const val MAIL_ENDPOINT = "mails"
	}

	@GET(MAIL_ENDPOINT)
	suspend fun getMails(): Response<ItemsResponse<Mail>>
}