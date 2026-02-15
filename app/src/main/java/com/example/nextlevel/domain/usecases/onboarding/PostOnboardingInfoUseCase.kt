package com.example.nextlevel.domain.usecases.onboarding

import com.example.nextlevel.data.onboarding.OnboardingService
import com.example.nextlevel.data.onboarding.model.PlatformAccessDataBody
import com.example.nextlevel.data.onboarding.model.PostInterestListBody
import com.example.nextlevel.data.onboarding.model.PostNotificationIntervalBody
import javax.inject.Inject

class PostOnboardingInfoUseCase @Inject constructor(
	private val onboardingService: OnboardingService
) {
	suspend fun postPlatformAccessData(body: PlatformAccessDataBody): Result<Unit> {
		val response = onboardingService.postPlatformAccessData(body)
		return if (response.isSuccessful) {
			Result.success(Unit)
		} else {
			Result.failure(Throwable(response.message()))
		}
	}

	suspend fun postInterests(list: List<Int>): Result<Unit> {
		val response = onboardingService.postInterests(PostInterestListBody(list))
		return if (response.isSuccessful) {
			Result.success(Unit)
		} else {
			Result.failure(Throwable(response.message()))
		}
	}

	suspend fun postNotificationInterval(body: PostNotificationIntervalBody): Result<Unit> {
		val response = onboardingService.postNotificationInterval(body)
		return if (response.isSuccessful) {
			Result.success(Unit)
		} else {
			Result.failure(Throwable(response.message()))
		}
	}
}