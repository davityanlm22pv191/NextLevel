package com.example.tutorplace.navigation

import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.togetherWith
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.ui.NavDisplay
import com.example.tutorplace.ui.screens.auth.authorization.AuthorizationScreen
import com.example.tutorplace.ui.screens.auth.registration.RegistrationScreen
import com.example.tutorplace.ui.screens.auth.restorepassword.RestorePasswordScreen
import com.example.tutorplace.ui.screens.main.MainScreen
import com.example.tutorplace.ui.screens.main.model.MainScreenParams
import com.example.tutorplace.ui.screens.onboarding.OnboardingScreen
import com.example.tutorplace.ui.screens.stub.StubScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppNavigationGraph(startRoute: NavKey) {
	val navigationState = rememberNavigationState(
		startRoute = startRoute,
		topLevelRoutes = setOf(
			Destinations.Authorization,
			Destinations.MainScreen(MainScreenParams(isShouldShowOnboarding = false))
		)
	)
	val navigator = remember { Navigator(navigationState) }
	val entryProvider = entryProvider<NavKey> {
		entry<Destinations.Onboarding>(
			metadata = BottomSheetSceneStrategy.bottomSheet(),
		) {
			OnboardingScreen(navigator)
		}
		entry<Destinations.MainScreen> { mainScreen -> MainScreen(navigator, mainScreen.params) }
		entry<Destinations.Authorization> { AuthorizationScreen(navigator) }
		entry<Destinations.RestorePassword> { RestorePasswordScreen(navigator) }
		entry<Destinations.Registration> { RegistrationScreen(navigator) }
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