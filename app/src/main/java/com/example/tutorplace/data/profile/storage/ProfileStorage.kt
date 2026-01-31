package com.example.tutorplace.data.profile.storage

import com.example.tutorplace.data.profile.model.ProfileShortInfo
import kotlinx.coroutines.flow.StateFlow

interface ProfileStorage {

	val profileShortInfo: StateFlow<ProfileShortInfo?>

	fun setProfileShortInfo(info: ProfileShortInfo)

	fun clear()
}