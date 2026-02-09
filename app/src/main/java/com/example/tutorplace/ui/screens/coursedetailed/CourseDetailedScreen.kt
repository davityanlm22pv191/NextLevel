package com.example.tutorplace.ui.screens.coursedetailed

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.tutorplace.R
import com.example.tutorplace.data.courses.course.CourseDetailed
import com.example.tutorplace.domain.model.DataInfo
import com.example.tutorplace.navigation.Navigator
import com.example.tutorplace.navigation.destinations.DestinationWithToolbar
import com.example.tutorplace.navigation.destinations.Destinations
import com.example.tutorplace.ui.common.lazyitems.itemWithSkeleton
import com.example.tutorplace.ui.common.toolbar.TOOLBAR_HEADER_HEIGHT
import com.example.tutorplace.ui.screens.coursedetailed.model.CourseDetailedParams
import com.example.tutorplace.ui.screens.coursedetailed.presentation.CourseDetailedEffect
import com.example.tutorplace.ui.screens.coursedetailed.presentation.CourseDetailedEffect.NavigateToCertificateDetailed
import com.example.tutorplace.ui.screens.coursedetailed.presentation.CourseDetailedEffect.NavigateToDashboardDetailed
import com.example.tutorplace.ui.screens.coursedetailed.presentation.CourseDetailedEffect.NavigateToMaterialsForCourse
import com.example.tutorplace.ui.screens.coursedetailed.presentation.CourseDetailedEffect.NavigateToStartLesson
import com.example.tutorplace.ui.screens.coursedetailed.presentation.CourseDetailedEvent.AttachParams
import com.example.tutorplace.ui.screens.coursedetailed.presentation.CourseDetailedEvent.CertificateClicked
import com.example.tutorplace.ui.screens.coursedetailed.presentation.CourseDetailedEvent.DashboardClicked
import com.example.tutorplace.ui.screens.coursedetailed.presentation.CourseDetailedEvent.MaterialsForCoursesClicked
import com.example.tutorplace.ui.screens.coursedetailed.presentation.CourseDetailedEvent.StartLessonClicked
import com.example.tutorplace.ui.screens.coursedetailed.presentation.CourseDetailedState
import com.example.tutorplace.ui.screens.coursedetailed.presentation.CourseDetailedViewModel
import com.example.tutorplace.ui.screens.coursedetailed.ui.CourseAction
import com.example.tutorplace.ui.screens.coursedetailed.ui.CourseActionSkeleton
import com.example.tutorplace.ui.screens.coursedetailed.ui.CourseDetailedShortInfo
import com.example.tutorplace.ui.screens.coursedetailed.ui.model.BackgroundPattern.DASH_LINES
import com.example.tutorplace.ui.screens.coursedetailed.ui.model.BackgroundPattern.GEOMETRIC_FIGURES
import com.example.tutorplace.ui.theme.Black
import com.example.tutorplace.ui.theme.Black34
import com.example.tutorplace.ui.theme.ScreenColor
import kotlinx.coroutines.flow.Flow

@Composable
fun CourseDetailedScreen(navigator: Navigator, params: CourseDetailedParams) {
	val viewModel = hiltViewModel<CourseDetailedViewModel>()
	LaunchedEffect(params.courseId) {
		viewModel.onEvent(AttachParams(params))
	}
	val state by viewModel.state.collectAsStateWithLifecycle()
	CollectEffects(viewModel.effect, navigator)
	CourseDetailedContent(
		state,
		onMaterialsForCourseClicked = { viewModel.onEvent(MaterialsForCoursesClicked) },
		onStartLessonClicked = { viewModel.onEvent(StartLessonClicked) },
		onDashboardClicked = { viewModel.onEvent(DashboardClicked) },
		onCertificateClicked = { viewModel.onEvent(CertificateClicked) }
	)
}

