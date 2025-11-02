package com.example.tutorplace.ui.common.sectiontitle.model

sealed class SectionTitle(open val text: String) {

	data class NonClickable(override val text: String) : SectionTitle(text)

	data class Clickable(override val text: String, val onClick: () -> Unit) : SectionTitle(text)
}
