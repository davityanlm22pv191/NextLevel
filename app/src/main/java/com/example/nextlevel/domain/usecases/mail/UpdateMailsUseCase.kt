package com.example.nextlevel.domain.usecases.mail

import com.example.nextlevel.data.mail.MailService
import com.example.nextlevel.data.mail.storage.MailStorage
import com.example.nextlevel.network.api.ApiClient
import com.example.nextlevel.network.error.NetworkResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class UpdateMailsUseCase @Inject constructor(
	private val apiClient: ApiClient,
	private val mailService: MailService,
	private val mailStorage: MailStorage,
) {
	suspend fun execute(): NetworkResult<Unit> = withContext(Dispatchers.IO) {
		mailStorage.clear()
		return@withContext apiClient
			.call { mailService.getMails() }
			.onSuccess { mails -> mailStorage.setMails(mails.items) }
			.ignoreElement()
	}
}