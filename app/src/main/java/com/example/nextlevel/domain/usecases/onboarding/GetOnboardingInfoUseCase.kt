package com.example.nextlevel.domain.usecases.onboarding

import com.example.nextlevel.data.onboarding.OnboardingService
import com.example.nextlevel.data.onboarding.model.OnboardingInfo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetOnboardingInfoUseCase @Inject constructor(
	private val onboardingService: OnboardingService,
) {
	suspend fun execute(): Result<OnboardingInfo> = withContext(Dispatchers.IO) {
		try {
			val response = onboardingService.getOnboardingInfo()
			if (response.isSuccessful && response.body() != null) {
				Result.success(response.body()!!)
			} else {
				Result.failure(Throwable(response.message()))
			}
		} catch (e: Exception) {
			Result.failure(e)
		}
	}
}