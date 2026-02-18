package com.example.nextlevel.domain.usecases.courses

import com.example.nextlevel.data.courses.course.Course
import com.example.nextlevel.data.courses.repository.CoursesRepository
import com.example.nextlevel.network.error.NetworkResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetMyCoursesUseCase @Inject constructor(
	private val coursesRepository: CoursesRepository
) {
	suspend fun execute(): NetworkResult<List<Course>> = withContext(Dispatchers.IO) {
		return@withContext coursesRepository.getMyCourses()
	}
}