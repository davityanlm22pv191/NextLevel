package com.example.nextlevel.ui.screens.coursedetailed.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

@Serializable
@Parcelize
data class CourseDetailedParams(
	val courseId: String,
) : Parcelable
