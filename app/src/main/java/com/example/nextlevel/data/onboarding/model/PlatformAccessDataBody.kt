package com.example.nextlevel.data.onboarding.model

import com.example.nextlevel.domain.model.Sex

data class PlatformAccessDataBody(
	val userName: String,
	val password: String,
	val sex: Sex
)