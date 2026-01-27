package com.example.tutorplace.extension

import androidx.compose.ui.graphics.Color
import kotlin.math.sqrt

fun Color.isLight(): Boolean {
	val r = (red * 255).toInt()
	val g = (green * 255).toInt()
	val b = (blue * 255).toInt()

	// perceived brightness algorithm
	val brightness = sqrt(r * r * 0.241 + g * g * 0.691 + b * b * 0.068)
	return brightness >= 130
}