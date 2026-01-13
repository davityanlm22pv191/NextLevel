package com.example.tutorplace.navigation

import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.togetherWith
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.scene.DialogSceneStrategy
import androidx.navigation3.ui.NavDisplay
import com.example.tutorplace.ui.screens.auth.authorization.AuthorizationScreen
import com.example.tutorplace.ui.screens.auth.registration.RegistrationScreen
import com.example.tutorplace.ui.screens.auth.restorepassword.RestorePasswordScreen
import com.example.tutorplace.ui.screens.catalog.CatalogScreen
import com.example.tutorplace.ui.screens.coursedetailed.CourseDetailedScreen
import com.example.tutorplace.ui.screens.fortunewheel.fortunewheel.FortuneWheelScreen
import com.example.tutorplace.ui.screens.fortunewheel.fortunewheelinformation.FortuneWheelInformationScreen
import com.example.tutorplace.ui.screens.home.HomeScreen
import com.example.tutorplace.ui.screens.main.MainScreen
import com.example.tutorplace.ui.screens.main.model.MainScreenParams
import com.example.tutorplace.ui.screens.mycourses.MyCoursesScreen
import com.example.tutorplace.ui.screens.onboarding.OnboardingScreen
import com.example.tutorplace.ui.screens.stub.StubScreen
import com.example.tutorplace.ui.screens.tasks.TasksScreen

@Composable
fun AppNavigationGraph(startRoute: NavKey) {
	val navigationState = rememberNavigationState(
		startRoute = startRoute,
		topLevelRoutes = setOf(
			Destinations.Auth,
			Destinations.MainScreen(MainScreenParams(isShouldShowOnboarding = false))
		)
	)
	val navigator = remember { Navigator(navigationState) }
	val entryProvider = entryProvider<NavKey> {
		entry<Destinations.MainScreen> { mainScreen -> MainScreen(navigator, mainScreen.params) }
		entry<Destinations.Auth> { AuthorizationScreen(navigator) }
		entry<Destinations.RestorePassword> { RestorePasswordScreen(navigator) }
		entry<Destinations.Registration> { RegistrationScreen(navigator) }
		entry<Destinations.Onboarding>(metadata = DialogSceneStrategy.dialog()) {
			OnboardingScreen(navigator)
		}
		entry<Destinations.FortuneWheel> { FortuneWheelScreen(navigator) }
		entry<Destinations.FortuneWheelInformation> { FortuneWheelInformationScreen(navigator) }
		entry<Destinations.CourseDetailed> { courseDetailed ->
			CourseDetailedScreen(navigator, courseDetailed.params)
		}
		entry<Destinations.Catalog> { CatalogScreen(navigator) }
		entry<Destinations.Home> { HomeScreen(navigator) }
		entry<Destinations.MyCourses> { MyCoursesScreen(navigator) }
		entry<Destinations.Tasks> { TasksScreen(navigator) }
		entry<Destinations.Support> { StubScreen() }
		entry<Destinations.YandexAuthorization> { StubScreen() }
	}

	NavDisplay(
		entries = navigationState.toEntries(entryProvider),
		onBack = { navigator.goBack() },
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