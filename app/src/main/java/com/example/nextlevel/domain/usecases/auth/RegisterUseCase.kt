package com.example.nextlevel.domain.usecases.auth

import com.example.nextlevel.data.auth.repository.AuthRepository
import com.example.nextlevel.network.error.NetworkResult
import javax.inject.Inject

class RegisterUseCase @Inject constructor(
	private val authRepository: AuthRepository
) {
	suspend fun execute(
		name: String,
		phoneNumber: String,
		telegram: String,
		email: String,
		password: String,
	): NetworkResult<Boolean> {
		return authRepository.register(name, phoneNumber, telegram, email, password)
	}
}