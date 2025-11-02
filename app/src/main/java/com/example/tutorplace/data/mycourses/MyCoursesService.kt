package com.example.tutorplace.data.mycourses

import com.example.tutorplace.data.mycourses.course.CoursesResponse
import retrofit2.Response
import retrofit2.http.GET

interface MyCoursesService {

	private companion object {
		const val ENDPOINT = "myCourses"
	}

	@GET(ENDPOINT)
	suspend fun getMyCourses(): Response<CoursesResponse>
}