package com.example.tutorplace.ui.common.toolbar

import androidx.annotation.StringRes

data class ToolbarHeaderConfig(
	@StringRes val screenName: Int,
	val style: ToolbarHeaderStyle,
	val isBackArrowVisible: Boolean,
) {
	sealed interface ToolbarHeaderStyle {
		data object Transparent : ToolbarHeaderStyle
		data object Light : ToolbarHeaderStyle
		data object Dark : ToolbarHeaderStyle
	}
}
