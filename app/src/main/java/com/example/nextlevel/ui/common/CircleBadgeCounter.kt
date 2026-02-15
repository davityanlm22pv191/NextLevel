package com.example.nextlevel.ui.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.nextlevel.ui.theme.Black16
import com.example.nextlevel.ui.theme.Red33
import com.example.nextlevel.ui.theme.Typography
import com.example.nextlevel.ui.theme.White
import com.example.nextlevel.ui.theme.Yellow12

@Composable
fun CircleBadgeCounter(
	modifier: Modifier = Modifier,
	value: Int,
	badgeColor: Color,
	textColor: Color,
) {
	val isOverflow = value >= 100
	val text = if (isOverflow) "99+" else value.toString()
	val fontSize = if (isOverflow) 8.sp else 10.sp

	Box(
		modifier = modifier
			.size(16.dp)
			.background(badgeColor, CircleShape),
		contentAlignment = Alignment.Center
	) {
		Text(
			modifier = Modifier.offset(x = (-0.5).dp).takeIf { value <= 9 } ?: Modifier,
			text = text,
			color = textColor,
			style = Typography.displaySmall.copy(
				fontSize = fontSize,
				fontWeight = FontWeight.SemiBold,
			),
		)
	}
}

@Preview(showBackground = true)
@Composable
private fun Preview() {
	Column(
		modifier = Modifier.padding(8.dp),
		verticalArrangement = Arrangement.spacedBy(2.dp)
	) {
		CircleBadgeCounter(value = 9, badgeColor = Red33, textColor = White)
		CircleBadgeCounter(value = 99, badgeColor = Red33, textColor = White)
		CircleBadgeCounter(value = 999, badgeColor = Red33, textColor = White)
		CircleBadgeCounter(value = 9, badgeColor = Yellow12, textColor = Black16)
		CircleBadgeCounter(value = 99, badgeColor = Yellow12, textColor = Black16)
		CircleBadgeCounter(value = 999, badgeColor = Yellow12, textColor = Black16)
	}
}