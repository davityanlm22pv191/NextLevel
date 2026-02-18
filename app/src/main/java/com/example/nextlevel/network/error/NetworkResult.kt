package com.example.nextlevel.network.error

import com.example.nextlevel.domain.model.NetworkError
import com.example.nextlevel.extension.getErrorMessage

sealed class NetworkResult<out T> {

	data class Success<T>(val data: T) : NetworkResult<T>()

	data class Error(val error: NetworkError) : NetworkResult<Nothing>() {
		fun getErrorMessage(): String {
			return error.asThrowable().getErrorMessage()
		}
	}

	val isSuccess: Boolean get() = this is Success

	val isError: Boolean get() = this is Error

	fun getOrNull(): T? = (this as? Success)?.data

	fun errorOrNull(): NetworkError? = (this as? Error)?.error

	fun ignoreElement(): NetworkResult<Unit> = when (this) {
		is Success -> Success(Unit)
		is Error -> this
	}

	inline fun <R> map(action: (T) -> R): NetworkResult<R> {
		return getOrNull()
			?.let { Success(action(it)) }
			?: this as Error
	}

	inline fun onSuccess(action: (T) -> Unit): NetworkResult<T> {
		if (this is Success) action(data)
		return this
	}

	inline fun onError(action: (NetworkError) -> Unit): NetworkResult<T> {
		if (this is Error) action(error)
		return this
	}
}