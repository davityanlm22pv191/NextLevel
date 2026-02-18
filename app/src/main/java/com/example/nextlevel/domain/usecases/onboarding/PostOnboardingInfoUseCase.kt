package com.example.nextlevel.domain.usecases.onboarding

import com.example.nextlevel.data.onboarding.OnboardingService
import com.example.nextlevel.data.onboarding.model.PlatformAccessDataBody
import com.example.nextlevel.data.onboarding.model.PostInterestListBody
import com.example.nextlevel.data.onboarding.model.PostNotificationIntervalBody
import com.example.nextlevel.network.api.ApiClient
import com.example.nextlevel.network.error.NetworkResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class PostOnboardingInfoUseCase @Inject constructor(
	private val apiClient: ApiClient,
	private val onboardingService: OnboardingService
) {
	suspend fun postPlatformAccessData(
		body: PlatformAccessDataBody
	): NetworkResult<Unit> = withContext(Dispatchers.IO) {
		return@withContext apiClient.call { onboardingService.postPlatformAccessData(body) }
	}

	suspend fun postInterests(
		list: List<Int>
	): NetworkResult<Unit> = withContext(Dispatchers.IO) {
		return@withContext apiClient.call {
			onboardingService.postInterests(PostInterestListBody(list))
		}
	}

	suspend fun postNotificationInterval(
		body: PostNotificationIntervalBody
	): NetworkResult<Unit> = withContext(Dispatchers.IO) {
		return@withContext apiClient.call { onboardingService.postNotificationInterval(body) }
	}
}