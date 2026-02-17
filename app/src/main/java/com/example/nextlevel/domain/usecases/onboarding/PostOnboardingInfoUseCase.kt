package com.example.nextlevel.domain.usecases.onboarding

import com.example.nextlevel.data.onboarding.OnboardingService
import com.example.nextlevel.data.onboarding.model.PlatformAccessDataBody
import com.example.nextlevel.data.onboarding.model.PostInterestListBody
import com.example.nextlevel.data.onboarding.model.PostNotificationIntervalBody
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class PostOnboardingInfoUseCase @Inject constructor(
	private val onboardingService: OnboardingService
) {
	suspend fun postPlatformAccessData(body: PlatformAccessDataBody): Result<Unit> = withContext(Dispatchers.IO) {
		try {
			val response = onboardingService.postPlatformAccessData(body)
			if (response.isSuccessful) {
				Result.success(Unit)
			} else {
				Result.failure(Throwable(response.message()))
			}
		} catch (e: Exception) {
			Result.failure(e)
		}
	}

	suspend fun postInterests(list: List<Int>): Result<Unit> = withContext(Dispatchers.IO) {
		try {
			val response = onboardingService.postInterests(PostInterestListBody(list))
			if (response.isSuccessful) {
				Result.success(Unit)
			} else {
				Result.failure(Throwable(response.message()))
			}
		} catch (e: Exception) {
			Result.failure(e)
		}
	}

	suspend fun postNotificationInterval(body: PostNotificationIntervalBody): Result<Unit> = withContext(Dispatchers.IO) {
		try {
			val response = onboardingService.postNotificationInterval(body)
			if (response.isSuccessful) {
				Result.success(Unit)
			} else {
				Result.failure(Throwable(response.message()))
			}
		} catch (e: Exception) {
			Result.failure(e)
		}
	}
}