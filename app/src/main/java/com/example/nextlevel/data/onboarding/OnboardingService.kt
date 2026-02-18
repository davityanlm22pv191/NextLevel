package com.example.nextlevel.data.onboarding

import com.example.nextlevel.data.onboarding.model.OnboardingInfo
import com.example.nextlevel.data.onboarding.model.PlatformAccessDataBody
import com.example.nextlevel.data.onboarding.model.PostInterestListBody
import com.example.nextlevel.data.onboarding.model.PostNotificationIntervalBody
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.Response as RetrofitResponse

interface OnboardingService {

	private companion object {
		const val ENDPOINT = "onboarding"
		const val PLATFORM_ACCESS_DATA_ENDPOINT = "$ENDPOINT/platformAccessData"
		const val INTERESTS_ENDPOINT = "$ENDPOINT/interests"
		const val NOTIFICATION_INTERVAL_ENDPOINT = "$ENDPOINT/notificationInterval"
	}

	@GET(ENDPOINT)
	suspend fun getOnboardingInfo(): RetrofitResponse<OnboardingInfo>

	@POST(PLATFORM_ACCESS_DATA_ENDPOINT)
	suspend fun postPlatformAccessData(@Body body: PlatformAccessDataBody): RetrofitResponse<Unit>

	@POST(INTERESTS_ENDPOINT)
	suspend fun postInterests(@Body body: PostInterestListBody): RetrofitResponse<Unit>

	@POST(NOTIFICATION_INTERVAL_ENDPOINT)
	suspend fun postNotificationInterval(@Body body: PostNotificationIntervalBody): RetrofitResponse<Unit>
}