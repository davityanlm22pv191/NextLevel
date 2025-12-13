package com.example.tutorplace.ui.screens.coursedetailed

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.example.tutorplace.R
import com.example.tutorplace.ui.common.toolbar.ToolbarHeader
import com.example.tutorplace.ui.screens.coursedetailed.presentation.CourseDetailedState
import com.example.tutorplace.ui.screens.coursedetailed.presentation.CourseDetailedViewModel
import com.example.tutorplace.ui.theme.Black
import com.example.tutorplace.ui.theme.ScreenColor

@Composable
fun CourseDetailedScreen(navController: NavHostController) {
	val viewModel = hiltViewModel<CourseDetailedViewModel>()
	val state by viewModel.state.collectAsStateWithLifecycle()
	CourseDetailedScreen(
		state,
		onBackButtonClicked = { navController.popBackStack() },
		onNotificationClicked = {},
		onSearchClicked = {},
		onProfileClicked = {}
	)
}

@Composable
private fun CourseDetailedScreen(
	state: CourseDetailedState,
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
				unreadEmailCount = state.profileShortInfo.data?.unreadMessageCount ?: 0,
				profileImageUrl = state.profileShortInfo.data?.profileThumbUrl.orEmpty(),
				level = state.profileShortInfo.data?.level?.level ?: 0,
				progress = state.profileShortInfo.data?.level?.let { (_, currentAmount, target) ->
					currentAmount / target.toFloat()
				} ?: 0f,
				isArrowVisible = true,
				isLightAppearance = false,
				isTransparentBackground = true,
				isLoading = state.profileShortInfo.isLoading,
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

		}
	}
}

@Preview
@Composable
private fun CourseDetailedScreenPreview() {
	CourseDetailedScreen(
		state = CourseDetailedState(),
		onBackButtonClicked = {},
		onNotificationClicked = {},
		onSearchClicked = {},
		onProfileClicked = {}
	)
}