package com.example.tutorplace.data.credentials

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class CredentialsStorageImpl @Inject constructor(
	@ApplicationContext private val context: Context
) : CredentialsStorage {

	private val Context.dataStore by preferencesDataStore(name = "credentials")
	private val tokenPreferenceKey = stringPreferencesKey("TOKEN_PREFERENCE_KEY")

	override suspend fun saveToken(token: String)  {
		context.dataStore.edit { prefs -> prefs[tokenPreferenceKey] = token }
	}

	override suspend fun isAuthorized(): Flow<Boolean> {
		return getToken().map { token -> !token.isNullOrEmpty() }
	}

	override suspend fun clearToken() {
		context.dataStore.edit { prefs -> prefs.remove(tokenPreferenceKey) }
	}

	private fun getToken(): Flow<String?> = with(context.dataStore) {
		data.map { prefs -> prefs[tokenPreferenceKey] }
	}

	override suspend fun collectAuthorized(): Flow<Boolean> = getToken().map { token -> !token.isNullOrEmpty() }
}