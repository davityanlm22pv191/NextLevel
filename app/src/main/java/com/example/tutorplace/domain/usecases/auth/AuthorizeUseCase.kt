package com.example.tutorplace.domain.usecases.auth

import com.example.tutorplace.data.auth.AuthService
import com.example.tutorplace.data.credentials.CredentialsStorage
import javax.inject.Inject

class AuthorizeUseCase @Inject constructor(
	private val authService: AuthService,
	private val credentialsStorage: CredentialsStorage
) {
	suspend fun execute(email: String, password: String) {
		authService
			.authorizeWithEmail(email, password)
			.onSuccess { token ->
				if (token != null) {
					credentialsStorage.saveToken(token)
				}
			}
	}
}