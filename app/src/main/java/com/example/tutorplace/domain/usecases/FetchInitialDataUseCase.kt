package com.example.tutorplace.domain.usecases

import com.example.tutorplace.domain.usecases.profile.UpdateProfileShortInfoUseCase
import javax.inject.Inject

class FetchInitialDataUseCase @Inject constructor(
	private val updateProfileShortInfoUseCase: UpdateProfileShortInfoUseCase,
) {
	suspend fun execute() {
		updateProfileShortInfoUseCase.execute()
	}
}