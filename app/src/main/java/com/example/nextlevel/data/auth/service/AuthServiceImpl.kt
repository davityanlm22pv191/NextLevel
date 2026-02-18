package com.example.nextlevel.data.auth.service

import kotlinx.coroutines.delay
import javax.inject.Inject
import retrofit2.Response as RetrofitResponse

class AuthServiceImpl @Inject constructor() : AuthService {

	override suspend fun authorizeWithEmail(
		email: String,
		password: String
	): RetrofitResponse<String?> {
		val token = (0..1000)
			.random()
			.takeIf { randomInt -> randomInt >= 400 }
			?.let { randomInt -> "$randomInt:$email:$password" }
		delay(1500L)
		return RetrofitResponse.success(token)
	}

	override suspend fun register(
		name: String,
		phoneNumber: String,
		telegram: String,
		email: String,
		password: String,
	): RetrofitResponse<String?> {
		val token = (0..1000)
			.random()
			.takeIf { randomInt -> randomInt >= 400 }
			?.let { randomInt -> "$randomInt:$email:$password::$name:$phoneNumber:$telegram" }
		delay(1500L)
		return RetrofitResponse.success(token)
	}

	override suspend fun restorePassword(email: String): RetrofitResponse<Boolean> {
		delay(1500L)
		return RetrofitResponse.success(true)
	}
}