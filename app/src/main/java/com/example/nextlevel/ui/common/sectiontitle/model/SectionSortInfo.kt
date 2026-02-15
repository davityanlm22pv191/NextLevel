package com.example.nextlevel.ui.common.sectiontitle.model

import com.example.nextlevel.data.common.Sort

data class SectionSortInfo(
	val selectedSort: Sort,
	val sorts: List<Sort>,
	val onClick: () -> Unit
)