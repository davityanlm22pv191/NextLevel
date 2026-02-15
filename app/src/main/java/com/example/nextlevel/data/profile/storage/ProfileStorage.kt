package com.example.nextlevel.data.profile.storage

import com.example.nextlevel.data.profile.model.ProfileShortInfo
import kotlinx.coroutines.flow.StateFlow

interface ProfileStorage {

	val profileShortInfo: StateFlow<ProfileShortInfo?>

	fun setProfileShortInfo(info: ProfileShortInfo)

	fun clear()
}