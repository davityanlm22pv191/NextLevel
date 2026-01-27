package com.example.tutorplace.navigation.bottomnavbar

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class NavBarItem(
	@DrawableRes val icon: Int,
	@StringRes val label: Int,
)
