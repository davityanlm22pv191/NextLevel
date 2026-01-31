package com.example.tutorplace.data.profile.storage

import com.example.tutorplace.data.profile.model.ProfileShortInfo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

class ProfileStorageImpl @Inject constructor() : ProfileStorage {

	private val _profileShortInfo = MutableStateFlow<ProfileShortInfo?>(null)

	override val profileShortInfo: StateFlow<ProfileShortInfo?>
		get() = _profileShortInfo

	override fun setProfileShortInfo(info: ProfileShortInfo) {
		_profileShortInfo.value = info
	}

	override fun clear() {
		_profileShortInfo.value = null
	}
}