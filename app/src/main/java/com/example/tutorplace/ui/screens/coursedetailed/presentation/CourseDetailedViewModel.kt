package com.example.tutorplace.ui.screens.coursedetailed.presentation

import androidx.lifecycle.viewModelScope
import com.example.tutorplace.data.courses.CoursesService
import com.example.tutorplace.ui.base.BaseViewModel
import com.example.tutorplace.ui.screens.coursedetailed.presentation.CourseDetailedEffect.NavigateToCertificate
import com.example.tutorplace.ui.screens.coursedetailed.presentation.CourseDetailedEffect.NavigateToDashboard
import com.example.tutorplace.ui.screens.coursedetailed.presentation.CourseDetailedEffect.NavigateToMaterialsForCourse
import com.example.tutorplace.ui.screens.coursedetailed.presentation.CourseDetailedEffect.NavigateToStartLesson
import com.example.tutorplace.ui.screens.coursedetailed.presentation.CourseDetailedEvent.AttachParams
import com.example.tutorplace.ui.screens.coursedetailed.presentation.CourseDetailedEvent.CertificateClicked
import com.example.tutorplace.ui.screens.coursedetailed.presentation.CourseDetailedEvent.CourseDetailedFailed
import com.example.tutorplace.ui.screens.coursedetailed.presentation.CourseDetailedEvent.CourseDetailedLoaded
import com.example.tutorplace.ui.screens.coursedetailed.presentation.CourseDetailedEvent.DashboardClicked
import com.example.tutorplace.ui.screens.coursedetailed.presentation.CourseDetailedEvent.MaterialsForCoursesClicked
import com.example.tutorplace.ui.screens.coursedetailed.presentation.CourseDetailedEvent.StartLessonClicked
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
			is CertificateClicked -> sendEffect(NavigateToCertificate)
			is DashboardClicked -> sendEffect(NavigateToDashboard)
			is MaterialsForCoursesClicked -> sendEffect(NavigateToMaterialsForCourse)
			is StartLessonClicked -> sendEffect(NavigateToStartLesson)
		}
	}

	private fun loadCourseDetailed(courseId: String) {
		viewModelScope.launch {
			val response = coursesService.getCourseDetailed(courseId)
			if (response.isSuccessful) {
				onEvent(CourseDetailedLoaded(response.body()!!))
			} else {
				onEvent(CourseDetailedFailed(Throwable(response.message())))
			}
		}
	}
}