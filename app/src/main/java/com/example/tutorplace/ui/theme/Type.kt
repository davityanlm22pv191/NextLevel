package com.example.tutorplace.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

// Set of Material typography styles to start with
val Typography = Typography(
	bodyLarge = TextStyle(
		fontWeight = FontWeight.SemiBold,
		fontSize = 15.sp,
		lineHeight = 21.sp,
	),
	headlineLarge = TextStyle(
		fontWeight = FontWeight.SemiBold,
		lineHeight = 29.sp,
		fontSize = 24.sp,
	),
	bodySmall = TextStyle(
		fontWeight = FontWeight.Normal,
		fontSize = 12.sp
	),
	bodyMedium = TextStyle(
		fontWeight = FontWeight.Normal,
		lineHeight = 28.sp,
		fontSize = 15.sp,
	),
	labelMedium = TextStyle(
		fontSize = 15.sp,
		lineHeight = 21.sp,
	),
	labelSmall = TextStyle(
		fontWeight = FontWeight.Normal,
		fontSize = 13.sp,
		lineHeight = 13.sp,
	),
	displayLarge = TextStyle(
		fontWeight = FontWeight.SemiBold,
		fontSize = 64.sp,
	),
	displaySmall = TextStyle(
		fontSize = 10.sp
	),
	displayMedium = TextStyle(
		fontSize = 36.sp
	),
	headlineMedium = TextStyle(
		fontSize = 20.sp
	)

	/* Other default text styles to override
	titleLarge = TextStyle(
		fontFamily = FontFamily.Default,
		fontWeight = FontWeight.Normal,
		fontSize = 22.sp,
		lineHeight = 28.sp,
		letterSpacing = 0.sp
	),
	labelSmall = TextStyle(
		fontFamily = FontFamily.Default,
		fontWeight = FontWeight.Medium,
		fontSize = 11.sp,
		lineHeight = 16.sp,
		letterSpacing = 0.5.sp
	)
	*/
)