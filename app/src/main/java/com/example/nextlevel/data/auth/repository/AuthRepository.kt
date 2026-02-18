package com.example.nextlevel.data.auth.repository

import com.example.nextlevel.network.error.NetworkResult

interface AuthRepository {

	suspend fun login(
		email: String,
		password: String,
	): NetworkResult<Boolean>

	suspend fun register(
		name: String,
		phoneNumber: String,
		telegram: String,
		email: String,
		password: String,
	): NetworkResult<Boolean>

	suspend fun restorePassword(email: String): NetworkResult<Boolean>
}