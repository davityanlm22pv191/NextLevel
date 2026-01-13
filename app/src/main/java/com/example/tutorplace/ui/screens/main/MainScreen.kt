package com.example.tutorplace.ui.screens.main

import android.Manifest
import android.os.Build
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheetProperties
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.ui.NavDisplay
import com.example.tutorplace.R
import com.example.tutorplace.navigation.BottomSheetSceneStrategy
import com.example.tutorplace.navigation.Destinations
import com.example.tutorplace.navigation.NavBarItem
import com.example.tutorplace.navigation.Navigator
import com.example.tutorplace.navigation.rememberNavigationState
import com.example.tutorplace.navigation.toEntries
import com.example.tutorplace.ui.common.RequestPermission
import com.example.tutorplace.ui.screens.catalog.CatalogScreen
import com.example.tutorplace.ui.screens.coursedetailed.CourseDetailedScreen
import com.example.tutorplace.ui.screens.fortunewheel.fortunewheel.FortuneWheelScreen
import com.example.tutorplace.ui.screens.fortunewheel.fortunewheelinformation.FortuneWheelInformationScreen
import com.example.tutorplace.ui.screens.home.HomeScreen
import com.example.tutorplace.ui.screens.main.model.MainScreenParams
import com.example.tutorplace.ui.screens.main.presentation.MainScreenViewModel
import com.example.tutorplace.ui.screens.mycourses.MyCoursesScreen
import com.example.tutorplace.ui.screens.tasks.TasksScreen
import com.example.tutorplace.ui.theme.ContainerColor
import com.example.tutorplace.ui.theme.Grey82
import com.example.tutorplace.ui.theme.PurpleCC
import com.example.tutorplace.ui.theme.Transparent
import com.example.tutorplace.ui.theme.Typography

private val TOP_LEVEL_ROUTES = mapOf(
	Destinations.Catalog to NavBarItem(R.drawable.ic_catalog, R.string.tab_catalog_title),
	Destinations.MyCourses to NavBarItem(R.drawable.ic_play, R.string.tab_my_courses_title),
	Destinations.Home to NavBarItem(R.drawable.ic_home, R.string.tab_home_title),
	Destinations.Tasks to NavBarItem(R.drawable.ic_star, R.string.tab_tasks_title),
)

@Composable
fun MainScreen(
	navigator: Navigator,
	params: MainScreenParams,
) {
	val viewModel = hiltViewModel<MainScreenViewModel>()
	OpenOnboardingIfNeeded(navigator, params.isShouldShowOnboarding)
	MainContent()
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun MainContent() {
	val navigationState = rememberNavigationState(
		startRoute = Destinations.Home,
		topLevelRoutes = TOP_LEVEL_ROUTES.keys
	)
	val navigator = remember { Navigator(navigationState) }
	val bottomSheetStrategy = remember { BottomSheetSceneStrategy<NavKey>() }
	val entryProvider = entryProvider<NavKey> {
		entry<Destinations.FortuneWheel> { FortuneWheelScreen(navigator) }
		entry<Destinations.FortuneWheelInformation>(
			metadata = BottomSheetSceneStrategy.bottomSheet()
		) {
			FortuneWheelInformationScreen(navigator)
		}
		entry<Destinations.CourseDetailed> { courseDetailed ->
			CourseDetailedScreen(navigator, courseDetailed.params)
		}
		entry<Destinations.Catalog> { CatalogScreen(navigator) }
		entry<Destinations.Home> { HomeScreen(navigator) }
		entry<Destinations.MyCourses> { MyCoursesScreen(navigator) }
		entry<Destinations.Tasks> { TasksScreen(navigator) }
	}

	Scaffold(
		contentWindowInsets = WindowInsets(0, 0, 0, 0),
		bottomBar = {
			NavigationBar(
				modifier = Modifier
					.shadow(4.dp, RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp))
					.clip(RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp)),
				containerColor = ContainerColor,
			) {
				TOP_LEVEL_ROUTES.forEach { (destination, navBarItem) ->
					val isSelected = destination == navigationState.topLevelRoute
					NavigationBarItem(
						selected = isSelected,
						label = {
							Text(
								text = stringResource(navBarItem.label),
								style = Typography.bodySmall
							)
						},
						icon = {
							Icon(
								painter = painterResource(navBarItem.icon),
								contentDescription = null
							)
						},
						onClick = { navigator.navigate(destination) },
						colors = NavigationBarItemDefaults.colors(
							selectedIconColor = PurpleCC,
							unselectedIconColor = Grey82,
							selectedTextColor = PurpleCC,
							unselectedTextColor = Grey82,
							indicatorColor = Transparent
						)
					)
				}
			}
		}
	) { paddingValues ->
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
			RequestPermission(Manifest.permission.POST_NOTIFICATIONS) {}
		}
		NavDisplay(
			modifier = Modifier
				.fillMaxSize(),
			onBack = { navigator.goBack() },
			sceneStrategy = bottomSheetStrategy,
			entries = navigationState.toEntries(entryProvider),
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
	MainContent()
}