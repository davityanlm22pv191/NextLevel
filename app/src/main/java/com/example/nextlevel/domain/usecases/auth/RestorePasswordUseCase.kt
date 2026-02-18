package com.example.nextlevel.domain.usecases.auth

import com.example.nextlevel.data.auth.repository.AuthRepository
import com.example.nextlevel.network.error.NetworkResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RestorePasswordUseCase @Inject constructor(
	private val authRepository: AuthRepository
) {

	suspend fun execute(email: String): NetworkResult<Boolean> = withContext(Dispatchers.IO) {
		return@withContext authRepository.restorePassword(email)
	}
}