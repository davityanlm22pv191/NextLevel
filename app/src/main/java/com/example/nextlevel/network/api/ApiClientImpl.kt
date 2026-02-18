package com.example.nextlevel.network.api

import com.example.nextlevel.domain.auth.LogoutHandler
import com.example.nextlevel.domain.auth.TokenRefresher
import com.example.nextlevel.domain.model.NetworkError
import com.example.nextlevel.network.error.NetworkResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import java.io.IOException
import java.net.HttpURLConnection.HTTP_UNAUTHORIZED
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import javax.inject.Inject
import retrofit2.Response as RetrofitResponse

class ApiClientImpl @Inject constructor(
	private val tokenRefresher: TokenRefresher,
	private val logoutHandler: LogoutHandler,
) : ApiClient {

	private companion object {
		private val TIMEOUT_RETRY_DELAYS_MS = longArrayOf(0L, 5_000L, 15_000L)
	}

	override suspend fun <T> call(
		apiCall: suspend () -> RetrofitResponse<T>,
	): NetworkResult<T> {
		return withContext(Dispatchers.IO) {
			for (attempt in TIMEOUT_RETRY_DELAYS_MS.indices) {
				val delayMs = TIMEOUT_RETRY_DELAYS_MS[attempt]
				if (delayMs > 0) delay(delayMs)

				try {
					val response = apiCall()
					return@withContext handleResponse(response, apiCall)
				} catch (_: SocketTimeoutException) {
					if (attempt == TIMEOUT_RETRY_DELAYS_MS.lastIndex) {
						return@withContext NetworkResult.Error(NetworkError.TimeoutError)
					}
				} catch (_: UnknownHostException) {
					return@withContext NetworkResult.Error(NetworkError.NetworkConnectionError)
				} catch (_: IOException) {
					return@withContext NetworkResult.Error(NetworkError.NetworkConnectionError)
				} catch (e: Exception) {
					return@withContext NetworkResult.Error(NetworkError.UnknownError(e.message))
				}
			}
			NetworkResult.Error(NetworkError.TimeoutError)
		}
	}

	private suspend fun <T> handleResponse(
		response: RetrofitResponse<T>,
		apiCall: suspend () -> RetrofitResponse<T>,
	): NetworkResult<T> {
		if (response.isSuccessful) {
			val body = response.body()
			return if (body != null) {
				NetworkResult.Success(body)
			} else {
				NetworkResult.Error(NetworkError.UnknownError())
			}
		}

		if (response.code() == HTTP_UNAUTHORIZED) {
			return handleUnauthorized(apiCall)
		}

		return NetworkResult.Error(
			NetworkError.UnknownError("HTTP ${response.code()}: ${response.message()}")
		)
	}

	private suspend fun <T> handleUnauthorized(
		apiCall: suspend () -> RetrofitResponse<T>,
	): NetworkResult<T> {
		val refreshed = try {
			tokenRefresher.refresh()
		} catch (_: Exception) {
			false
		}

		if (!refreshed) {
			logoutHandler.onLogoutRequested()
			return NetworkResult.Error(NetworkError.AuthError401)
		}

		return try {
			val retryResponse = apiCall()
			if (retryResponse.isSuccessful) {
				val body = retryResponse.body()
				if (body != null) {
					NetworkResult.Success(body)
				} else {
					NetworkResult.Error(NetworkError.UnknownError())
				}
			} else if (retryResponse.code() == HTTP_UNAUTHORIZED) {
				logoutHandler.onLogoutRequested()
				NetworkResult.Error(NetworkError.AuthError401)
			} else {
				NetworkResult.Error(
					NetworkError.UnknownError("HTTP ${retryResponse.code()}: ${retryResponse.message()}")
				)
			}
		} catch (_: IOException) {
			NetworkResult.Error(NetworkError.NetworkConnectionError)
		} catch (e: Exception) {
			NetworkResult.Error(NetworkError.UnknownError(e.message))
		}
	}
}