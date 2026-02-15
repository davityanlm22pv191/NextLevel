package com.example.nextlevel.ui.common.coursecard.card

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.pluralStringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.nextlevel.R
import com.example.nextlevel.ui.theme.Typography
import com.example.nextlevel.ui.theme.White
import kotlin.math.roundToInt

@Composable
fun RatingWithCourseDuration(
	modifier: Modifier = Modifier,
	shape: CourseCardShapeType,
	rate: Float,
	duration: Int,
) {
	Row(
		modifier = modifier.padding(
			top = 8.dp.takeIf { shape == CourseCardShapeType.LARGE } ?: 4.dp
		),
		verticalAlignment = Alignment.CenterVertically
	) {
		Text(
			modifier = Modifier.padding(end = 4.dp),
			text = rate.toString().replace('.', ','),
			style = Typography.labelMedium.copy(color = White),
		)
		(1..rate.roundToInt()).forEach { _ ->
			Icon(
				painter = painterResource(id = R.drawable.ic_star),
				contentDescription = null,
				tint = Color.Unspecified
			)
		}
		if (shape == CourseCardShapeType.LARGE) {
			Icon(
				modifier = Modifier.padding(horizontal = 7.dp),
				painter = painterResource(R.drawable.ic_dot),
				contentDescription = null,
				tint = Color.Unspecified
			)
			Text(
				text = pluralStringResource(
					R.plurals.common_days_format,
					duration,
					duration
				),
				style = Typography.labelMedium.copy(color = White)
			)
		}
	}
}

@Preview(showBackground = true, backgroundColor = 0xFF000000)
@Composable
fun RatingWithCourseDurationPreview() {
	Column(
		verticalArrangement = Arrangement.spacedBy(12.dp),
		modifier = Modifier.padding(16.dp)
	) {
		RatingWithCourseDuration(
			shape = CourseCardShapeType.SQUARE,
			rate = 4.8f,
			duration = 120
		)

		RatingWithCourseDuration(
			shape = CourseCardShapeType.SQUARE,
			rate = 3.5f,
			duration = 45
		)

		RatingWithCourseDuration(
			shape = CourseCardShapeType.LARGE,
			rate = 5.0f,
			duration = 360
		)

		RatingWithCourseDuration(
			shape = CourseCardShapeType.LARGE,
			rate = 0f,
			duration = 0
		)
	}
}