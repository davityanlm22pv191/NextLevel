package com.example.tutorplace.navigation

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entryProvider
import com.example.tutorplace.navigation.destinations.Destinations
import com.example.tutorplace.navigation.strategy.BottomSheetSceneStrategy
import com.example.tutorplace.ui.screens.auth.authorization.AuthorizationScreen
import com.example.tutorplace.ui.screens.auth.registration.RegistrationScreen
import com.example.tutorplace.ui.screens.auth.restorepassword.RestorePasswordScreen
import com.example.tutorplace.ui.screens.catalog.CatalogScreen
import com.example.tutorplace.ui.screens.coursedetailed.CourseDetailedScreen
import com.example.tutorplace.ui.screens.fortunewheel.fortunewheel.FortuneWheelScreen
import com.example.tutorplace.ui.screens.fortunewheel.fortunewheelinformation.FortuneWheelInformationScreen
import com.example.tutorplace.ui.screens.home.HomeScreen
import com.example.tutorplace.ui.screens.mail.MailScreen
import com.example.tutorplace.ui.screens.matrixoffate.detailed.MatrixOfFateDetailedScreen
import com.example.tutorplace.ui.screens.matrixoffate.inputvalues.MatrixOfFateInputValuesScreen
import com.example.tutorplace.ui.screens.mycourses.MyCoursesScreen
import com.example.tutorplace.ui.screens.onboarding.OnboardingScreen
import com.example.tutorplace.ui.screens.stub.StubScreen
import com.example.tutorplace.ui.screens.tasks.TasksScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun appEntryProvider(navigator: Navigator) = entryProvider<NavKey> {
	entry<Destinations.Authorization> { AuthorizationScreen(navigator) }
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
	entry<Destinations.Home> { HomeScreen(navigator) }
	entry<Destinations.MyCourses> { MyCoursesScreen(navigator) }
	entry<Destinations.Tasks> { TasksScreen(navigator) }
	entry<Destinations.Mail> { MailScreen(navigator) }
	entry<Destinations.MatrixOfFateInputValues> { MatrixOfFateInputValuesScreen(navigator) }
	entry<Destinations.MatrixOfFateDetailed> { MatrixOfFateDetailedScreen(it.params, navigator) }
}