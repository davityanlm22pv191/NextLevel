package com.example.nextlevel.ui.common.lazyitems

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyGridScope
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.nextlevel.R
import com.example.nextlevel.data.common.Sort
import com.example.nextlevel.data.common.SortOrder
import com.example.nextlevel.data.common.SortType.DATE_ADDED
import com.example.nextlevel.data.courses.course.Course
import com.example.nextlevel.domain.model.DataInfo
import com.example.nextlevel.ui.common.coursecard.card.CourseCardShapeType.LARGE
import com.example.nextlevel.ui.common.coursecard.cardpager.CardPagerWithTitleAndSort
import com.example.nextlevel.ui.common.coursecard.cardpager.CardPagerWithTitleAndSortSkeleton
import com.example.nextlevel.ui.common.sectiontitle.model.SectionSortInfo
import com.example.nextlevel.ui.common.sectiontitle.model.SectionTitle
import com.example.nextlevel.ui.screens.home.ui.mycourses.MyCoursesEmptyItem

fun <T> LazyListScope.itemWithSkeleton(
	key: String,
	contentType: Any = key,
	dataInfo: DataInfo<T>,
	paddingValues: PaddingValues = PaddingValues(),
	content: @Composable (T) -> Unit,
	skeletonContent: @Composable () -> Unit,
	emptyStateContent: @Composable () -> Unit = {}
) {
	item(
		key = key,
		contentType = contentType,
	) {
		AnimatedContent(
			modifier = Modifier.padding(paddingValues),
			label = "${key}_itemWithSkeleton",
			targetState = dataInfo,
			transitionSpec = { fadeIn(tween(500)) togetherWith fadeOut(tween(500)) }
		) { dataInfo ->
			when (dataInfo) {
				is DataInfo.Loading -> skeletonContent()
				is DataInfo.Error -> {}
				is DataInfo.Success -> if (dataInfo.isEmptyState()) {
					emptyStateContent()
				} else {
					content(dataInfo.data)
				}
			}
		}
	}
}

fun <T> LazyGridScope.itemWithSkeleton(
	key: String,
	contentType: Any = key,
	span: GridItemSpan? = null,
	dataInfo: DataInfo<T>,
	paddingValues: PaddingValues = PaddingValues(),
	content: @Composable (T) -> Unit,
	skeletonContent: @Composable () -> Unit,
	emptyStateContent: @Composable () -> Unit = {}
) {
	item(
		key = key,
		contentType = contentType,
		span = span?.let { gridItemSpan -> { gridItemSpan } }
	) {
		AnimatedContent(
			modifier = Modifier.padding(paddingValues),
			label = "${key}_itemWithSkeleton",
			targetState = dataInfo,
			transitionSpec = { fadeIn(tween(500)) togetherWith fadeOut(tween(500)) }
		) { dataInfo ->
			when (dataInfo) {
				is DataInfo.Loading -> skeletonContent()
				is DataInfo.Error -> {}
				is DataInfo.Success -> if (dataInfo.isEmptyState()) {
					emptyStateContent()
				} else {
					content(dataInfo.data)
				}
			}
		}
	}
}

@Preview(showBackground = true, backgroundColor = 0xFF000000L)
@Composable
private fun SkeletonContentPreview() {
	LazyColumn(modifier = Modifier.padding(16.dp)) {
		itemWithSkeleton(
			key = "skeleton",
			contentType = "skeleton",
			dataInfo = DataInfo.Loading,
			paddingValues = PaddingValues(),
			content = {},
			skeletonContent = {
				CardPagerWithTitleAndSortSkeleton(shape = LARGE, withSort = false)
			},
			emptyStateContent = {}
		)
	}
}

@Preview(showBackground = true, backgroundColor = 0xFF000000L)
@Composable
private fun SuccessContentPreview() {
	LazyColumn(modifier = Modifier.padding(16.dp)) {
		itemWithSkeleton(
			key = "skeleton",
			contentType = "skeleton",
			dataInfo = DataInfo.Success(listOf<Course>(Course.MOCK1, Course.MOCK2)),
			paddingValues = PaddingValues(),
			content = { courses ->
				CardPagerWithTitleAndSort(
					sectionTitle = SectionTitle.Clickable(
						text = stringResource(R.string.home_my_courses_section_title),
						onClick = {}
					),
					sort = SectionSortInfo(
						selectedSort = Sort(type = DATE_ADDED, order = SortOrder.DESC),
						sorts = listOf(),
						onClick = {}
					),
					courses = courses,
					shape = LARGE,
					onCourseClick = {}
				)
			},
			skeletonContent = {},
			emptyStateContent = {}
		)
	}
}

@Preview(showBackground = true, backgroundColor = 0xFF000000L)
@Composable
private fun EmptyContentPreview() {
	LazyColumn(modifier = Modifier.padding(16.dp)) {
		itemWithSkeleton(
			key = "skeleton",
			contentType = "skeleton",
			dataInfo = DataInfo.Success(listOf<Course>()),
			paddingValues = PaddingValues(),
			content = {},
			skeletonContent = {},
			emptyStateContent = {
				MyCoursesEmptyItem(onCatalogClick = {})
			}
		)
	}
}