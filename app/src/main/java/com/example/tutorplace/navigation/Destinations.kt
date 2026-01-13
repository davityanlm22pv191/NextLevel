package com.example.tutorplace.navigation

import android.os.Parcelable
import androidx.navigation3.runtime.NavKey
import com.example.tutorplace.ui.screens.coursedetailed.model.CourseDetailedParams
import com.example.tutorplace.ui.screens.main.model.MainScreenParams
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
	data object Catalog : Destinations

	@Serializable
	@Parcelize
	data object MyCourses : Destinations

	@Serializable
	@Parcelize
	data object Home : Destinations

	@Serializable
	@Parcelize
	data object Tasks : Destinations

	@Serializable
	@Parcelize
	data class MainScreen(val params: MainScreenParams) : Destinations

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
	data class CourseDetailed(val params: CourseDetailedParams) : Destinations

	@Serializable
	@Parcelize
	data object Support : Destinations

	@Serializable
	@Parcelize
	data object YandexAuthorization : Destinations
}