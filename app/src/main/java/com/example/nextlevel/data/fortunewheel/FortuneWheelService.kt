package com.example.nextlevel.data.fortunewheel

import com.example.nextlevel.data.fortunewheel.model.FortuneWheelLastSpin
import retrofit2.Response
import retrofit2.http.GET

interface FortuneWheelService {

	private companion object {
		const val ENDPOINT = "fortuneWheel"
		const val LAST_SPIN_ENDPOINT = "$ENDPOINT/lastSpin"
	}

	@GET(LAST_SPIN_ENDPOINT)
	suspend fun getLastRotation(): Response<FortuneWheelLastSpin>
}