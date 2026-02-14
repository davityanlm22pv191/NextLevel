package com.example.nextlevel.data.auth

import kotlinx.coroutines.delay
import javax.inject.Inject

class AuthServiceImpl @Inject constructor() : AuthService {

	override suspend fun authorizeWithEmail(email: String, password: String): Result<String?> {
		delay(1500L)
		val token = (0..1000)
			.random()
			.takeIf { randomInt -> randomInt >= 400 }
			?.let { randomInt -> "$randomInt:$email:$password" }
		return Result.success(token)
	}

	override suspend fun register(
		name: String,
		phoneNumber: String,
		telegram: String,
		email: String,
		password: String,
	): Result<String?> {
		delay(1500L)
		val token = (0..1000)
			.random()
			.takeIf { randomInt -> randomInt >= 400 }
			?.let { randomInt -> "$randomInt:$email:$password::$name:$phoneNumber:$telegram" }
		return Result.success(token)
	}

	override suspend fun restorePassword(email: String): Result<Boolean> {
		delay(1500L)
		return Result.success(true)
	}
}