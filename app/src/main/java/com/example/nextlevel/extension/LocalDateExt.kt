package com.example.nextlevel.extension

import java.time.LocalDate
import java.time.ZoneId

fun LocalDate.from(utcTimeMillis: Long): LocalDate {
	return utcTimeMillis.toLocalDate()
}

fun LocalDate.isFuture(): Boolean {
	return this.isAfter(LocalDate.now())
}

fun LocalDate?.orNow(): LocalDate {
	return this ?: LocalDate.now()
}

fun LocalDate?.toEpochMillis(
	zoneId: ZoneId = ZoneId.systemDefault()
): Long {
	return this
		.orNow()
		.atStartOfDay(zoneId)
		.toInstant()
		.toEpochMilli()
}