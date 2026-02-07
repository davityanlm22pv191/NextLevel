package com.example.tutorplace.data.common

import androidx.annotation.StringRes
import com.example.tutorplace.R

enum class SortType(@StringRes val titleResId: Int) {
	PROGRESS(R.string.common_sort_progress),
	DATE_ADDED(R.string.common_sort_date_added),
}