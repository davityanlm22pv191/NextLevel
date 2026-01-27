package com.example.tutorplace.navigation

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class NavBarItem(
	@param:DrawableRes val icon: Int,
	@param:StringRes val label: Int,
)
