package com.example.tutorplace.data.courses.course

data class Certificate(
	val progress: Progress,
	val coverUrl: String,
) {
	companion object {
		val MOCK: Certificate
			get() = Certificate(
				progress = Progress(1, 21),
				coverUrl = "https://example.com/certificates/cert_123"
			)
	}
}