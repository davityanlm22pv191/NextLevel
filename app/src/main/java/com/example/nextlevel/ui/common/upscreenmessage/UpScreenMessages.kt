package com.example.nextlevel.ui.common.upscreenmessage

import androidx.compose.ui.graphics.Brush
import com.example.nextlevel.ui.theme.Black16
import com.example.nextlevel.ui.theme.PurpleD6
import com.example.nextlevel.ui.theme.PurpleDE
import com.example.nextlevel.ui.theme.Red33

sealed class UpScreenMessages(
	open val text: String,
	open val backgroundColor: Brush
) {
	companion object {
		const val BANNER_DISPLAY_DURATION_MS = 3000L
	}

	data class Error(
		override val text: String
	) : UpScreenMessages(text, Brush.linearGradient(listOf(PurpleD6, Red33)))

	data class Info(
		override val text: String
	) : UpScreenMessages(text, Brush.linearGradient(listOf(PurpleDE, Black16)))
}