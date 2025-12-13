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
import androidx.core.view.WindowInsetsControllerCompat
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.example.tutorplace.R
import com.example.tutorplace.data.profile.model.ProfileShortInfo
import com.example.tutorplace.domain.model.DataInfo
import com.example.tutorplace.ui.common.toolbar.ToolbarHeader
import com.example.tutorplace.ui.screens.coursedetailed.model.CourseDetailedParams
import com.example.tutorplace.ui.screens.coursedetailed.presentation.CourseDetailedState
import com.example.tutorplace.ui.screens.coursedetailed.presentation.CourseDetailedViewModel
import com.example.tutorplace.ui.theme.Black
import com.example.tutorplace.ui.theme.ScreenColor

@Composable
fun CourseDetailedScreen(navController: NavHostController, params: CourseDetailedParams) {
	val viewModel = hiltViewModel<CourseDetailedViewModel>()
	val state by viewModel.state.collectAsStateWithLifecycle()
	CourseDetailedScreen(
		state.profileShortInfo,
		onBackButtonClicked = { navController.popBackStack() },
		onNotificationClicked = {},
		onSearchClicked = {},
		onProfileClicked = {}
	)
}

@Composable
private fun CourseDetailedScreen(
	profileShortInfo: DataInfo<ProfileShortInfo?>,
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

		}
	}
}

@Preview
@Composable
private fun CourseDetailedScreenPreview() {
	CourseDetailedScreen(
		profileShortInfo = CourseDetailedState().profileShortInfo,
		onBackButtonClicked = {},
		onNotificationClicked = {},
		onSearchClicked = {},
		onProfileClicked = {}
	)
}