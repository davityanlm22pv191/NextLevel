package com.example.nextlevel.network

import com.example.nextlevel.data.credentials.CredentialsStorage
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject
import javax.inject.Singleton

/**
 * OkHttp interceptor that attaches the Bearer token to every request.
 *
 * If no token is stored, the request proceeds without an Authorization header.
 */
@Singleton
class AuthInterceptor @Inject constructor(
	private val credentialsStorage: CredentialsStorage,
) : Interceptor {

	override fun intercept(chain: Interceptor.Chain): Response {
		val originalRequest = chain.request()

		val token = runBlocking { credentialsStorage.getToken() }

		val request = if (!token.isNullOrEmpty()) {
			originalRequest.newBuilder()
				.header("Authorization", "Bearer $token")
				.build()
		} else {
			originalRequest
		}

		return chain.proceed(request)
	}
}
