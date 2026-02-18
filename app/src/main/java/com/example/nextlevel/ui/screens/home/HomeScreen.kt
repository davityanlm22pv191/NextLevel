package com.example.nextlevel.ui.screens.home

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
import com.example.nextlevel.R
import com.example.nextlevel.data.common.Sort
import com.example.nextlevel.data.common.SortOrder
import com.example.nextlevel.data.common.SortType.DATE_ADDED
import com.example.nextlevel.navigation.Navigator
import com.example.nextlevel.navigation.destinations.Destinations
import com.example.nextlevel.ui.common.BOTTOM_NAVIGATION_BAR_HEIGHT
import com.example.nextlevel.ui.common.coursecard.card.CourseCardShapeType.LARGE
import com.example.nextlevel.ui.common.coursecard.card.CourseCardShapeType.SQUARE
import com.example.nextlevel.ui.common.coursecard.cardpager.CardPagerWithTitleAndSort
import com.example.nextlevel.ui.common.coursecard.cardpager.CardPagerWithTitleAndSortSkeleton
import com.example.nextlevel.ui.common.lazyitems.itemWithSkeleton
import com.example.nextlevel.ui.common.sectiontitle.model.SectionSortInfo
import com.example.nextlevel.ui.common.sectiontitle.model.SectionTitle
import com.example.nextlevel.ui.common.toolbar.TOOLBAR_HEADER_HEIGHT
import com.example.nextlevel.ui.screens.coursedetailed.model.CourseDetailedParams
import com.example.nextlevel.ui.screens.home.presentation.HomeEffect
import com.example.nextlevel.ui.screens.home.presentation.HomeEffect.NavigateToCatalog
import com.example.nextlevel.ui.screens.home.presentation.HomeEffect.NavigateToCourseDetailed
import com.example.nextlevel.ui.screens.home.presentation.HomeEffect.NavigateToFortuneWheel
import com.example.nextlevel.ui.screens.home.presentation.HomeEffect.NavigateToFortuneWheelInformation
import com.example.nextlevel.ui.screens.home.presentation.HomeEffect.NavigateToMail
import com.example.nextlevel.ui.screens.home.presentation.HomeEffect.NavigateToMyCourses
import com.example.nextlevel.ui.screens.home.presentation.HomeEffect.NavigateToProfile
import com.example.nextlevel.ui.screens.home.presentation.HomeEffect.NavigateToSearch
import com.example.nextlevel.ui.screens.home.presentation.HomeEffect.ShowErrorMessage
import com.example.nextlevel.ui.screens.home.presentation.HomeEvent.UI
import com.example.nextlevel.ui.screens.home.presentation.HomeState
import com.example.nextlevel.ui.screens.home.presentation.HomeViewModel
import com.example.nextlevel.ui.screens.home.ui.fortunewheel.FortuneWheelShortItem
import com.example.nextlevel.ui.screens.home.ui.fortunewheel.FortuneWheelShortItemSkeleton
import com.example.nextlevel.ui.screens.home.ui.mycourses.MyCoursesEmptyItem
import com.example.nextlevel.ui.theme.ScreenColor
import kotlinx.coroutines.flow.Flow

@Composable
fun HomeScreen(navigator: Navigator, showError: (Throwable) -> Unit) {
	val viewModel = hiltViewModel<HomeViewModel>()
	val state by viewModel.state.collectAsStateWithLifecycle()
	CollectEffects(viewModel.effect, navigator, showError)
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
			item { Spacer(Modifier.height(TOOLBAR_HEADER_HEIGHT.dp)) }
			itemWithSkeleton(
				key = "FortuneWheelShort",
				dataInfo = state.fortuneWheelLastRotation,
				paddingValues = PaddingValues(top = 8.dp),
				content = { lastRotationTime ->
					FortuneWheelShortItem(
						modifier = Modifier.padding(horizontal = 4.dp),
						lastRotationTime = lastRotationTime,
						onInformationClick = { onFortuneWheelInformationClicked() },
						onItemClick = { onFortuneWheelClicked() }
					)
				},
				skeletonContent = {
					FortuneWheelShortItemSkeleton(modifier = Modifier.padding(horizontal = 4.dp))
				}
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
			item { Spacer(Modifier.height(BOTTOM_NAVIGATION_BAR_HEIGHT.dp)) }
		}
	}
}

@Composable
private fun CollectEffects(
	effect: Flow<HomeEffect>,
	navigator: Navigator,
	showError: (Throwable) -> Unit
) {
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
				is ShowErrorMessage -> showError(Throwable(homeEffect.message))
			}
		}
	}
}

@Preview(showSystemUi = true)
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