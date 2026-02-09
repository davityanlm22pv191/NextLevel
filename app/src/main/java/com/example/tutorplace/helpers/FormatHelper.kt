package com.example.tutorplace.helpers

import android.util.Patterns
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale

object FormatHelper {

	const val DATE_MONTH_YEAR_WITH_HOUR_MINUT_SECOND = "dd.MM.yyyy HH:mm:ss"
	const val DATE_MONTH_YEAR = "dd.MM.yyyy"

	private const val REQUIRED_NAME_LENGTH = 6
	private const val REQUIRED_PASSWORD_LENGTH = 6
	private const val RUSSIAN_PHONE_NUMBER_LENGTH = 11

	// region ==================== Date =====================

	fun formatDate(date: LocalDate, format: String): String {
		val formatter = DateTimeFormatter.ofPattern(format)
		return formatter.format(date)
	}

	fun CharSequence.toLocalDate(format: String): LocalDate {
		val formatter = DateTimeFormatter.ofPattern(format)
		return LocalDate.parse(this, formatter)
	}


	// endregion

	// region ==================== E-mail =====================

	fun isValidEmail(text: String): Boolean {
		return Patterns.EMAIL_ADDRESS.matcher(text).matches()
	}

	// endregion

	// region ==================== Password =====================

	fun isValidPassword(text: String): Boolean {
		return text.length >= REQUIRED_PASSWORD_LENGTH
	}

	// endregion

	// region ==================== Name =====================

	fun isValidName(text: String): Boolean {
		return text.isNotEmpty() && text.all { it.isLetter() } && text.length > REQUIRED_NAME_LENGTH
	}

	// endregion

	// region ==================== Phone =====================

	fun isValidPhone(phone: String): Boolean {
		return phone.length == RUSSIAN_PHONE_NUMBER_LENGTH &&
				phone.startsWith('7') &&
				phone[1] == '9'
	}

	// endregion

	// region ==================== Phone =====================

	fun isValidTelegram(telegram: String): Boolean {
		val formattedTelegram = if (telegram.startsWith('@')) telegram.substring(1) else telegram
		val regex = Regex("^[a-zA-Z0-9][a-zA-Z0-9_]{3,30}[a-zA-Z0-9]$")
		return regex.matches(formattedTelegram)
	}

	// endregion

	// region ==================== Time =====================

	fun formatTime(seconds: Int): String {
		val minutes = seconds / 60
		val remainingSeconds = seconds % 60
		return String.format(Locale.getDefault(), "%02d:%02d", minutes, remainingSeconds)
	}

	fun formatHourAndMinutes(hours: Int, minutes: Int): String {
		return String.format(Locale.getDefault(), "%02d:%02d", hours, minutes)
	}

	// endregion
}