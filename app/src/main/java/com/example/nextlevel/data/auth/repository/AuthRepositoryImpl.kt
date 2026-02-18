package com.example.nextlevel.data.auth.repository

import com.example.nextlevel.data.auth.service.AuthService
import com.example.nextlevel.data.credentials.CredentialsStorage
import com.example.nextlevel.network.api.ApiClient
import com.example.nextlevel.network.error.NetworkResult
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
	private val apiClient: ApiClient,
	private val authService: AuthService,
	private val credentialsStorage: CredentialsStorage,
) : AuthRepository {

	override suspend fun login(
		email: String,
		password: String,
	): NetworkResult<Boolean> {
		return apiClient
			.call { authService.authorizeWithEmail(email, password) }
			.onSuccess { token -> token?.let { credentialsStorage.saveToken(it) } }
			.map { token -> !token.isNullOrEmpty() }
	}

	override suspend fun register(
		name: String,
		phoneNumber: String,
		telegram: String,
		email: String,
		password: String,
	): NetworkResult<Boolean> {
		return apiClient
			.call { authService.register(name, phoneNumber, telegram, email, password) }
			.onSuccess { token -> token?.let { credentialsStorage.saveToken(it) } }
			.map { token -> !token.isNullOrEmpty() }
	}

	override suspend fun restorePassword(email: String): NetworkResult<Boolean> {
		return apiClient.call { authService.restorePassword(email) }
	}
}