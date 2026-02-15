package com.example.nextlevel.domain.usecases.mail

import com.example.nextlevel.data.mail.MailService
import com.example.nextlevel.data.mail.storage.MailStorage
import javax.inject.Inject

class UpdateMailsUseCase @Inject constructor(
	private val mailService: MailService,
	private val mailStorage: MailStorage,
) {
	suspend fun execute(): Result<Unit> {
		mailStorage.clear()
		val response = mailService.getMails()
		return if (response.isSuccessful) {
			response.body()?.items.orEmpty().let { mails ->
				mailStorage.setMails(mails)
			}
			Result.success(Unit)
		} else {
			Result.failure(Throwable(response.message()))
		}
	}
}