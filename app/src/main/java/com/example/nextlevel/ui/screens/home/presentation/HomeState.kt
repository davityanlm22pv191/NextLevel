package com.example.nextlevel.ui.screens.home.presentation

import com.example.nextlevel.data.courses.course.Course
import com.example.nextlevel.domain.model.DataInfo
import com.example.nextlevel.ui.base.BaseState
import java.time.LocalDateTime

data class HomeState(
	val isLoading: Boolean = false,
	val throwable: Throwable? = null,
	val fortuneWheelLastRotation: DataInfo<LocalDateTime> = DataInfo.Loading,
	val myCourses: DataInfo<List<Course>> = DataInfo.Loading,
	val speciallyForYou: DataInfo<List<Course>> = DataInfo.Loading
) : BaseState