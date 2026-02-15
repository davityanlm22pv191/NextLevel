package com.example.nextlevel.ui.common.toolbar

import android.os.Parcelable
import androidx.annotation.StringRes
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

@Serializable
@Parcelize
data class ToolbarHeaderConfig(
	@StringRes val screenName: Int,
	val theme: ToolbarHeaderTheme,
	val isBackArrowVisible: Boolean,
) : Parcelable {
	sealed interface ToolbarHeaderTheme : Parcelable {
		@Parcelize
		data object Dark : ToolbarHeaderTheme
		@Parcelize
		data object Light : ToolbarHeaderTheme
	}
}
