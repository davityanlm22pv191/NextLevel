package com.example.nextlevel.navigation.destinations

import android.os.Parcelable
import androidx.navigation3.runtime.NavKey
import com.example.nextlevel.R
import com.example.nextlevel.ui.common.toolbar.ToolbarHeaderConfig
import com.example.nextlevel.ui.screens.coursedetailed.model.CourseDetailedParams
import com.example.nextlevel.ui.screens.matrixoffate.detailed.model.MatrixOfFateDetailedParams
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

sealed interface Destinations : NavKey, Parcelable {

	@Serializable
	@Parcelize
	data object Authorization : Destinations

	@Serializable
	@Parcelize
	data object RestorePassword : Destinations

	@Serializable
	@Parcelize
	data object Registration : Destinations

	@Serializable
	@Parcelize
	data object Catalog : Destinations, DestinationWithToolbar, DestinationWithBottomBar {
		override val config: ToolbarHeaderConfig
			get() = ToolbarHeaderConfig(
				R.string.tab_catalog_title,
				theme = ToolbarHeaderConfig.ToolbarHeaderTheme.Light,
				isBackArrowVisible = false
			)
	}

	@Serializable
	@Parcelize
	data object MyCourses : Destinations, DestinationWithToolbar, DestinationWithBottomBar {
		override val config: ToolbarHeaderConfig
			get() = ToolbarHeaderConfig(
				R.string.tab_my_courses_title,
				theme = ToolbarHeaderConfig.ToolbarHeaderTheme.Light,
				isBackArrowVisible = false
			)
	}

	@Serializable
	@Parcelize
	data object Home : Destinations, DestinationWithToolbar, DestinationWithBottomBar {
		override val config: ToolbarHeaderConfig
			get() = ToolbarHeaderConfig(
				R.string.tab_home_title,
				theme = ToolbarHeaderConfig.ToolbarHeaderTheme.Light,
				isBackArrowVisible = false
			)
	}

	@Serializable
	@Parcelize
	data object Tasks : Destinations, DestinationWithToolbar, DestinationWithBottomBar {
		override val config: ToolbarHeaderConfig
			get() = ToolbarHeaderConfig(
				R.string.tab_tasks_title,
				theme = ToolbarHeaderConfig.ToolbarHeaderTheme.Light,
				isBackArrowVisible = false
			)
	}

	@Serializable
	@Parcelize
	data object Onboarding : Destinations

	@Serializable
	@Parcelize
	data object FortuneWheel : Destinations

	@Serializable
	@Parcelize
	data object FortuneWheelInformation : Destinations

	@Serializable
	@Parcelize
	data class CourseDetailed(
		val params: CourseDetailedParams
	) : Destinations, DestinationWithToolbar, DestinationWithBottomBar {
		override val config: ToolbarHeaderConfig
			get() = ToolbarHeaderConfig(
				R.string.course_detailed_title,
				theme = ToolbarHeaderConfig.ToolbarHeaderTheme.Dark,
				isBackArrowVisible = false
			)
	}

	@Serializable
	@Parcelize
	data object Mail : Destinations, DestinationWithToolbar, DestinationWithBottomBar {
		override val config: ToolbarHeaderConfig
			get() = ToolbarHeaderConfig(
				R.string.mail,
				theme = ToolbarHeaderConfig.ToolbarHeaderTheme.Light,
				isBackArrowVisible = false
			)
	}

	@Serializable
	@Parcelize
	data object Profile : Destinations

	@Serializable
	@Parcelize
	data object Search : Destinations

	@Serializable
	@Parcelize
	data object Support : Destinations

	@Serializable
	@Parcelize
	data object Offer : Destinations

	@Serializable
	@Parcelize
	data object Terms : Destinations

	@Serializable
	@Parcelize
	data object YandexAuthorization : Destinations

	@Serializable
	@Parcelize
	data class MatrixOfFateInputValues(
		override val config: ToolbarHeaderConfig
	) : Destinations, DestinationWithToolbar

	@Serializable
	@Parcelize
	data class MatrixOfFateDetailed(val params: MatrixOfFateDetailedParams) : Destinations
}