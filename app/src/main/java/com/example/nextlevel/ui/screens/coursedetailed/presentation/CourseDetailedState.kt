package com.example.nextlevel.ui.screens.coursedetailed.presentation

import com.example.nextlevel.data.courses.course.CourseDetailed
import com.example.nextlevel.domain.model.DataInfo
import com.example.nextlevel.ui.base.BaseState

data class CourseDetailedState(
	val courseDetailed: DataInfo<CourseDetailed> = DataInfo.Loading,
) : BaseState