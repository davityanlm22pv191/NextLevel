package com.example.nextlevel.domain.usecases.profile

import com.example.nextlevel.data.profile.ProfileService
import com.example.nextlevel.data.profile.storage.ProfileStorage
import com.example.nextlevel.network.api.ApiClient
import com.example.nextlevel.network.error.NetworkResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class UpdateProfileShortInfoUseCase @Inject constructor(
	private val apiClient: ApiClient,
	private val profileService: ProfileService,
	private val profileStorage: ProfileStorage,
) {
	// Mock Image Url https://iili.io/K4WLI4a.png
	suspend fun execute(): NetworkResult<Unit> = withContext(Dispatchers.IO) {
		profileStorage.clear()
		return@withContext apiClient
			.call { profileService.getProfileShortInfo() }
			.onSuccess { profileShortInfo ->
				profileStorage.setProfileShortInfo(profileShortInfo)
			}
			.onError { networkError ->
				Result.failure<Throwable>(networkError.asThrowable())
			}
			.ignoreElement()
	}
}