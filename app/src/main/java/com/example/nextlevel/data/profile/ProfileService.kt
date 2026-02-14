package com.example.nextlevel.data.profile

import com.example.nextlevel.data.profile.model.ProfileShortInfo
import retrofit2.Response
import retrofit2.http.GET

interface ProfileService {

	private companion object {
		const val PROFILE_SHORT_INFO_ENDPOINT = "profile/short"
	}

	@GET(PROFILE_SHORT_INFO_ENDPOINT)
	suspend fun getProfileShortInfo(): Response<ProfileShortInfo>
}