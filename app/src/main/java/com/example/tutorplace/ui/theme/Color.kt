package com.example.tutorplace.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.lerp

val Transparent = Color(0x00000000)
val ScreenColor: Color
	@Composable get() = lerp(White, MaterialTheme.colorScheme.primaryContainer, 0.10f)

val ContainerColor: Color
	@Composable get() = lerp(White, MaterialTheme.colorScheme.primaryContainer, 0.05f)

val White = Color(0xFFFFFFFF)

val Black16 = Color(0xFF161616)
val Black16Alpha30 = Color(0x4D161616)
val Black49 = Color(0xFF3D4449)
val Black36 = Color(0xFF293036)
val BlackAlpha04 = Color(0x0A000000)

val Grey82 = Color(0xFF828282)
val GreyF8 = Color(0xFFF8F8F8)
val GreyAC = Color(0xFFACACAC)
val GreyD5 = Color(0xFFD5D5D5)
val GreyD5Alpha30 = Color(0x4DD5D5D5)

val PurpleCC = Color(0xFF7200CC)
val PurpleDE = Color(0xFFA55CDE)
val PurpleC3 = Color(0xFFB71AC3)

val Red1D = Color(0xFFFC3F1D)
val Red33 = Color(0xFFD63333)

val Yellow12 = Color(0xFFF6DD12)

val Green22 = Color(0xFFCBF922)

val Blue36Alpha90 = Color(0x1E2736E5)
val BlueCE= Color(0xFF649ECE)