@Composable
private fun CourseDetailedContent(
	state: CourseDetailedState,
	onStartLessonClicked: () -> Unit,
	onMaterialsForCourseClicked: () -> Unit,
	onDashboardClicked: () -> Unit,
	onCertificateClicked: () -> Unit
) {
	Scaffold(containerColor = ScreenColor) { paddingValues ->
		LazyVerticalGrid(
			modifier = Modifier
				.fillMaxSize()
				.padding(paddingValues),
			columns = GridCells.Fixed(2),
			horizontalArrangement = Arrangement.spacedBy(8.dp)
		) {
			item(
				span = { GridItemSpan(2) }
			) {
				AnimatedVisibility(state.courseDetailed is DataInfo.Loading) {
					Spacer(Modifier.height(TOOLBAR_HEADER_HEIGHT.dp))
				}
			}
			item(span = { GridItemSpan(2) }) {
				AnimatedVisibility(visible = state.courseDetailed is DataInfo.Success) {
					CourseDetailedShortInfo(
						modifier = Modifier
							.background(
								brush = Brush.verticalGradient(colors = listOf(Black, Black34)),
								shape = RoundedCornerShape(bottomStart = 20.dp, bottomEnd = 20.dp)
							)
							.padding(top = TOOLBAR_HEADER_HEIGHT.dp),
						course = (state.courseDetailed as DataInfo.Success).data,
						onStartLessonClicked = onStartLessonClicked,
						onMaterialsForCourseClicked = onMaterialsForCourseClicked
					)
				}
			}
			item(span = { GridItemSpan(2) }) { Spacer(Modifier.height(18.dp)) }
			itemWithSkeleton(
				key = "dashboard",
				contentType = "dashoard",
				span = GridItemSpan(1),
				dataInfo = state.courseDetailed,
				content = { courseDetailed ->
					if (courseDetailed.dashboard != null) {
						CourseAction(
							modifier = Modifier.padding(start = 16.dp),
							title = courseDetailed.dashboard.title,
							tag = stringResource(R.string.course_detailed_dashboard),
							tagLeadingIcon = null,
							backgroundPattern = DASH_LINES,
							onClicked = onDashboardClicked
						)
					}
				},
				emptyStateContent = {},
				skeletonContent = {
					CourseActionSkeleton(
						modifier = Modifier.padding(start = 16.dp),
						backgroundPattern = DASH_LINES
					)
				}
			)
			itemWithSkeleton(
				key = "certificate",
				contentType = "certificate",
				span = GridItemSpan(1),
				dataInfo = state.courseDetailed,
				content = { courseDetailed ->
					if (courseDetailed.dashboard != null) {
						CourseAction(
							modifier = Modifier.padding(end = 16.dp),
							title = stringResource(R.string.course_detailed_certificate),
							tag = "1/21",
							tagLeadingIcon = R.drawable.ic_check_inside_progress_circle,
							backgroundPattern = GEOMETRIC_FIGURES,
							onClicked = onCertificateClicked
						)
					}
				},
				emptyStateContent = {},
				skeletonContent = {
					CourseActionSkeleton(
						modifier = Modifier.padding(end = 16.dp),
						backgroundPattern = GEOMETRIC_FIGURES
					)
				}
			)
		}
	}
}

@Composable
private fun CollectEffects(effect: Flow<CourseDetailedEffect>, navigator: Navigator) {
	LaunchedEffect(Unit) {
		effect.collect { effect ->
			when (effect) {
				NavigateToCertificateDetailed -> {}
				NavigateToDashboardDetailed -> navigator.navigate(
					Destinations.MatrixOfFateInputValues((navigator.state.currentScreen as DestinationWithToolbar).config)
				)
				NavigateToMaterialsForCourse -> {}
				NavigateToStartLesson -> {}
			}
		}
	}
}

@Preview
@Composable
private fun CourseDetailedContentPreview() {
	CourseDetailedContent(
		CourseDetailedState(courseDetailed = DataInfo.Success(CourseDetailed.MOCK)),
		onMaterialsForCourseClicked = {},
		onStartLessonClicked = {},
		onDashboardClicked = {},
		onCertificateClicked = {}
	)
}