package com.example.tutorplace.ui.common.sectiontitle.model

data class SectionSortInfo(
	val selectedSort: Sort,
	val sorts: List<Sort>,
	val onClick: () -> Unit
)