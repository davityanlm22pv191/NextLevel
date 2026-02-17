package com.example.nextlevel.domain.usecases.profile

import com.example.nextlevel.data.profile.ProfileService
import com.example.nextlevel.data.profile.storage.ProfileStorage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class UpdateProfileShortInfoUseCase @Inject constructor(
	private val profileService: ProfileService,
	private val profileStorage: ProfileStorage,
) {
	// Mock Image Url https://iili.io/K4WLI4a.png
	suspend fun execute(): Result<Unit> = withContext(Dispatchers.IO) {
		try {
			profileStorage.clear()
			profileStorage.profileShortInfo
			val response = profileService.getProfileShortInfo()
			if (response.isSuccessful) {
				response.body()?.let { profileShortInfo ->
					profileStorage.setProfileShortInfo(profileShortInfo)
				}
				Result.success(Unit)
			} else {
				Result.failure(Throwable(response.message()))
			}
		} catch (e: Exception) {
			Result.failure(e)
		}
	}
}