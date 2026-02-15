package com.example.nextlevel.data.courses.course

data class Certificate(
	val progress: Progress,
	val coverUrl: String,
) {
	companion object {
		val MOCK: Certificate
			get() = Certificate(
				progress = Progress(current = 1, target = 21),
				coverUrl = "https://example.com/certificates/cert_123"
			)
	}
}