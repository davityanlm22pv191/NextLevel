package com.example.tutorplace.data.mycourses.course

data class Course(
	val id: String,
	val name: String,
	val tutorName: String,
	val rate: Float,
	val duration: Int,
	val progress: Progress,
	val tag: String,
	val shape: CourseShapeType,
	val coverUrl: String,
	val forAdults: Boolean
)