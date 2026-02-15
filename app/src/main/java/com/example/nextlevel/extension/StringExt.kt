package com.example.nextlevel.extension

import com.example.nextlevel.domain.model.Sex
import com.example.nextlevel.domain.model.Sex.FEMALE
import com.example.nextlevel.domain.model.Sex.MALE
import com.example.nextlevel.domain.model.Sex.MIDDLE

fun String.gender(): Sex {
	val lower = this.lowercase().trim()

	val exceptionsMasculine = listOf("дядя", "папа", "мужчина", "юноша")
	val exceptionsFeminine = listOf("мама", "женщина", "девочка", "дочь")

	if (lower in exceptionsMasculine) return MALE
	if (lower in exceptionsFeminine) return FEMALE

	return when {
		lower.endsWith("а") || lower.endsWith("я") -> FEMALE
		lower.endsWith("о") || lower.endsWith("е") -> MIDDLE
		lower.endsWith("ь") -> FEMALE
		else -> MALE
	}
}