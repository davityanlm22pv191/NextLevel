package com.example.nextlevel.data.credentials

import kotlinx.coroutines.flow.Flow

interface CredentialsStorage {

	suspend fun saveToken(token: String)

	suspend fun clearToken()

	suspend fun getToken(): String?

	suspend fun isAuthorized(): Flow<Boolean>

	suspend fun collectAuthorized(): Flow<Boolean>
}