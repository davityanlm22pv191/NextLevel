package com.example.tutorplace.data.courses.course

data class CourseDetailed(
	val id: String,
	val name: String,
	val description: String,
	val rate: Float,
	val currentDayCount: Int,
	val dashboard: Dashboard?,
	val certificate: Certificate
) {
	companion object {
		val MOCK: CourseDetailed
			get() = CourseDetailed(
				id = "course_001",
				name = "Kotlin for Android Developers",
				description = "Подробный курс по современному Android-разработке на Kotlin",
				rate = 4.8f,
				currentDayCount = 14,
				dashboard = Dashboard.MOCK,
				certificate = Certificate.MOCK
			)
	}
}