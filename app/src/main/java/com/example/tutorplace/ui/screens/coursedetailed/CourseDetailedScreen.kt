package com.example.tutorplace.ui.screens.coursedetailed

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.tutorplace.R
import com.example.tutorplace.data.courses.course.CourseDetailed
import com.example.tutorplace.data.profile.model.ProfileShortInfo
import com.example.tutorplace.domain.model.DataInfo
import com.example.tutorplace.domain.model.loaded
import com.example.tutorplace.navigation.Navigator
import com.example.tutorplace.ui.common.itemWithSkeleton
import com.example.tutorplace.ui.common.toolbar.ToolbarHeader
import com.example.tutorplace.ui.screens.coursedetailed.model.CourseDetailedParams
import com.example.tutorplace.ui.screens.coursedetailed.presentation.CourseDetailedEvent
import com.example.tutorplace.ui.screens.coursedetailed.presentation.CourseDetailedState
import com.example.tutorplace.ui.screens.coursedetailed.presentation.CourseDetailedViewModel
import com.example.tutorplace.ui.screens.coursedetailed.ui.CourseDetailedShortInfo
import com.example.tutorplace.ui.theme.Black
import com.example.tutorplace.ui.theme.ScreenColor

@Composable
fun CourseDetailedScreen(navigator: Navigator, params: CourseDetailedParams) {
	val viewModel = hiltViewModel<CourseDetailedViewModel>()
	LaunchedEffect(params.courseId) {
		viewModel.onEvent(CourseDetailedEvent.AttachParams(params))
	}
	val state by viewModel.state.collectAsStateWithLifecycle()

	CourseDetailedContent(
		state.profileShortInfo,
		state.courseDetailed,
		onBackButtonClicked = { navigator.goBack() },
		onNotificationClicked = {},
		onSearchClicked = {},
		onProfileClicked = {}
	)
}

@Composable
private fun CourseDetailedContent(
	profileShortInfo: DataInfo<ProfileShortInfo?>,
	courseDetailed: DataInfo<CourseDetailed?>,
	onBackButtonClicked: () -> Unit,
	onNotificationClicked: () -> Unit,
	onSearchClicked: () -> Unit,
	onProfileClicked: () -> Unit
) {
	Scaffold(
		topBar = {
			ToolbarHeader(
				modifier = Modifier.background(Black),
				screenName = stringResource(R.string.course_detailed_title),
				unreadEmailCount = profileShortInfo.data?.unreadMessageCount ?: 0,
				profileImageUrl = profileShortInfo.data?.profileThumbUrl.orEmpty(),
				level = profileShortInfo.data?.level?.level ?: 0,
				progress = profileShortInfo.data?.level?.let { (_, currentAmount, target) ->
					currentAmount / target.toFloat()
				} ?: 0f,
				isArrowVisible = true,
				isLightAppearance = false,
				isTransparentBackground = true,
				isLoading = profileShortInfo.isLoading,
				onBackClicked = { onBackButtonClicked() },
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
				key = "courseInfo",
				dataInfo = courseDetailed,
				content = {
					CourseDetailedShortInfo(
						course = courseDetailed.data ?: return@itemWithSkeleton,
						onStartLessonClicked = {},
						onMaterialsClicked = {}
					)
				},
				skeletonContent = {},
			)
		}
	}
}

@Preview
@Composable
private fun CourseDetailedContentPreview() {
	CourseDetailedContent(
		profileShortInfo = CourseDetailedState().profileShortInfo,
		courseDetailed = CourseDetailedState().courseDetailed.loaded(CourseDetailed.MOCK),
		onBackButtonClicked = {},
		onNotificationClicked = {},
		onSearchClicked = {},
		onProfileClicked = {}
	)
}