package com.example.nextlevel.ui.common

import androidx.compose.material3.SelectableDates
import com.example.nextlevel.extension.isFuture
import com.example.nextlevel.extension.toLocalDate
import java.time.LocalDate

fun SelectableDatesPast(): SelectableDates {
	return object : SelectableDates {
		override fun isSelectableDate(utcTimeMillis: Long): Boolean {
			val localDate = utcTimeMillis.toLocalDate()
			return !localDate.isFuture()
		}

		override fun isSelectableYear(year: Int): Boolean {
			val currentYear = LocalDate.now().year
			return currentYear >= year
		}
	}
}