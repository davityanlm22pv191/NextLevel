package com.example.nextlevel.ui.screens.home.presentation

import androidx.lifecycle.viewModelScope
import com.example.nextlevel.data.courses.CoursesService
import com.example.nextlevel.data.fortunewheel.FortuneWheelService
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
	private val fortuneWheelService: FortuneWheelService,
	private val coursesService: CoursesService,
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
			val response = fortuneWheelService.getLastRotation()
			if (response.isSuccessful) {
				onDomainEvent(FortuneWheelLoaded(response.body()!!.lastSpinTime))
			} else {
				onDomainEvent(FortuneWheelFailed(Throwable(response.message())))
			}
		}
	}

	private fun loadMyCourses() {
		viewModelScope.launch {
			onDomainEvent(Domain.MyCoursesLoading)
			val response = coursesService.getMyCourses()
			if (response.isSuccessful) {
				onDomainEvent(Domain.MyCoursesLoaded(response.body()!!.courses))
			} else {
				onDomainEvent(Domain.MyCoursesFailed(Throwable(response.message())))
			}
		}
	}

	private fun loadSpeciallyForYou() {
		viewModelScope.launch {
			onDomainEvent(Domain.SpeciallyForYouLoading)
			val response = coursesService.getSpeciallyForYou()
			if (response.isSuccessful) {
				onDomainEvent(Domain.SpeciallyForLoaded(response.body()!!.courses))
			} else {
				onDomainEvent(Domain.SpeciallyForFailed(Throwable(response.message())))
			}
		}
	}
}