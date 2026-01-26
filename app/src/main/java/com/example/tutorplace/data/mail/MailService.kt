package com.example.tutorplace.data.mail

import com.example.tutorplace.data.common.ItemsResponse
import com.example.tutorplace.data.mail.model.Mail
import retrofit2.Response
import retrofit2.http.GET

interface MailService {
	private companion object {
		const val MAIL_ENDPOINT = "mail_mock_endpoint"
	}

	@GET(MAIL_ENDPOINT)
	suspend fun getMails(): Response<ItemsResponse<Mail>>
}