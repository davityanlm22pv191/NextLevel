package com.example.tutorplace.ui.screens.home

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

import com.example.tutorplace.R
import com.example.tutorplace.data.common.Sort
import com.example.tutorplace.data.common.SortOrder
import com.example.tutorplace.data.common.SortType.DATE_ADDED
import com.example.tutorplace.navigation.Navigator
import com.example.tutorplace.ui.common.coursecard.card.CourseCardShapeType.LARGE
import com.example.tutorplace.ui.common.coursecard.card.CourseCardShapeType.SQUARE
import com.example.tutorplace.ui.common.coursecard.cardpager.CardPagerWithTitleAndSort
import com.example.tutorplace.ui.common.coursecard.cardpager.CardPagerWithTitleAndSortSkeleton
import com.example.tutorplace.ui.common.itemWithSkeleton
import com.example.tutorplace.ui.common.sectiontitle.model.SectionSortInfo
import com.example.tutorplace.ui.common.sectiontitle.model.SectionTitle
import com.example.tutorplace.ui.common.toolbar.ToolbarHeader
import com.example.tutorplace.ui.screens.home.presentation.HomeEvent.UI
import com.example.tutorplace.ui.screens.home.presentation.HomeNavigator
import com.example.tutorplace.ui.screens.home.presentation.HomeState
import com.example.tutorplace.ui.screens.home.presentation.HomeViewModel
import com.example.tutorplace.ui.screens.home.ui.fortunewheel.FortuneWheelShortItem
import com.example.tutorplace.ui.screens.home.ui.fortunewheel.FortuneWheelShortItemSkeleton
import com.example.tutorplace.ui.screens.home.ui.mycourses.MyCoursesEmptyItem
import com.example.tutorplace.ui.theme.ScreenColor

@Composable
fun HomeScreen(navigator: Navigator) {
	val viewModel = hiltViewModel<HomeViewModel>()
	val state by viewModel.state.collectAsStateWithLifecycle()
	val navigator = remember(navigator) { HomeNavigator(navigator) }
	LaunchedEffect(Unit) { viewModel.attachNavigator(navigator) }
	HomeScreen(
		state = state,
		onNotificationClicked = { viewModel.onEvent(UI.NotificationClicked) },
		onSearchClicked = { viewModel.onEvent(UI.SearchClicked) },
		onProfileClicked = { viewModel.onEvent(UI.ProfileClicked) },
		onFortuneWheelClicked = { viewModel.onEvent(UI.FortuneWheelClicked) },
		onFortuneWheelInformationClicked = { viewModel.onEvent(UI.FortuneWheelInformationClicked) },
		onMyCoursesClicked = { viewModel.onEvent(UI.MyCoursesClicked) },
		onCatalogClicked = { viewModel.onEvent(UI.CatalogClicked) },
		onCourseClicked = { courseId -> viewModel.onEvent(UI.CourseClicked(courseId)) }
	)
}

@Composable
private fun HomeScreen(
	state: HomeState,
	onNotificationClicked: () -> Unit,
	onSearchClicked: () -> Unit,
	onProfileClicked: () -> Unit,
	onFortuneWheelClicked: () -> Unit,
	onFortuneWheelInformationClicked: () -> Unit,
	onMyCoursesClicked: () -> Unit,
	onCatalogClicked: () -> Unit,
	onCourseClicked: (courseId: String) -> Unit,
) {
	Scaffold(
		topBar = {
			ToolbarHeader(
				screenName = stringResource(R.string.home_screen_name),
				unreadEmailCount = state.profileShortInfo?.unreadMessageCount ?: 0,
				profileImageUrl = state.profileShortInfo?.profileThumbUrl.orEmpty(),
				level = state.profileShortInfo?.level?.level ?: 0,
				progress = state.profileShortInfo?.level?.let { (_, currentAmount, target) ->
					currentAmount / target.toFloat()
				} ?: 0f,
				isArrowVisible = false,
				isLoading = state.profileShortInfo == null,
				onBackClicked = {},
				onNotificationClicked = { onNotificationClicked() },
				onSearchClicked = { onSearchClicked() },
				onProfileClicked = { onProfileClicked() }
			)
		},
		containerColor = ScreenColor,
	) { paddingValues ->
		LazyColumn(
			modifier = Modifier
				.fillMaxSize()
				.padding(paddingValues)
		) {
			itemWithSkeleton(
				key = "FortuneWheelShort",
				dataInfo = state.fortuneWheelLastRotation,
				paddingValues = PaddingValues(top = 8.dp),
				content = {
					FortuneWheelShortItem(
						lastRotationTime = state.fortuneWheelLastRotation.data,
						onInformationClick = { onFortuneWheelInformationClicked() },
						onItemClick = { onFortuneWheelClicked() }
					)
				},
				skeletonContent = { FortuneWheelShortItemSkeleton() }
			)
			itemWithSkeleton(
				key = "MyCourses",
				dataInfo = state.myCourses,
				paddingValues = PaddingValues(top = 8.dp),
				content = {
					CardPagerWithTitleAndSort(
						sectionTitle = SectionTitle.Clickable(
							text = stringResource(R.string.home_my_courses_section_title),
							onClick = { onMyCoursesClicked() }
						),
						sort = SectionSortInfo(
							selectedSort = Sort(type = DATE_ADDED, order = SortOrder.DESC),
							sorts = listOf(),
							onClick = {}
						),
						courses = state.myCourses.data ?: emptyList(),
						shape = SQUARE,
						onCourseClick = { course -> onCourseClicked(course.id) }
					)
				},
				skeletonContent = {
					CardPagerWithTitleAndSortSkeleton(shape = SQUARE, withSort = true)
				},
				emptyStateContent = {
					MyCoursesEmptyItem(onCatalogClick = { onCatalogClicked() })
				}
			)
			itemWithSkeleton(
				key = "SpeciallyForYou",
				dataInfo = state.speciallyForYou,
				paddingValues = PaddingValues(top = 8.dp),
				content = {
					CardPagerWithTitleAndSort(
						modifier = Modifier,
						sectionTitle = SectionTitle.NonClickable(text = stringResource(R.string.home_specially_for_you)),
						sort = null,
						courses = state.speciallyForYou.data ?: emptyList(),
						shape = LARGE,
						onCourseClick = { course -> onCourseClicked(course.id) }
					)
				},
				skeletonContent = {
					CardPagerWithTitleAndSortSkeleton(shape = LARGE, withSort = false)
				},
				emptyStateContent = {}
			)
		}
	}
}

@Preview
@Composable
private fun HomePreview() {
	HomeScreen(
		state = HomeState(),
		onNotificationClicked = {},
		onSearchClicked = {},
		onProfileClicked = {},
		onFortuneWheelClicked = {},
		onFortuneWheelInformationClicked = {},
		onMyCoursesClicked = {},
		onCatalogClicked = {},
		onCourseClicked = {}
	)
}