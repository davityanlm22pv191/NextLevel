package com.example.nextlevel.domain.usecases.auth

import com.example.nextlevel.data.auth.AuthService
import com.example.nextlevel.data.credentials.CredentialsStorage
import javax.inject.Inject

class RegisterUseCase @Inject constructor(
	private val authService: AuthService,
	private val credentialsStorage: CredentialsStorage,
) {

	suspend fun execute(
		name: String,
		phoneNumber: String,
		telegram: String,
		email: String,
		password: String,
	): Boolean {
		return authService
			.register(name, phoneNumber, telegram, email, password)
			.onSuccess { token ->
				if (token != null) {
					credentialsStorage.saveToken(token)
				}
			}
			.isSuccess
	}
}