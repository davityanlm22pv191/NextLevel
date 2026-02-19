package com.example.nextlevel.ui.screens.home.presentation

import androidx.lifecycle.viewModelScope
import com.example.nextlevel.domain.usecases.courses.GetMyCoursesUseCase
import com.example.nextlevel.domain.usecases.courses.GetSpeciallyForYouCoursesUseCase
import com.example.nextlevel.domain.usecases.fortunewheel.GetLastFortuneWheelRotationDateUseCase
import com.example.nextlevel.domain.usecases.profile.UpdateProfileShortInfoUseCase
import com.example.nextlevel.ui.base.BaseViewModel
import com.example.nextlevel.ui.screens.home.presentation.HomeEffect.NavigateToCatalog
import com.example.nextlevel.ui.screens.home.presentation.HomeEffect.NavigateToCourseDetailed
import com.example.nextlevel.ui.screens.home.presentation.HomeEffect.NavigateToFortuneWheel
import com.example.nextlevel.ui.screens.home.presentation.HomeEffect.NavigateToFortuneWheelInformation
import com.example.nextlevel.ui.screens.home.presentation.HomeEffect.NavigateToMail
import com.example.nextlevel.ui.screens.home.presentation.HomeEffect.NavigateToMyCourses
import com.example.nextlevel.ui.screens.home.presentation.HomeEffect.NavigateToProfile
import com.example.nextlevel.ui.screens.home.presentation.HomeEffect.NavigateToSearch
import com.example.nextlevel.ui.screens.home.presentation.HomeEffect.ShowErrorMessage
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
import com.example.nextlevel.ui.screens.home.presentation.HomeEvent.UI.Refresh
import com.example.nextlevel.ui.screens.home.presentation.HomeEvent.UI.SearchClicked
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
	private val getMyCoursesUseCase: GetMyCoursesUseCase,
	private val getLastFortuneWheelRotationDateUseCase: GetLastFortuneWheelRotationDateUseCase,
	private val getSpeciallyForYouCoursesUseCase: GetSpeciallyForYouCoursesUseCase,
	private val updateProfileShortInfoUseCase: UpdateProfileShortInfoUseCase,
) : BaseViewModel<HomeEvent, HomeState, HomeEffect>() {

	init {
		loadData(isShouldUpdateProfileInfo = false)
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
			is NotificationClicked -> sendEffect(NavigateToMail)
			is ProfileClicked -> sendEffect(NavigateToProfile)
			is SearchClicked -> sendEffect(NavigateToSearch)
			is FortuneWheelClicked -> sendEffect(NavigateToFortuneWheel)
			is FortuneWheelInformationClicked -> sendEffect(NavigateToFortuneWheelInformation)
			is CatalogClicked -> sendEffect(NavigateToCatalog)
			is MyCoursesClicked -> sendEffect(NavigateToMyCourses)
			is CourseClicked -> sendEffect(NavigateToCourseDetailed(event.courseId))
			is Refresh -> loadData(isShouldUpdateProfileInfo = true)
		}
	}

	private fun loadData(isShouldUpdateProfileInfo: Boolean) {
		if (isShouldUpdateProfileInfo) {
			updateProfileShortInfo()
		}
		loadFortuneWheelLastRotation()
		loadMyCourses()
		loadSpeciallyForYou()
	}

	private fun loadFortuneWheelLastRotation() {
		viewModelScope.launch {
			onDomainEvent(FortuneWheelLoading)
			getLastFortuneWheelRotationDateUseCase
				.execute()
				.onSuccess { lastSpinTime -> onDomainEvent(FortuneWheelLoaded(lastSpinTime)) }
				.onError { error ->
					sendEffect(ShowErrorMessage(error.getErrorMessage()))
					onDomainEvent(FortuneWheelFailed(error.asThrowable()))
				}
		}
	}

	private fun loadMyCourses() {
		viewModelScope.launch {
			onDomainEvent(Domain.MyCoursesLoading)
			getMyCoursesUseCase
				.execute()
				.onSuccess { courses -> onDomainEvent(Domain.MyCoursesLoaded(courses)) }
				.onError { error ->
					sendEffect(ShowErrorMessage((error.getErrorMessage())))
					onDomainEvent(Domain.MyCoursesFailed(error.asThrowable()))
				}
		}
	}

	private fun loadSpeciallyForYou() {
		viewModelScope.launch {
			onDomainEvent(Domain.SpeciallyForYouLoading)
			getSpeciallyForYouCoursesUseCase
				.execute()
				.onSuccess { courses -> onDomainEvent(Domain.SpeciallyForLoaded(courses)) }
				.onError { error ->
					sendEffect(ShowErrorMessage(error.getErrorMessage()))
					onDomainEvent(Domain.SpeciallyForFailed(error.asThrowable()))
				}
		}
	}

	private fun updateProfileShortInfo() {
		viewModelScope.launch {
			updateProfileShortInfoUseCase.execute()
		}
	}
}
