package com.example.tutorplace.ui.common.coursecard.cardpager

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.tutorplace.data.common.Sort
import com.example.tutorplace.data.common.SortOrder.DESC
import com.example.tutorplace.data.common.SortType.DATE_ADDED
import com.example.tutorplace.data.courses.course.Course
import com.example.tutorplace.ui.common.coursecard.card.CourseCardShapeType
import com.example.tutorplace.ui.common.coursecard.card.CourseCardShapeType.LARGE
import com.example.tutorplace.ui.common.coursecard.card.CourseCardShapeType.SQUARE
import com.example.tutorplace.ui.common.sectiontitle.SectionTitleWithSort
import com.example.tutorplace.ui.common.sectiontitle.model.SectionSortInfo
import com.example.tutorplace.ui.common.sectiontitle.model.SectionTitle
import com.example.tutorplace.ui.theme.Black16Alpha30
import com.example.tutorplace.ui.theme.White

@Composable
fun CardPagerWithTitleAndSort(
	modifier: Modifier = Modifier,
	sectionTitle: SectionTitle,
	sort: SectionSortInfo?,
	courses: List<Course>,
	shape: CourseCardShapeType,
	onCourseClick: (Course) -> Unit,
) {
	Column(
		modifier = modifier
			.fillMaxWidth()
			.background(color = White, shape = RoundedCornerShape(20.dp))
			.padding(vertical = 16.dp),
		verticalArrangement = Arrangement.spacedBy(16.dp)
	) {
		SectionTitleWithSort(
			modifier = Modifier
				.fillMaxWidth()
				.padding(horizontal = 16.dp),
			sectionTitle,
			sort
		)
		CourseCardsPager(
			modifier = Modifier.fillMaxWidth(),
			shape = shape,
			courses = courses,
			paddingValues = PaddingValues(horizontal = 16.dp),
			onCourseClick = { course -> onCourseClick(course) },
		)
	}
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
@Composable
private fun CardPagerWithTitleAndSortPreview() {
	Column(
		modifier = Modifier
			.fillMaxWidth()
			.background(color = Black16Alpha30),
		verticalArrangement = Arrangement.spacedBy(12.dp)
	) {
		CardPagerWithTitleAndSort(
			sectionTitle = SectionTitle.Clickable(text = "Мое обучение", onClick = {}),
			sort = SectionSortInfo(
				selectedSort = Sort(DATE_ADDED, order = DESC),
				sorts = listOf(),
				onClick = {}
			),
			courses = listOf(Course.MOCK1, Course.MOCK2, Course.MOCK3),
			shape = SQUARE,
			onCourseClick = {}
		)

		CardPagerWithTitleAndSort(
			sectionTitle = SectionTitle.Clickable(text = "Специально для вас", onClick = {}),
			sort = null,
			courses = listOf(Course.MOCK1, Course.MOCK2, Course.MOCK3),
			shape = LARGE,
			onCourseClick = {}
		)
	}
}