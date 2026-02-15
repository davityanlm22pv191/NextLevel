package com.example.nextlevel.domain.usecases

import com.example.nextlevel.domain.usecases.profile.UpdateProfileShortInfoUseCase
import javax.inject.Inject

class FetchInitialDataUseCase @Inject constructor(
	private val updateProfileShortInfoUseCase: UpdateProfileShortInfoUseCase,
) {
	suspend fun execute() {
		updateProfileShortInfoUseCase.execute()
	}
}