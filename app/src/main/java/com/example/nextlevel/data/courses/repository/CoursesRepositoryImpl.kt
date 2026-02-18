package com.example.nextlevel.data.courses.repository

import com.example.nextlevel.data.courses.CoursesService
import com.example.nextlevel.data.courses.course.Course
import com.example.nextlevel.data.courses.course.CourseDetailed
import com.example.nextlevel.network.api.ApiClient
import com.example.nextlevel.network.error.NetworkResult
import javax.inject.Inject

class CoursesRepositoryImpl @Inject constructor(
	private val apiClient: ApiClient,
	private val coursesService: CoursesService,
) : CoursesRepository {

	override suspend fun getMyCourses(): NetworkResult<List<Course>> {
		return apiClient
			.call { coursesService.getMyCourses() }
			.map { response -> response.courses }
	}

	override suspend fun getSpeciallyForYou(): NetworkResult<List<Course>> {
		return apiClient
			.call { coursesService.getSpeciallyForYou() }
			.map { response -> response.courses }
	}

	override suspend fun getCourseDetailed(courseId: String): NetworkResult<CourseDetailed> {
		return apiClient.call { coursesService.getCourseDetailed(courseId) }
	}
}