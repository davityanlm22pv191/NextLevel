package com.example.nextlevel.domain.auth

import android.util.Log
import com.example.nextlevel.data.credentials.CredentialsStorage
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TokenRefresherImpl @Inject constructor(
	private val credentialsStorage: CredentialsStorage,
) : TokenRefresher {

	override suspend fun refresh(): Boolean {
		// TODO: вызвать реальный эндпоинт refresh-token и сохранить новый токен.
		//  Пример:
		//    val response = authApi.refreshToken(currentRefreshToken)
		//    if (response.isSuccessful) {
		//        credentialsStorage.saveToken(response.body()!!.accessToken)
		//        return true
		//    }
		Log.w("TokenRefresher", "Token refresh requested — not yet implemented, returning false")
		return false
	}
}
