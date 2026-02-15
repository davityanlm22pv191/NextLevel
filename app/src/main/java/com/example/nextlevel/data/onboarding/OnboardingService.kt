package com.example.nextlevel.data.onboarding

import com.example.nextlevel.data.onboarding.model.OnboardingInfo
import com.example.nextlevel.data.onboarding.model.PlatformAccessDataBody
import com.example.nextlevel.data.onboarding.model.PostInterestListBody
import com.example.nextlevel.data.onboarding.model.PostNotificationIntervalBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface OnboardingService {

	private companion object {
		const val ENDPOINT = "onboarding"
		const val PLATFORM_ACCESS_DATA_ENDPOINT = "$ENDPOINT/platformAccessData"
		const val INTERESTS_ENDPOINT = "$ENDPOINT/interests"
		const val NOTIFICATION_INTERVAL_ENDPOINT = "$ENDPOINT/notificationInterval"
	}

	@GET(ENDPOINT)
	suspend fun getOnboardingInfo(): Response<OnboardingInfo>

	@POST(PLATFORM_ACCESS_DATA_ENDPOINT)
	suspend fun postPlatformAccessData(@Body body: PlatformAccessDataBody): Response<Unit>

	@POST(INTERESTS_ENDPOINT)
	suspend fun postInterests(@Body body: PostInterestListBody): Response<Unit>

	@POST(NOTIFICATION_INTERVAL_ENDPOINT)
	suspend fun postNotificationInterval(@Body body: PostNotificationIntervalBody): Response<Unit>
}