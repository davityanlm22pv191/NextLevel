package com.example.tutorplace.ui.screens.main

import android.Manifest
import android.os.Build
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.ui.NavDisplay
import com.example.tutorplace.navigation.Navigator
import com.example.tutorplace.navigation.appEntryProvider
import com.example.tutorplace.navigation.bottomnavbar.BottomNavigationBarItems
import com.example.tutorplace.navigation.destinations.DestinationWithBottomBar
import com.example.tutorplace.navigation.destinations.DestinationWithToolbar
import com.example.tutorplace.navigation.destinations.Destinations
import com.example.tutorplace.navigation.rememberNavigationState
import com.example.tutorplace.navigation.strategy.BottomSheetSceneStrategy
import com.example.tutorplace.navigation.toEntries
import com.example.tutorplace.ui.common.BottomNavigationBar
import com.example.tutorplace.ui.common.RequestPermission
import com.example.tutorplace.ui.common.toolbar.ToolbarHeader
import com.example.tutorplace.ui.screens.main.presentation.MainScreenState
import com.example.tutorplace.ui.screens.main.presentation.MainScreenViewModel

@Composable
fun MainScreen(userIsAuthorized: Boolean) {
	val viewModel = hiltViewModel<MainScreenViewModel>()
	val state by viewModel.state.collectAsState()
	MainContent(state, userIsAuthorized)
}

@Composable
private fun MainContent(state: MainScreenState, userIsAuthorized: Boolean) {
	val navigationState = rememberNavigationState(
		startRoute = if (userIsAuthorized) Destinations.Home else Destinations.Authorization,
		topLevelRoutes = BottomNavigationBarItems.entries
			.map { topLevelRoute -> topLevelRoute.destination }
			.plus(Destinations.Authorization)
			.toSet()
	)
	val context = LocalContext.current
	val navigator = remember { Navigator(navigationState, context) }
	val bottomSheetStrategy = remember { BottomSheetSceneStrategy<NavKey>() }

	Scaffold(
		topBar = {
			val isToolbarVisible = navigationState.currentScreen is DestinationWithToolbar
			AnimatedContent(
				label = "toolbarHeader",
				targetState = isToolbarVisible,
			) { isVisible ->
				if (isVisible) {
					val config = (navigationState.currentScreen as? DestinationWithToolbar)?.config
						?: return@AnimatedContent
					ToolbarHeader(
						theme = config.theme,
						isArrowVisible = navigationState.currentScreen !in BottomNavigationBarItems.entries.map { topLevelRoute -> topLevelRoute.destination },
						screenName = stringResource(config.screenName),
						profileShortInfo = state.profileShortInfo,
						onBackClicked = { navigator.goBack() },
						onNotificationClicked = {
							if (!navigationState.isDestinationIsAlreadyOpen(Destinations.Mail)) {
								navigator.navigate(Destinations.Mail)
							}
						},
						onSearchClicked = {
							if (!navigationState.isDestinationIsAlreadyOpen(Destinations.Search)) {
								navigator.navigate(Destinations.Search)
							}
						},
						onProfileClicked = { navigator.navigate(Destinations.Profile) }
					)
				}
			}
		},
		bottomBar = {
			val isBottomBarVisible = navigationState.currentScreen is DestinationWithBottomBar
			AnimatedContent(
				label = "bottomBar",
				targetState = isBottomBarVisible
			) {
				if (it) {
					BottomNavigationBar(
						currentTopLevelRoute = navigationState.topLevelRoute,
						onClick = { bottomNavBarItemDestination ->
							navigator.navigate(bottomNavBarItemDestination)
						}
					)
				}
			}
		}
	) { paddingValues ->
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
			RequestPermission(Manifest.permission.POST_NOTIFICATIONS) {}
		}

		NavDisplay(
			modifier = Modifier.fillMaxSize(),
			onBack = { navigator.goBack() },
			sceneStrategy = bottomSheetStrategy,
			entries = navigationState.toEntries(appEntryProvider(navigator)),
			predictivePopTransitionSpec = {
				slideInHorizontally(
					initialOffsetX = { -it },
					animationSpec = tween(1000)
				) togetherWith slideOutHorizontally(
					targetOffsetX = { it },
					animationSpec = tween(1000)
				)
			}
		)
		// OpenOnboardingIfNeeded(navigator, params.isShouldShowOnboarding)
	}
}

@Composable
private fun OpenOnboardingIfNeeded(
	navigator: Navigator,
	isShouldShowOnboarding: Boolean
) {
	var onboardingNavigated by rememberSaveable { mutableStateOf(false) }
	LaunchedEffect(isShouldShowOnboarding) {
		if (isShouldShowOnboarding && !onboardingNavigated) {
			onboardingNavigated = true
			navigator.navigate(Destinations.Onboarding)
		}
	}
}

@Preview
@Composable
private fun MainScreenPreview() {
	MainContent(state = MainScreenState(), userIsAuthorized = false)
}