package com.example.nextlevel.domain.usecases.fortunewheel

import com.example.nextlevel.data.fortunewheel.repository.FortuneWheelRepository
import com.example.nextlevel.network.error.NetworkResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.time.LocalDateTime
import javax.inject.Inject

class GetLastFortuneWheelRotationDateUseCase @Inject constructor(
	private val fortuneWheelRepository: FortuneWheelRepository
) {

	suspend fun execute(): NetworkResult<LocalDateTime> = withContext(Dispatchers.IO) {
		fortuneWheelRepository.getLastRotation()
	}
}