package com.example.nextlevel.data.fortunewheel.repository

import com.example.nextlevel.data.fortunewheel.FortuneWheelService
import com.example.nextlevel.network.api.ApiClient
import com.example.nextlevel.network.error.NetworkResult
import java.time.LocalDateTime
import javax.inject.Inject

class FortuneWheelRepositoryImpl @Inject constructor(
	private val apiClient: ApiClient,
	private val fortuneWheelService: FortuneWheelService,
) : FortuneWheelRepository {

	override suspend fun getLastRotation(): NetworkResult<LocalDateTime> {
		return apiClient
			.call { fortuneWheelService.getLastRotation() }
			.map { fortuneWheelLastSpin -> fortuneWheelLastSpin.lastSpinTime }
	}
}