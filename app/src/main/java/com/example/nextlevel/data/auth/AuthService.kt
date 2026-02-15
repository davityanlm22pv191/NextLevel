package com.example.nextlevel.data.auth

interface AuthService {

	suspend fun authorizeWithEmail(email: String, password: String): Result<String?>

	suspend fun register(
		name: String,
		phoneNumber: String,
		telegram: String,
		email: String,
		password: String,
	): Result<String?>

	suspend fun restorePassword(email: String): Result<Boolean>
}