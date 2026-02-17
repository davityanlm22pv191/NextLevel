package com.example.nextlevel.network.error

import com.example.nextlevel.domain.model.NetworkError

/**
 * A result wrapper returned by [safeApiCall].
 *
 * Use [onSuccess] and [onError] for convenient chaining,
 * or pattern-match via `when` expression.
 */
sealed class NetworkResult<out T> {

	data class Success<T>(val data: T) : NetworkResult<T>()

	data class Error(val error: NetworkError) : NetworkResult<Nothing>()

	val isSuccess: Boolean get() = this is Success

	val isError: Boolean get() = this is Error

	fun getOrNull(): T? = (this as? Success)?.data

	fun errorOrNull(): NetworkError? = (this as? Error)?.error

	inline fun onSuccess(action: (T) -> Unit): NetworkResult<T> {
		if (this is Success) action(data)
		return this
	}

	inline fun onError(action: (NetworkError) -> Unit): NetworkResult<T> {
		if (this is Error) action(error)
		return this
	}
}
