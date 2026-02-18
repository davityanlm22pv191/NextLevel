package com.example.nextlevel.network.api

import com.example.nextlevel.network.error.NetworkResult
import retrofit2.Response as RetrofitResponse

interface ApiClient {
	suspend fun <T> call(apiCall: suspend () -> RetrofitResponse<T>): NetworkResult<T>
}