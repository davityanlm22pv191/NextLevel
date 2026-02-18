package com.example.nextlevel.data.auth.service

import retrofit2.Response as RetrofitResponse

interface AuthService {

	suspend fun authorizeWithEmail(email: String, password: String): RetrofitResponse<String?>

	suspend fun register(
		name: String,
		phoneNumber: String,
		telegram: String,
		email: String,
		password: String,
	): RetrofitResponse<String?>

	suspend fun restorePassword(email: String): RetrofitResponse<Boolean>
}