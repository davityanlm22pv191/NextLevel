package com.example.nextlevel.domain.usecases.onboarding

import com.example.nextlevel.data.onboarding.OnboardingService
import com.example.nextlevel.data.onboarding.model.OnboardingInfo
import com.example.nextlevel.network.api.ApiClient
import com.example.nextlevel.network.error.NetworkResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetOnboardingInfoUseCase @Inject constructor(
	private val apiClient: ApiClient,
	private val onboardingService: OnboardingService,
) {
	suspend fun execute(): NetworkResult<OnboardingInfo> = withContext(Dispatchers.IO) {
		return@withContext apiClient.call { onboardingService.getOnboardingInfo() }
	}
}