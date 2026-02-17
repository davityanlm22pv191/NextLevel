package com.example.nextlevel.ui.screens.coursedetailed.presentation

import androidx.lifecycle.viewModelScope
import com.example.nextlevel.data.courses.CoursesService
import com.example.nextlevel.ui.base.BaseViewModel
import com.example.nextlevel.ui.screens.coursedetailed.presentation.CourseDetailedEffect.NavigateToCertificateDetailed
import com.example.nextlevel.ui.screens.coursedetailed.presentation.CourseDetailedEffect.NavigateToDashboardDetailed
import com.example.nextlevel.ui.screens.coursedetailed.presentation.CourseDetailedEffect.NavigateToMaterialsForCourse
import com.example.nextlevel.ui.screens.coursedetailed.presentation.CourseDetailedEffect.NavigateToStartLesson
import com.example.nextlevel.ui.screens.coursedetailed.presentation.CourseDetailedEvent.AttachParams
import com.example.nextlevel.ui.screens.coursedetailed.presentation.CourseDetailedEvent.CertificateClicked
import com.example.nextlevel.ui.screens.coursedetailed.presentation.CourseDetailedEvent.CourseDetailedFailed
import com.example.nextlevel.ui.screens.coursedetailed.presentation.CourseDetailedEvent.CourseDetailedLoaded
import com.example.nextlevel.ui.screens.coursedetailed.presentation.CourseDetailedEvent.DashboardClicked
import com.example.nextlevel.ui.screens.coursedetailed.presentation.CourseDetailedEvent.MaterialsForCoursesClicked
import com.example.nextlevel.ui.screens.coursedetailed.presentation.CourseDetailedEvent.StartLessonClicked
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CourseDetailedViewModel @Inject constructor(
	private val coursesService: CoursesService,
) : BaseViewModel<CourseDetailedEvent, CourseDetailedState, CourseDetailedEffect>() {

	override fun initialState() = CourseDetailedState()

	override fun onEvent(event: CourseDetailedEvent) {
		when (event) {
			is CourseDetailedFailed,
			is CourseDetailedLoaded -> setState(CourseDetailedReducer.reduce(state.value, event))
			is AttachParams -> loadCourseDetailed(event.params.courseId)
			is CertificateClicked -> sendEffect(NavigateToCertificateDetailed)
			is DashboardClicked -> sendEffect(NavigateToDashboardDetailed)
			is MaterialsForCoursesClicked -> sendEffect(NavigateToMaterialsForCourse)
			is StartLessonClicked -> sendEffect(NavigateToStartLesson)
		}
	}

	private fun loadCourseDetailed(courseId: String) {
		viewModelScope.launch {
			try {
				val response = coursesService.getCourseDetailed(courseId)
				if (response.isSuccessful) {
					onEvent(CourseDetailedLoaded(response.body()!!))
				} else {
					onEvent(CourseDetailedFailed(Throwable(response.message())))
				}
			} catch (e: Exception) {
				onEvent(CourseDetailedFailed(e))
			}
		}
	}
}