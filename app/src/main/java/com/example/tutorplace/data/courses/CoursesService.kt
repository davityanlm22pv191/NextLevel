package com.example.tutorplace.data.courses

import com.example.tutorplace.data.courses.course.CourseDetailed
import com.example.tutorplace.data.courses.course.CoursesResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface CoursesService {

	private companion object {
		const val MY_COURSES_ENDPOINT = "myCourses"
		const val SPECIALLY_FOR_YOU_ENDPOINT = "speciallyForYou"
		const val COURSE_DETAILED_ENDPOINT = "courseDetailed/{courseId}"
	}

	@GET(MY_COURSES_ENDPOINT)
	suspend fun getMyCourses(): Response<CoursesResponse>

	@GET(SPECIALLY_FOR_YOU_ENDPOINT)
	suspend fun getSpeciallyForYou(): Response<CoursesResponse>

	@GET(COURSE_DETAILED_ENDPOINT)
	suspend fun getCourseDetailed(@Path("courseId") courseId: String): Response<CourseDetailed>
}