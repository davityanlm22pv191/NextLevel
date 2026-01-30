package com.example.tutorplace.ui.common.toolbar

import androidx.annotation.StringRes

data class ToolbarHeaderConfig(
	@StringRes val screenName: Int,
	val theme: ToolbarHeaderTheme,
	val isBackArrowVisible: Boolean,
) {
	sealed interface ToolbarHeaderTheme {
		data object Dark : ToolbarHeaderTheme
		data object Light : ToolbarHeaderTheme
	}
}
