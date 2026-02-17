package com.example.nextlevel.network.error

import com.example.nextlevel.domain.model.NetworkError
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import retrofit2.Response
import java.io.IOException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

/**
 * Timeout retry delays in milliseconds.
 * First attempt — immediate, then retry after 5 s, then after 15 s.
 * If all three attempts time out — return [NetworkError.TimeoutError].
 */
private val TIMEOUT_RETRY_DELAYS_MS = longArrayOf(0L, 5_000L, 15_000L)

/**
 * Executes a Retrofit suspend call safely, mapping all failures to [NetworkError].
 *
 * Flow:
 * 1. Execute [apiCall].
 * 2. If the response is successful — return the body via [NetworkResult.Success].
 * 3. If HTTP 401 — call [onRefreshToken]. If refresh succeeds, retry [apiCall] once.
 *    If the retry also fails with 401, or refresh itself fails — call [onLogout] and
 *    return [NetworkResult.Error] with [NetworkError.AuthError401].
 * 4. If [SocketTimeoutException] — retry after 5 s, then after 15 s. If all retries
 *    time out — return [NetworkError.TimeoutError].
 * 5. If [IOException] / [UnknownHostException] — return [NetworkError.NetworkConnectionError].
 * 6. Everything else — return [NetworkError.UnknownError].
 */
suspend fun <T> safeApiCall(
	onRefreshToken: suspend () -> Boolean = { false },
	onLogout: () -> Unit = {},
	apiCall: suspend () -> Response<T>,
): NetworkResult<T> {
	return withContext(Dispatchers.IO) {
		// Retry loop for timeouts: attempt 0 (immediate), 1 (after 5s), 2 (after 15s)
		for (attempt in TIMEOUT_RETRY_DELAYS_MS.indices) {
			val delayMs = TIMEOUT_RETRY_DELAYS_MS[attempt]
			if (delayMs > 0) delay(delayMs)

			try {
				val response = apiCall()
				return@withContext handleResponse(
					response = response,
					onRefreshToken = onRefreshToken,
					onLogout = onLogout,
					apiCall = apiCall,
				)
			} catch (_: SocketTimeoutException) {
				// If this was the last attempt, fall through to return TimeoutError
				if (attempt == TIMEOUT_RETRY_DELAYS_MS.lastIndex) {
					return@withContext NetworkResult.Error(NetworkError.TimeoutError)
				}
				// Otherwise continue to next retry
			} catch (_: UnknownHostException) {
				return@withContext NetworkResult.Error(NetworkError.NetworkConnectionError)
			} catch (_: IOException) {
				return@withContext NetworkResult.Error(NetworkError.NetworkConnectionError)
			} catch (e: Exception) {
				return@withContext NetworkResult.Error(NetworkError.UnknownError(e.message))
			}
		}
		// Should not reach here, but just in case
		NetworkResult.Error(NetworkError.TimeoutError)
	}
}

private suspend fun <T> handleResponse(
	response: Response<T>,
	onRefreshToken: suspend () -> Boolean,
	onLogout: () -> Unit,
	apiCall: suspend () -> Response<T>,
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
		return handleUnauthorized(onRefreshToken, onLogout, apiCall)
	}

	return NetworkResult.Error(
		NetworkError.UnknownError("HTTP ${response.code()}: ${response.message()}")
	)
}

private suspend fun <T> handleUnauthorized(
	onRefreshToken: suspend () -> Boolean,
	onLogout: () -> Unit,
	apiCall: suspend () -> Response<T>,
): NetworkResult<T> {
	val refreshed = try {
		onRefreshToken()
	} catch (_: Exception) {
		false
	}

	if (!refreshed) {
		onLogout()
		return NetworkResult.Error(NetworkError.AuthError401)
	}

	// Retry the original call once after a successful token refresh
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
			onLogout()
			NetworkResult.Error(NetworkError.AuthError401)
		} else {
			NetworkResult.Error(
				NetworkError.UnknownError("HTTP ${retryResponse.code()}: ${retryResponse.message()}")
			)
		}
	} catch (e: IOException) {
		NetworkResult.Error(NetworkError.NetworkConnectionError)
	} catch (e: Exception) {
		NetworkResult.Error(NetworkError.UnknownError(e.message))
	}
}

private const val HTTP_UNAUTHORIZED = 401
