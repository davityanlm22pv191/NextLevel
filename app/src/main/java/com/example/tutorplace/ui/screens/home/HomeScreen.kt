package com.example.tutorplace.ui.screens.home

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.tutorplace.R
import com.example.tutorplace.navigation.Destinations.FortuneWheelFlow
import com.example.tutorplace.ui.common.toolbar.ToolbarHeader
import com.example.tutorplace.ui.screens.fortunewheel.fortunewheel.model.FortuneWheelParams
import com.example.tutorplace.ui.screens.home.presentation.HomeEffect
import com.example.tutorplace.ui.screens.home.presentation.HomeEvent.UI
import com.example.tutorplace.ui.screens.home.presentation.HomeState
import com.example.tutorplace.ui.screens.home.presentation.HomeViewModel
import com.example.tutorplace.ui.screens.home.ui.FortuneWheelShortItem
import com.example.tutorplace.ui.screens.home.ui.FortuneWheelShortItemSkeleton
import com.example.tutorplace.ui.screens.home.ui.mytraining.MyTrainingEmptyItem
import com.example.tutorplace.ui.theme.ScreenColor

@Composable
fun HomeScreen(navController: NavHostController) {
	val viewModel = hiltViewModel<HomeViewModel>()
	val state by viewModel.state.collectAsState()
	ObserveViewModelEvents(viewModel, navController)
	HomeScreen(
		state = state,
		onNotificationClicked = { viewModel.onEvent(UI.NotificationClicked) },
		onSearchClicked = { viewModel.onEvent(UI.SearchClicked) },
		onProfileClicked = { viewModel.onEvent(UI.ProfileClicked) },
		onFortuneWheelClicked = { viewModel.onEvent(UI.FortuneWheelClicked) },
		onFortuneWheelInformationClicked = { viewModel.onEvent(UI.FortuneWheelInformationClicked) },
		onCatalogClicked = {
			// TODO TP-22  CALL MAIN VIEW MODEL FOR SWITCH TAB TO CATALOG
		}
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
	onCatalogClicked: () -> Unit
) {
	Scaffold(
		topBar = {
			ToolbarHeader(
				modifier = Modifier,
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
			item {
				val isFortuneWheelSectionReady =
					!state.fortuneWheelLastRotation.isLoading && state.fortuneWheelLastRotation.throwable == null
				AnimatedContent(
					targetState = isFortuneWheelSectionReady,
					transitionSpec = {
						fadeIn(animationSpec = tween(durationMillis = 500)) togetherWith
								fadeOut(animationSpec = tween(durationMillis = 500))
					}) {
					if (it) {
						FortuneWheelShortItem(
							modifier = Modifier.padding(top = 8.dp),
							lastRotationTime = state.fortuneWheelLastRotation.data,
							onInformationClick = { onFortuneWheelInformationClicked() },
							onItemClick = { onFortuneWheelClicked() }
						)
					} else {
						FortuneWheelShortItemSkeleton(modifier = Modifier.padding(top = 8.dp))
					}
				}
			}
			item {
				MyTrainingEmptyItem(
					modifier = Modifier.padding(top = 8.dp),
					onCatalogClick = { onCatalogClicked() }
				)
			}
		}
	}
}

@Composable
private fun ObserveViewModelEvents(
	viewModel: HomeViewModel,
	navController: NavHostController
) {
	LaunchedEffect(Unit) {
		viewModel.effect.collect { effect ->
			when (effect) {
				HomeEffect.NavigateToMail -> navController
				HomeEffect.NavigateToProfile -> navController
				HomeEffect.NavigateToSearchScreen -> navController
				HomeEffect.NavigateToFortuneWheelInformationBottomSheet -> navController.navigate(
					FortuneWheelFlow.FortuneWheel(
						FortuneWheelParams(isShouldShowInformation = true)
					).route
				)
				HomeEffect.NavigateToFortuneWheelScreen -> navController.navigate(
					FortuneWheelFlow.FortuneWheel(
						FortuneWheelParams(isShouldShowInformation = false)
					).route
				)
			}
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
		onCatalogClicked = {}
	)
}