package com.example.tutorplace.domain.model

sealed class DataInfo<out T> {

	data object Loading : DataInfo<Nothing>()

	data class Error(val throwable: Throwable) : DataInfo<Nothing>()

	data class Success<T>(val data: T) : DataInfo<T>() {
		fun isEmptyState(): Boolean = data is Collection<*> && data.isEmpty()
	}
}