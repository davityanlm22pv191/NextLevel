package com.example.tutorplace.data.courses.course

data class Dashboard(
	val title: String,
	val coverUrl: String,
) {
	companion object {
		val MOCK: Dashboard
			get() = Dashboard(title = "Расчет матрицы", coverUrl = "")
	}
}
