package com.example.tutorplace.ui.common.sectiontitle.model

import com.example.tutorplace.data.common.Sort

data class SectionSortInfo(
	val selectedSort: Sort,
	val sorts: List<Sort>,
	val onClick: () -> Unit
)