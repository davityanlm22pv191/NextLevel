package com.example.nextlevel.ui.common.spannabletext

import androidx.compose.ui.text.SpanStyle

data class SpanLinkData(
	val link: String,
	val tag: String,
	val style: SpanStyle,
	val onClick: () -> Unit
)