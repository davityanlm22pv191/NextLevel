package com.example.tutorplace.domain.model

data class DataInfo<T>(
	val data: T,
	val isLoading: Boolean = true,
	val throwable: Throwable? = null,
) {
	val isLoaded: Boolean
		get() = !isLoading && throwable == null && data != null

	val isEmptyState: Boolean
		get() = isLoaded && (data is Collection<*> && data.isEmpty())
}

fun <T> DataInfo<T>.loaded(data: T): DataInfo<T> {
	return this.copy(
		data = data,
		isLoading = false,
		throwable = null
	)
}

fun <T> DataInfo<T>.loading(): DataInfo<T> {
	return this.copy(
		isLoading = true,
		throwable = null
	)
}

fun <T> DataInfo<T>.failure(throwable: Throwable): DataInfo<T> {
	return this.copy(
		isLoading = false,
		throwable = throwable
	)
}