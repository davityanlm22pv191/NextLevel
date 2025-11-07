package com.example.tutorplace.ui.common.coursecard.card

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.graphics.vector.VectorPainter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.pluralStringResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.tutorplace.R
import com.example.tutorplace.data.mycourses.course.Course
import com.example.tutorplace.ui.theme.Black16
import com.example.tutorplace.ui.theme.Green22
import com.example.tutorplace.ui.theme.GreyD5
import com.example.tutorplace.ui.theme.Typography
import com.example.tutorplace.ui.theme.White
import kotlin.math.roundToInt

@Composable
fun CourseCard(
	modifier: Modifier = Modifier,
	course: Course,
	shape: CourseCardShapeType,
	onClick: () -> Unit
) {
	Box(
		modifier = modifier
			.width(if (shape == CourseCardShapeType.SQUARE) 175.dp else 340.dp)
			.height(200.dp)
			.clip(RoundedCornerShape(20.dp))
			.clickable { onClick() },
	) {
		AsyncImage(
			modifier = Modifier.matchParentSize(),
			model = course.coverUrl,
			contentDescription = null,
			placeholder = ColorPainter(GreyD5),
			error = ColorPainter(Black16)
		)
		Column(
			modifier = Modifier.padding(16.dp),
		) {
			if (shape == CourseCardShapeType.LARGE && course.tutorName.isNotBlank()) {
				Text(
					modifier = Modifier.fillMaxWidth(),
					text = course.tutorName,
					style = Typography.labelSmall.copy(lineHeight = 18.sp, color = White),
				)
			}
			Text(
				modifier = Modifier.padding(
					top = 4.dp.takeIf { shape == CourseCardShapeType.LARGE } ?: 0.dp
				),
				text = course.name,
				style = Typography.labelMedium.copy(color = White),
			)
			Row(
				modifier = Modifier.padding(
					top = 8.dp.takeIf { shape == CourseCardShapeType.LARGE } ?: 4.dp
				),
				verticalAlignment = Alignment.CenterVertically
			) {
				Text(
					modifier = Modifier.padding(end = 4.dp),
					text = course.rate.toString().replace('.', ','),
					style = Typography.labelMedium.copy(color = White),
				)
				(1..course.rate.roundToInt()).forEach { _ ->
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
							course.duration,
							course.duration
						),
						style = Typography.labelMedium.copy(color = White)
					)
				}
			}
			Spacer(modifier = Modifier.weight(1f))
			if (course.progress != null) {
				Row(
					modifier = Modifier
						.background(color = Green22, RoundedCornerShape(8.dp))
						.padding(horizontal = 8.dp, vertical = 3.dp),
					verticalAlignment = Alignment.CenterVertically,
					horizontalArrangement = Arrangement.spacedBy(4.dp)
				) {
					Icon(
						painter = painterResource(id = R.drawable.ic_progress),
						contentDescription = null,
						tint = Color.Unspecified
					)
					Text(
						text = stringResource(
							R.string.common_splash_format,
							course.progress.current,
							course.progress.target
						),
						style = Typography.labelMedium.copy(color = Black16)
					)
				}
			} else {
				Text(
					modifier = Modifier
						.background(color = White, RoundedCornerShape(8.dp))
						.padding(horizontal = 6.dp, vertical = 3.dp),
					text = course.tag,
					style = Typography.labelMedium.copy(color = Black16)
				)
			}
		}
	}
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
@Composable
fun CourseCardPreview() {
	Column(
		modifier = Modifier
			.fillMaxWidth()
			.padding(16.dp),
		horizontalAlignment = Alignment.CenterHorizontally,
		verticalArrangement = Arrangement.spacedBy(16.dp)
	) {
		CourseCard(
			course = Course.MOCK1,
			shape = CourseCardShapeType.SQUARE,
			onClick = {}
		)
		CourseCard(
			course = Course.MOCK2,
			shape = CourseCardShapeType.LARGE,
			onClick = {}
		)
	}
}