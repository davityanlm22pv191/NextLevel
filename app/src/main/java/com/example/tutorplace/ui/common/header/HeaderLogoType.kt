package com.example.tutorplace.ui.common.header

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

sealed class HeaderLogoType {

	data class Image(
		@DrawableRes val image: Int,
		val paddingTop: Int = 40
	) : HeaderLogoType()

	data class Text(@StringRes val text: Int) : HeaderLogoType()

	data object None : HeaderLogoType()
}