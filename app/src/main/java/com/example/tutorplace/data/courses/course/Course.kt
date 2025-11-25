package com.example.tutorplace.data.courses.course

data class Course(
	val id: String,
	val name: String,
	val tutorName: String,
	val rate: Float,
	val duration: Int,
	val progress: Progress?,
	val tag: String,
	val coverUrl: String,
	val forAdults: Boolean
) {
	companion object {
		val MOCK1: Course
			get() = Course(
				id = "1",
				name = "Круговые тренировки",
				tutorName = "Анастасия Леонидовна",
				rate = 4.4f,
				duration = 102,
				progress = Progress(1, 21),
				tag = "Tag",
				coverUrl = "",
				forAdults = false
			)
		val MOCK2: Course
			get() = Course(
				id = "2",
				name = "Мужские тренировки",
				tutorName = "Анастасия Леонидовна",
				rate = 4.4f,
				duration = 102,
				progress = null,
				tag = "Профессии",
				coverUrl = "",
				forAdults = false
			)
		val MOCK3: Course
			get() = Course(
				id = "3",
				name = "Плавание",
				tutorName = "Анастасия Леонидовна",
				rate = 1.5f,
				duration = 11,
				progress = null,
				tag = "Профессии",
				coverUrl = "",
				forAdults = false
			)
	}
}