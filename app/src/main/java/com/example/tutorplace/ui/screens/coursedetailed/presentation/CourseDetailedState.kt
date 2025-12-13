package com.example.tutorplace.ui.screens.coursedetailed.presentation

import com.example.tutorplace.data.courses.course.CourseDetailed
import com.example.tutorplace.data.profile.model.ProfileShortInfo
import com.example.tutorplace.domain.model.DataInfo
import com.example.tutorplace.ui.base.BaseState

data class CourseDetailedState(
	val courseDetailed: DataInfo<CourseDetailed?> = DataInfo(null),
	val profileShortInfo: DataInfo<ProfileShortInfo?> = DataInfo(null)
) : BaseState