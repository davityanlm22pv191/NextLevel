package com.example.tutorplace.ui.common.coursecard.cardpager

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.tutorplace.data.mycourses.course.Course
import com.example.tutorplace.ui.common.coursecard.card.CourseCard
import com.example.tutorplace.ui.common.coursecard.card.CourseCardShapeType
import com.example.tutorplace.ui.common.coursecard.card.CourseCardShapeType.LARGE
import com.example.tutorplace.ui.common.coursecard.card.CourseCardShapeType.SQUARE
import com.example.tutorplace.ui.theme.Black16
import com.example.tutorplace.ui.theme.Black16Alpha30
import com.example.tutorplace.ui.theme.GreyF8

@Composable
fun CourseCardsPager(
	modifier: Modifier = Modifier,
	shape: CourseCardShapeType,
	courses: List<Course>,
	paddingValues: PaddingValues = PaddingValues(0.dp),
	onCourseClick: (Course) -> Unit,
) {
	val thresholdOffset = remember { if (shape == SQUARE) 100 else 200 }
	val lazyListState = rememberLazyListState()
	val currentIndex = remember {
		derivedStateOf {
			val firstVisibleItemIndex = lazyListState.firstVisibleItemIndex
			val offset = lazyListState.firstVisibleItemScrollOffset
			val lastVisibleItem = lazyListState.layoutInfo.visibleItemsInfo.lastOrNull()
			val isLastFullyVisible = lastVisibleItem?.let {
				it.index == courses.lastIndex &&
						it.offset + it.size <= lazyListState.layoutInfo.viewportEndOffset
			} ?: false
			val current = when {
				isLastFullyVisible -> courses.lastIndex
				offset > thresholdOffset -> firstVisibleItemIndex + 1
				else -> firstVisibleItemIndex
			}
			current.coerceIn(0, courses.lastIndex)
		}
	}
	Column(
		modifier = modifier.fillMaxWidth(),
		horizontalAlignment = Alignment.CenterHorizontally,
		verticalArrangement = Arrangement.spacedBy(24.dp)
	) {
		LazyRow(
			modifier = Modifier.fillMaxWidth(),
			state = lazyListState,
			horizontalArrangement = Arrangement.spacedBy(8.dp),
			contentPadding = paddingValues
		) {
			courses.forEach { course ->
				item(key = course.id) {
					CourseCard(course = course, shape = shape, onClick = { onCourseClick(course) })
				}
			}
		}
		if (courses.size != 1) {
			DotsIndicator(
				totalDots = courses.size,
				selectedIndex = currentIndex.value
			)
		}
	}
}

@Composable
fun DotsIndicator(
	modifier: Modifier = Modifier,
	totalDots: Int,
	selectedIndex: Int,
) {
	Row(
		modifier = modifier
			.background(GreyF8, shape = RoundedCornerShape(100.dp))
			.padding(horizontal = 16.dp, vertical = 13.dp),
		horizontalArrangement = Arrangement.spacedBy(8.dp),
		verticalAlignment = Alignment.CenterVertically
	) {
		repeat(totalDots) { index ->
			val dotSize by animateDpAsState(
				targetValue = if (index == selectedIndex) 6.dp else 4.dp,
				animationSpec = tween(durationMillis = 300)
			)
			val color by animateColorAsState(
				targetValue = if (index == selectedIndex) Black16 else Black16Alpha30,
				animationSpec = tween(durationMillis = 300)
			)
			Box(
				modifier = Modifier
					.size(dotSize)
					.clip(CircleShape)
					.background(color)
			)
		}
	}
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
@Composable
private fun CourseCardsSquarePagerPreview() {
	CourseCardsPager(
		shape = SQUARE,
		courses = listOf(Course.MOCK1, Course.MOCK2, Course.MOCK3),
		paddingValues = PaddingValues(horizontal = 16.dp),
		onCourseClick = {}
	)
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
@Composable
private fun CourseCardsLargePagerPreview() {
	CourseCardsPager(
		shape = LARGE,
		courses = listOf(Course.MOCK1, Course.MOCK2, Course.MOCK3),
		paddingValues = PaddingValues(horizontal = 16.dp),
		onCourseClick = {}
	)
}