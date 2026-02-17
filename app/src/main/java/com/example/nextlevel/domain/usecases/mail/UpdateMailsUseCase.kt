package com.example.nextlevel.domain.usecases.mail

import com.example.nextlevel.data.mail.MailService
import com.example.nextlevel.data.mail.storage.MailStorage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class UpdateMailsUseCase @Inject constructor(
	private val mailService: MailService,
	private val mailStorage: MailStorage,
) {
	suspend fun execute(): Result<Unit> = withContext(Dispatchers.IO) {
		try {
			mailStorage.clear()
			val response = mailService.getMails()
			if (response.isSuccessful) {
				response.body()?.items.orEmpty().let { mails ->
					mailStorage.setMails(mails)
				}
				Result.success(Unit)
			} else {
				Result.failure(Throwable(response.message()))
			}
		} catch (e: Exception) {
			Result.failure(e)
		}
	}
}