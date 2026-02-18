package com.example.nextlevel.ui.screens.main

import android.Manifest
import android.annotation.SuppressLint
import android.os.Build
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.slideOutVertically
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.ui.NavDisplay
import com.example.nextlevel.navigation.Navigator
import com.example.nextlevel.navigation.appEntryProvider
import com.example.nextlevel.navigation.bottomnavbar.BottomNavigationBarItems
import com.example.nextlevel.navigation.destinations.DestinationWithBottomBar
import com.example.nextlevel.navigation.destinations.DestinationWithToolbar
import com.example.nextlevel.navigation.destinations.Destinations
import com.example.nextlevel.navigation.rememberNavigationState
import com.example.nextlevel.navigation.strategy.BottomSheetSceneStrategy
import com.example.nextlevel.navigation.toEntries
import com.example.nextlevel.ui.common.BottomNavigationBar
import com.example.nextlevel.ui.common.RequestPermission
import com.example.nextlevel.ui.common.errorbanner.ErrorBanner
import com.example.nextlevel.ui.common.toolbar.ToolbarHeader
import com.example.nextlevel.ui.screens.main.presentation.MainScreenState
import com.example.nextlevel.ui.screens.main.presentation.MainScreenViewModel

private const val BAR_ANIMATIONS_DURATION_MS = 300

@Composable
fun MainScreen(userIsAuthorized: Boolean) {
	val viewModel = hiltViewModel<MainScreenViewModel>()
	val state by viewModel.state.collectAsStateWithLifecycle()
	MainContent(state, userIsAuthorized)
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
private fun MainContent(
	state: MainScreenState,
	userIsAuthorized: Boolean,
) {
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
	var showError by remember { mutableStateOf<Throwable?>(null) }

	Box(modifier = Modifier.fillMaxSize()) {
		Scaffold(
			topBar = {
				val isToolbarVisible = navigationState.currentScreen is DestinationWithToolbar
				AnimatedVisibility(
					label = "toolbarHeaderVisibility",
					visible = isToolbarVisible,
					enter = slideInVertically(
						initialOffsetY = { -it },
						animationSpec = tween(durationMillis = BAR_ANIMATIONS_DURATION_MS)
					),
					exit = slideOutVertically(
						targetOffsetY = { -it },
						animationSpec = tween(durationMillis = BAR_ANIMATIONS_DURATION_MS)
					)
				) {
					val config = (navigationState.currentScreen as? DestinationWithToolbar)?.config
						?: return@AnimatedVisibility
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
			},
			bottomBar = {
				val isBottomBarVisible = navigationState.currentScreen is DestinationWithBottomBar
				AnimatedVisibility(
					label = "bottomBarVisibility",
					visible = isBottomBarVisible,
					enter = slideInVertically(
						initialOffsetY = { fullHeight -> fullHeight },
						animationSpec = tween(durationMillis = BAR_ANIMATIONS_DURATION_MS)
					),
					exit = slideOutVertically(
						targetOffsetY = { fullHeight -> fullHeight },
						animationSpec = tween(durationMillis = BAR_ANIMATIONS_DURATION_MS)
					)
				) {
					BottomNavigationBar(
						currentTopLevelRoute = navigationState.topLevelRoute,
						onClick = { bottomNavBarItemDestination ->
							navigator.navigate(bottomNavBarItemDestination)
						}
					)
				}
			}
		) { _ ->
			if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
				RequestPermission(Manifest.permission.POST_NOTIFICATIONS) {}
			}

			NavDisplay(
				modifier = Modifier.fillMaxSize(),
				onBack = { navigator.goBack() },
				sceneStrategy = bottomSheetStrategy,
				entries = navigationState.toEntries(
					appEntryProvider(navigator) { throwable -> showError = throwable }
				),
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

		showError?.let { throwable ->
			ErrorBanner(
				modifier = Modifier.align(Alignment.TopCenter),
				throwable = throwable,
				onDismiss = { showError = null }
			)
		}
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