package com.example.nextlevel.domain.model

/**
 * Domain model for classified network errors.
 *
 * Not a [Throwable] — it is a plain sealed class so that it is safe to use
 * inside `data class` state holders (no recursive `cause` chains, no
 * StackOverflow in auto-generated `equals`/`hashCode`/`toString`).
 */
sealed class NetworkError {

	/** HTTP 401 — token expired or invalid credentials. */
	data object AuthError401 : NetworkError()

	/** No internet connection or DNS resolution failed. */
	data object NetworkConnectionError : NetworkError()

	/** Request timed out (SocketTimeoutException). */
	data object TimeoutError : NetworkError()

	/** Any other HTTP error or unexpected failure. */
	data class UnknownError(val message: String? = null) : NetworkError()

	/** Convert to a [Throwable] when the call-site expects one (e.g. DataInfo.Error). */
	fun asThrowable(): Throwable = when (this) {
		is AuthError401 -> Throwable("HTTP 401 Unauthorized")
		is NetworkConnectionError -> Throwable("Network connection error")
		is TimeoutError -> Throwable("Request timed out")
		is UnknownError -> Throwable(message ?: "Unknown error")
	}
}
