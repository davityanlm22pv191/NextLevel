package com.example.nextlevel.ui.screens.home.presentation

import android.app.Application
import androidx.lifecycle.viewModelScope
import com.example.nextlevel.R
import com.example.nextlevel.data.courses.CoursesService
import com.example.nextlevel.data.fortunewheel.FortuneWheelService
import com.example.nextlevel.domain.auth.LogoutHandler
import com.example.nextlevel.domain.auth.TokenRefresher
import com.example.nextlevel.domain.model.NetworkError
import com.example.nextlevel.network.error.ErrorEventBus
import com.example.nextlevel.network.error.NetworkResult
import com.example.nextlevel.network.error.safeApiCall
import com.example.nextlevel.ui.base.BaseViewModel
import com.example.nextlevel.ui.screens.home.presentation.HomeEvent.Domain
import com.example.nextlevel.ui.screens.home.presentation.HomeEvent.Domain.FortuneWheelFailed
import com.example.nextlevel.ui.screens.home.presentation.HomeEvent.Domain.FortuneWheelLoaded
import com.example.nextlevel.ui.screens.home.presentation.HomeEvent.Domain.FortuneWheelLoading
import com.example.nextlevel.ui.screens.home.presentation.HomeEvent.UI
import com.example.nextlevel.ui.screens.home.presentation.HomeEvent.UI.CatalogClicked
import com.example.nextlevel.ui.screens.home.presentation.HomeEvent.UI.CourseClicked
import com.example.nextlevel.ui.screens.home.presentation.HomeEvent.UI.FortuneWheelClicked
import com.example.nextlevel.ui.screens.home.presentation.HomeEvent.UI.FortuneWheelInformationClicked
import com.example.nextlevel.ui.screens.home.presentation.HomeEvent.UI.MyCoursesClicked
import com.example.nextlevel.ui.screens.home.presentation.HomeEvent.UI.NotificationClicked
import com.example.nextlevel.ui.screens.home.presentation.HomeEvent.UI.ProfileClicked
import com.example.nextlevel.ui.screens.home.presentation.HomeEvent.UI.SearchClicked
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
	private val application: Application,
	private val fortuneWheelService: FortuneWheelService,
	private val coursesService: CoursesService,
	private val tokenRefresher: TokenRefresher,
	private val logoutHandler: LogoutHandler,
	private val errorEventBus: ErrorEventBus,
) : BaseViewModel<HomeEvent, HomeState, HomeEffect>() {

	// Квадратные превью курсов
	// https://iili.io/KLGeEua.png
	// https://iili.io/KLMMUs1.png
	// https://iili.io/KLMW6Av.png
	// https://iili.io/KLMjuF1.png
	// https://iili.io/KLMwO5g.png

	// https://iili.io/KLM3TNt.png
	// https://iili.io/KLMfdR2.png
	// https://iili.io/KLMqVYx.png
	// https://iili.io/KLMCGOQ.png
	// https://iili.io/KLMxJHb.png
	// https://iili.io/KLMzcQV.png
	// https://iili.io/KLMISVV.png
	// https://iili.io/KLM7Qbn.png
	// https://iili.io/KLMazGf.png
	// https://iili.io/KLMl2eI.png

	init {
		loadFortuneWheelLastRotation()
		loadMyCourses()
		loadSpeciallyForYou()
	}

	override fun initialState() = HomeState()
	override fun onEvent(event: HomeEvent) = when (event) {
		is UI -> onUiEvent(event)
		is Domain -> onDomainEvent(event)
	}

	private fun onDomainEvent(event: Domain) {
		setState(HomeReducer.reduce(state.value, event))
	}

	private fun onUiEvent(event: UI) {
		when (event) {
			is NotificationClicked -> sendEffect(HomeEffect.NavigateToMail)
			is ProfileClicked -> sendEffect(HomeEffect.NavigateToProfile)
			is SearchClicked -> sendEffect(HomeEffect.NavigateToSearch)
			is FortuneWheelClicked -> sendEffect(HomeEffect.NavigateToFortuneWheel)
			is FortuneWheelInformationClicked -> sendEffect(HomeEffect.NavigateToFortuneWheelInformation)
			is CatalogClicked -> sendEffect(HomeEffect.NavigateToCatalog)
			is MyCoursesClicked -> sendEffect(HomeEffect.NavigateToMyCourses)
			is CourseClicked -> sendEffect(HomeEffect.NavigateToCourseDetailed(event.courseId))
		}
	}

	private fun loadFortuneWheelLastRotation() {
		viewModelScope.launch {
			onDomainEvent(FortuneWheelLoading)
			val result = safeApiCall(
				onRefreshToken = { tokenRefresher.refresh() },
				onLogout = { logoutHandler.onLogoutRequested() },
				apiCall = { fortuneWheelService.getLastRotation() },
			)
			when (result) {
				is NetworkResult.Success -> {
					onDomainEvent(FortuneWheelLoaded(result.data.lastSpinTime))
				}
				is NetworkResult.Error -> {
					onDomainEvent(FortuneWheelFailed(result.error.asThrowable()))
					handleNetworkError(result.error)
				}
			}
		}
	}

	private fun loadMyCourses() {
		viewModelScope.launch {
			onDomainEvent(Domain.MyCoursesLoading)
			val result = safeApiCall(
				onRefreshToken = { tokenRefresher.refresh() },
				onLogout = { logoutHandler.onLogoutRequested() },
				apiCall = { coursesService.getMyCourses() },
			)
			when (result) {
				is NetworkResult.Success -> {
					onDomainEvent(Domain.MyCoursesLoaded(result.data.courses))
				}
				is NetworkResult.Error -> {
					onDomainEvent(Domain.MyCoursesFailed(result.error.asThrowable()))
					handleNetworkError(result.error)
				}
			}
		}
	}

	private fun loadSpeciallyForYou() {
		viewModelScope.launch {
			onDomainEvent(Domain.SpeciallyForYouLoading)
			val result = safeApiCall(
				onRefreshToken = { tokenRefresher.refresh() },
				onLogout = { logoutHandler.onLogoutRequested() },
				apiCall = { coursesService.getSpeciallyForYou() },
			)
			when (result) {
				is NetworkResult.Success -> {
					onDomainEvent(Domain.SpeciallyForLoaded(result.data.courses))
				}
				is NetworkResult.Error -> {
					onDomainEvent(Domain.SpeciallyForFailed(result.error.asThrowable()))
					handleNetworkError(result.error)
				}
			}
		}
	}

	private fun handleNetworkError(error: NetworkError) {
		errorEventBus.sendForError(
			error = error,
			networkConnectionMessage = application.getString(R.string.error_network_connection),
			timeoutMessage = application.getString(R.string.error_timeout),
			unknownErrorMessage = application.getString(R.string.error_unknown),
		)
	}
}
