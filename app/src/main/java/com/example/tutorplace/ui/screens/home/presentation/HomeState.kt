package com.example.tutorplace.ui.screens.home.presentation

import com.example.tutorplace.data.courses.course.Course
import com.example.tutorplace.data.profile.model.ProfileShortInfo
import com.example.tutorplace.domain.model.DataInfo
import com.example.tutorplace.ui.base.BaseState
import java.time.LocalDateTime

data class HomeState(
	val isLoading: Boolean = false,
	val throwable: Throwable? = null,
	val fortuneWheelLastRotation: DataInfo<LocalDateTime> = DataInfo.Loading,
	val myCourses: DataInfo<List<Course>> = DataInfo.Loading,
	val speciallyForYou: DataInfo<List<Course>> = DataInfo.Loading
) : BaseState