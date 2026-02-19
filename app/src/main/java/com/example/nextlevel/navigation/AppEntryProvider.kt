package com.example.nextlevel.navigation

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entryProvider
import com.example.nextlevel.navigation.destinations.Destinations
import com.example.nextlevel.navigation.strategy.BottomSheetSceneStrategy
import com.example.nextlevel.ui.common.upscreenmessage.UpScreenMessages
import com.example.nextlevel.ui.screens.auth.authorization.AuthorizationScreen
import com.example.nextlevel.ui.screens.auth.registration.RegistrationScreen
import com.example.nextlevel.ui.screens.auth.restorepassword.RestorePasswordScreen
import com.example.nextlevel.ui.screens.catalog.CatalogScreen
import com.example.nextlevel.ui.screens.coursedetailed.CourseDetailedScreen
import com.example.nextlevel.ui.screens.fortunewheel.fortunewheel.FortuneWheelScreen
import com.example.nextlevel.ui.screens.fortunewheel.fortunewheelinformation.FortuneWheelInformationScreen
import com.example.nextlevel.ui.screens.home.HomeScreen
import com.example.nextlevel.ui.screens.mail.MailScreen
import com.example.nextlevel.ui.screens.matrixoffate.detailed.MatrixOfFateDetailedScreen
import com.example.nextlevel.ui.screens.matrixoffate.inputvalues.MatrixOfFateInputValuesScreen
import com.example.nextlevel.ui.screens.mycourses.MyCoursesScreen
import com.example.nextlevel.ui.screens.onboarding.OnboardingScreen
import com.example.nextlevel.ui.screens.stub.StubScreen
import com.example.nextlevel.ui.screens.tasks.TasksScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun appEntryProvider(
	navigator: Navigator,
	showMessage: (UpScreenMessages) -> Unit
) = entryProvider<NavKey> {
	entry<Destinations.Authorization> { AuthorizationScreen(navigator, showMessage) }
	entry<Destinations.RestorePassword> { RestorePasswordScreen(navigator) }
	entry<Destinations.Registration> { RegistrationScreen(navigator) }
	entry<Destinations.YandexAuthorization> { StubScreen() }
	entry<Destinations.Support> { StubScreen() }
	entry<Destinations.Terms> { StubScreen() }
	entry<Destinations.Offer> { StubScreen() }
	entry<Destinations.Onboarding>(
		metadata = BottomSheetSceneStrategy.bottomSheet(),
		content = { OnboardingScreen(navigator) },
	)
	entry<Destinations.FortuneWheel> { FortuneWheelScreen(navigator) }
	entry<Destinations.FortuneWheelInformation>(
		metadata = BottomSheetSceneStrategy.bottomSheet(),
		content = { FortuneWheelInformationScreen(navigator) }
	)
	entry<Destinations.CourseDetailed> { CourseDetailedScreen(navigator, it.params) }
	entry<Destinations.Search> { StubScreen() }
	entry<Destinations.Profile> { StubScreen() }
	entry<Destinations.Catalog> { CatalogScreen(navigator) }
	entry<Destinations.Home> { HomeScreen(navigator, showMessage) }
	entry<Destinations.MyCourses> { MyCoursesScreen(navigator) }
	entry<Destinations.Tasks> { TasksScreen(navigator) }
	entry<Destinations.Mail> { MailScreen(navigator) }
	entry<Destinations.MatrixOfFateInputValues>(
		metadata = BottomSheetSceneStrategy.bottomSheet(),
		content = { MatrixOfFateInputValuesScreen(navigator) },
	)
	entry<Destinations.MatrixOfFateDetailed> { MatrixOfFateDetailedScreen(it.params, navigator) }
}