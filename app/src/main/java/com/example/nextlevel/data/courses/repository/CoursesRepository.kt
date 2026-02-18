package com.example.nextlevel.data.courses.repository

import com.example.nextlevel.data.courses.course.Course
import com.example.nextlevel.data.courses.course.CourseDetailed
import com.example.nextlevel.network.error.NetworkResult

interface CoursesRepository {
	suspend fun getMyCourses(): NetworkResult<List<Course>>

	suspend fun getSpeciallyForYou(): NetworkResult<List<Course>>

	suspend fun getCourseDetailed(courseId: String): NetworkResult<CourseDetailed>
}