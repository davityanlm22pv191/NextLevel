package com.example.tutorplace.ui.screens.coursedetailed.presentation

import androidx.lifecycle.viewModelScope
import com.example.tutorplace.data.courses.CoursesService
import com.example.tutorplace.data.profile.storage.ProfileStorage
import com.example.tutorplace.ui.base.BaseViewModel
import com.example.tutorplace.ui.screens.coursedetailed.presentation.CourseDetailedEvent.SetProfileInfo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CourseDetailedViewModel @Inject constructor(
	private val coursesService: CoursesService,
	private val profileStorage: ProfileStorage,
) : BaseViewModel<CourseDetailedEvent, CourseDetailedState, CourseDetailedEffect>() {

	init {
		collectProfileShortInfo()
	}

	override fun initialState() = CourseDetailedState()

	override fun onEvent(event: CourseDetailedEvent) {
		when (event) {
			is SetProfileInfo -> setState(CourseDetailedReducer.reduce(state.value, event))
		}
	}

	private fun collectProfileShortInfo() {
		viewModelScope.launch {
			profileStorage.profileShortInfo.collect { value ->
				value?.let { profileShortInfo -> onEvent(SetProfileInfo(profileShortInfo)) }
			}
		}
	}
}