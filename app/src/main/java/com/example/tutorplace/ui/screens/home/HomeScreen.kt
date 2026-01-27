package com.example.tutorplace.ui.screens.home

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
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
import com.example.tutorplace.navigation.destinations.Destinations
import com.example.tutorplace.navigation.Navigator
import com.example.tutorplace.ui.common.coursecard.card.CourseCardShapeType.LARGE
import com.example.tutorplace.ui.common.coursecard.card.CourseCardShapeType.SQUARE
import com.example.tutorplace.ui.common.coursecard.cardpager.CardPagerWithTitleAndSort
import com.example.tutorplace.ui.common.coursecard.cardpager.CardPagerWithTitleAndSortSkeleton
import com.example.tutorplace.ui.common.itemWithSkeleton
import com.example.tutorplace.ui.common.sectiontitle.model.SectionSortInfo
import com.example.tutorplace.ui.common.sectiontitle.model.SectionTitle
import com.example.tutorplace.ui.screens.coursedetailed.model.CourseDetailedParams
import com.example.tutorplace.ui.screens.home.presentation.HomeEffect
import com.example.tutorplace.ui.screens.home.presentation.HomeEffect.NavigateToCatalog
import com.example.tutorplace.ui.screens.home.presentation.HomeEffect.NavigateToCourseDetailed
import com.example.tutorplace.ui.screens.home.presentation.HomeEffect.NavigateToFortuneWheel
import com.example.tutorplace.ui.screens.home.presentation.HomeEffect.NavigateToFortuneWheelInformation
import com.example.tutorplace.ui.screens.home.presentation.HomeEffect.NavigateToMail
import com.example.tutorplace.ui.screens.home.presentation.HomeEffect.NavigateToMyCourses
import com.example.tutorplace.ui.screens.home.presentation.HomeEffect.NavigateToProfile
import com.example.tutorplace.ui.screens.home.presentation.HomeEffect.NavigateToSearch
import com.example.tutorplace.ui.screens.home.presentation.HomeEvent.UI
import com.example.tutorplace.ui.screens.home.presentation.HomeState
import com.example.tutorplace.ui.screens.home.presentation.HomeViewModel
import com.example.tutorplace.ui.screens.home.ui.fortunewheel.FortuneWheelShortItem
import com.example.tutorplace.ui.screens.home.ui.fortunewheel.FortuneWheelShortItemSkeleton
import com.example.tutorplace.ui.screens.home.ui.mycourses.MyCoursesEmptyItem
import com.example.tutorplace.ui.theme.ScreenColor
import kotlinx.coroutines.flow.Flow

@Composable
fun HomeScreen(navigator: Navigator) {
	val viewModel = hiltViewModel<HomeViewModel>()
	val state by viewModel.state.collectAsStateWithLifecycle()
	CollectEffects(viewModel.effect, navigator)
	HomeContent(
		state = state,
		onFortuneWheelClicked = { viewModel.onEvent(UI.FortuneWheelClicked) },
		onFortuneWheelInformationClicked = { viewModel.onEvent(UI.FortuneWheelInformationClicked) },
		onMyCoursesClicked = { viewModel.onEvent(UI.MyCoursesClicked) },
		onCatalogClicked = { viewModel.onEvent(UI.CatalogClicked) },
		onCourseClicked = { courseId -> viewModel.onEvent(UI.CourseClicked(courseId)) }
	)
}

@Composable
private fun HomeContent(
	state: HomeState,
	onFortuneWheelClicked: () -> Unit,
	onFortuneWheelInformationClicked: () -> Unit,
	onMyCoursesClicked: () -> Unit,
	onCatalogClicked: () -> Unit,
	onCourseClicked: (courseId: String) -> Unit,
) {
	Scaffold(containerColor = ScreenColor) { paddingValues ->
		LazyColumn(
			modifier = Modifier
				.fillMaxSize()
				.padding(paddingValues)
		) {
			itemWithSkeleton(
				key = "FortuneWheelShort",
				dataInfo = state.fortuneWheelLastRotation,
				paddingValues = PaddingValues(top = 8.dp),
				content = { lastRotationTime ->
					FortuneWheelShortItem(
						lastRotationTime = lastRotationTime,
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
				content = { courses ->
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
						courses = courses,
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
				content = { courses ->
					CardPagerWithTitleAndSort(
						sectionTitle = SectionTitle.NonClickable(text = stringResource(R.string.home_specially_for_you)),
						sort = null,
						courses = courses,
						shape = LARGE,
						onCourseClick = { course -> onCourseClicked(course.id) }
					)
				},
				skeletonContent = {
					CardPagerWithTitleAndSortSkeleton(shape = LARGE, withSort = false)
				},
				emptyStateContent = {}
			)
			item { Spacer(Modifier.height(94.dp)) }
		}
	}
}

@Composable
private fun CollectEffects(effect: Flow<HomeEffect>, navigator: Navigator) {
	LaunchedEffect(Unit) {
		effect.collect { homeEffect ->
			when (homeEffect) {
				is NavigateToCatalog -> navigator.navigate(Destinations.Catalog)
				is NavigateToCourseDetailed -> navigator.navigate(
					Destinations.CourseDetailed(CourseDetailedParams(homeEffect.courseId))
				)
				is NavigateToFortuneWheel -> navigator.navigate(Destinations.FortuneWheel)
				is NavigateToFortuneWheelInformation -> navigator.navigate(
					Destinations.FortuneWheel,
					Destinations.FortuneWheelInformation
				)
				is NavigateToMail -> navigator.navigate(Destinations.Mail)
				is NavigateToMyCourses -> navigator.navigate(Destinations.MyCourses)
				is NavigateToProfile -> navigator.navigate(Destinations.Profile)
				is NavigateToSearch -> navigator.navigate(Destinations.Search)
			}
		}
	}
}

@Preview
@Composable
private fun HomePreview() {
	HomeContent(
		state = HomeState(),
		onFortuneWheelClicked = {},
		onFortuneWheelInformationClicked = {},
		onMyCoursesClicked = {},
		onCatalogClicked = {},
		onCourseClicked = {}
	)
}