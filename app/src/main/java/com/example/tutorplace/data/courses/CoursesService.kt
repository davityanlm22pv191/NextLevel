package com.example.tutorplace.data.courses

import com.example.tutorplace.data.courses.course.CoursesResponse
import retrofit2.Response
import retrofit2.http.GET

interface CoursesService {

	private companion object {
		const val MY_COURSES_ENDPOINT = "myCourses"
		const val SPECIALLY_FOR_YOU_ENDPOINT = "speciallyForYou"
	}

	@GET(MY_COURSES_ENDPOINT)
	suspend fun getMyCourses(): Response<CoursesResponse>

	@GET(SPECIALLY_FOR_YOU_ENDPOINT)
	suspend fun getSpeciallyForYou(): Response<CoursesResponse>
}