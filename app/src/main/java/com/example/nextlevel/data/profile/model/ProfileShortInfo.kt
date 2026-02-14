package com.example.nextlevel.data.profile.model

data class ProfileShortInfo(
	val id: String,
	val userName: String,
	val level: LevelInfo,
	val unreadMessageCount: Int,
	val profileThumbUrl: String,
)