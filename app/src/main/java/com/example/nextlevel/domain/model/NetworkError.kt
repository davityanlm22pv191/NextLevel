package com.example.nextlevel.domain.model

sealed class NetworkError {

	data object AuthError401 : NetworkError()

	data object NetworkConnectionError : NetworkError()

	data object TimeoutError : NetworkError()

	data class UnknownError(val message: String? = null) : NetworkError()

	fun asThrowable(): Throwable = when (this) {
		is AuthError401 -> Throwable("HTTP 401 Unauthorized")
		is NetworkConnectionError -> Throwable("Network connection error")
		is TimeoutError -> Throwable("Request timed out")
		is UnknownError -> Throwable(message ?: "Unknown error")
	}

	fun getErrorMessage(): String = when (this) {
		is AuthError401 -> "Ошибка авторизации"
		is NetworkConnectionError -> "Ошибка интернет соединения"
		is TimeoutError -> "Произошла неизвестная ошибка"
		is UnknownError -> "Произошла неизвестная ошибка"
	}
}
