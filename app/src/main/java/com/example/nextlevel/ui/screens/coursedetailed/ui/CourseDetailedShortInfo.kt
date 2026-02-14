package com.example.nextlevel.ui.screens.coursedetailed.ui

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.nextlevel.R
import com.example.nextlevel.data.courses.course.CourseDetailed
import com.example.nextlevel.ui.common.PurpleGradientButton
import com.example.nextlevel.ui.common.coursecard.card.CourseCardShapeType
import com.example.nextlevel.ui.common.coursecard.card.RatingWithCourseDuration
import com.example.nextlevel.ui.common.toolbar.TOOLBAR_HEADER_HEIGHT
import com.example.nextlevel.ui.theme.Black
import com.example.nextlevel.ui.theme.Black34
import com.example.nextlevel.ui.theme.PurpleDE
import com.example.nextlevel.ui.theme.Typography
import com.example.nextlevel.ui.theme.White

@Composable
fun CourseDetailedShortInfo(
	modifier: Modifier = Modifier,
	course: CourseDetailed,
	onStartLessonClicked: () -> Unit,
	onMaterialsForCourseClicked: () -> Unit
) {
	Column(
		modifier = modifier
			.fillMaxWidth()
			.clip(RoundedCornerShape(bottomStart = 20.dp, bottomEnd = 20.dp))
			.padding(16.dp),
	) {
		Text(
			text = course.name,
			style = Typography.headlineLarge.copy(color = White),
		)
		Text(
			modifier = Modifier.padding(top = 8.dp),
			text = course.description,
			style = Typography.labelMedium.copy(color = White),
		)
		RatingWithCourseDuration(
			modifier = Modifier.padding(top = 8.dp),
			shape = CourseCardShapeType.LARGE,
			rate = course.rate,
			duration = course.currentDayCount,
		)
		Row(
			modifier = Modifier.padding(top = 28.dp),
			verticalAlignment = Alignment.CenterVertically
		) {
			PurpleGradientButton(
				modifier = Modifier
					.width(138.dp)
					.height(48.dp),
				text = stringResource(R.string.course_detailed_start_lesson),
				onClick = { onStartLessonClicked() }
			)
			Spacer(Modifier.weight(1f))
			Text(
				text = stringResource(R.string.course_detailed_materials),
				style = Typography.labelMedium.copy(color = White),
				textAlign = TextAlign.End,
			)
			val interactionSource = remember { MutableInteractionSource() }
			val isPressed by interactionSource.collectIsPressedAsState()
			val alpha by animateFloatAsState(if (isPressed) 0.5f else 1f)
			Icon(
				modifier = Modifier
					.padding(start = 8.dp)
					.width(50.dp)
					.height(50.dp)
					.background(
						color = White.copy(alpha = 0.05f),
						shape = RoundedCornerShape(12.dp)
					)
					.clip(RoundedCornerShape(12.dp))
					.clickable(
						interactionSource = interactionSource,
						onClick = { onMaterialsForCourseClicked() }
					)
					.alpha(alpha)
					.padding(4.dp),
				painter = painterResource(R.drawable.ic_folder),
				contentDescription = null,
				tint = PurpleDE
			)
		}
	}
}

@Composable
@Preview
private fun CourseDetailedShortInfoPreview() {
	Box(
		modifier = Modifier
			.background(color = White)
			.padding(bottom = 40.dp),
	) {
		CourseDetailedShortInfo(
			modifier = Modifier
				.background(
					brush = Brush.verticalGradient(colors = listOf(Black, Black34)),
					shape = RoundedCornerShape(bottomStart = 20.dp, bottomEnd = 20.dp)
				)
				.padding(top = TOOLBAR_HEADER_HEIGHT.dp),
			course = CourseDetailed.MOCK,
			onStartLessonClicked = {},
			onMaterialsForCourseClicked = {}
		)
	}
}