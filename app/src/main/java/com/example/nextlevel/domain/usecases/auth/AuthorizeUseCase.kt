package com.example.nextlevel.domain.usecases.auth

import com.example.nextlevel.data.auth.service.AuthService
import com.example.nextlevel.data.credentials.CredentialsStorage
import com.example.nextlevel.network.api.ApiClient
import com.example.nextlevel.network.error.NetworkResult
import javax.inject.Inject

class AuthorizeUseCase @Inject constructor(
	private val apiClient: ApiClient,
	private val authService: AuthService,
	private val credentialsStorage: CredentialsStorage
) {
	suspend fun execute(email: String, password: String): NetworkResult<Unit> {
		return apiClient
			.call { authService.authorizeWithEmail(email, password) }
			.onSuccess { token -> token?.let { credentialsStorage.saveToken(token) } }
			.ignoreElement()
	}
}