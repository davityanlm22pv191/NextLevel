package com.example.nextlevel.data.common

import androidx.annotation.StringRes
import com.example.nextlevel.R

enum class SortType(@StringRes val titleResId: Int) {
	PROGRESS(R.string.common_sort_progress),
	DATE_ADDED(R.string.common_sort_date_added),
}