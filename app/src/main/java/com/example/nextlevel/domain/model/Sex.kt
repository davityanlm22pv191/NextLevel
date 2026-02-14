package com.example.nextlevel.domain.model

import androidx.annotation.StringRes
import com.example.nextlevel.R

enum class Sex(
	@StringRes val stringResId: Int
) {
	MALE(R.string.common_sex_male),
	MIDDLE(0),
	FEMALE(R.string.common_sex_female);

	override fun toString(): String {
		return when (this) {
			MALE -> "MALE"
			MIDDLE -> "MIDDLE"
			FEMALE -> "FEMALE"
		}
	}
}

fun Sex.switchStringResId(
	maleStringResId: Int,
	middleStringResId: Int,
	femaleStringResId: Int
): Int {
	return when (this) {
		Sex.MALE -> maleStringResId
		Sex.MIDDLE -> middleStringResId
		Sex.FEMALE -> femaleStringResId
	}
}