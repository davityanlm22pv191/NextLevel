package com.example.nextlevel.data.fortunewheel.repository

import com.example.nextlevel.network.error.NetworkResult
import java.time.LocalDateTime

interface FortuneWheelRepository {
	suspend fun getLastRotation(): NetworkResult<LocalDateTime>
}