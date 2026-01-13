package com.example.tutorplace.navigation

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class NavBarItem(
	@field:DrawableRes val icon: Int,
	@field:StringRes val label: Int,
)
