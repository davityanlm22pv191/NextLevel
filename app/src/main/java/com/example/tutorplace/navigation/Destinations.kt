package com.example.tutorplace.navigation

sealed class Destinations(open val route: String) {

	sealed class AuthorizationFlow(override val route: String) : Destinations(route) {
		companion object {
			const val FLOW_ROUTE = "authorization_flow"
		}

		object Auth : AuthorizationFlow("auth")

		object RestorePassword : AuthorizationFlow("restore_password")

		object Registration : AuthorizationFlow("registration")
	}

	object Catalog : Destinations("catalog")
	object MyCourses : Destinations("my_courses")
	object Home : Destinations("home")
	object Tasks : Destinations("tasks")

	data class MainScreen(val params: MainScreenParams) : Destinations(route = params.toRoute()) {
		companion object {
			private const val ROUTE = "main"
			const val DEFAULT_ROUTE = "$ROUTE?isShouldShowOnboarding={isShouldShowOnboarding}"

			private fun MainScreenParams.toRoute() =
				"$ROUTE?isShouldShowOnboarding=${isShouldShowOnboarding}"
		}

		data class MainScreenParams(
			val isShouldShowOnboarding: Boolean = false
		)
	}

	object Onboarding : Destinations("onboarding")

	sealed class FortuneWheelFlow(override val route: String) : Destinations(route) {
		companion object {
			const val FLOW_ROUTE = "fortune_wheel_flow"
		}

		object FortuneWheel : FortuneWheelFlow("fortune_wheel")

		object FortuneWheelInformation : FortuneWheelFlow("fortune_wheel_information")
	}
}