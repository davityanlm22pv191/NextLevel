package com.example.tutorplace.ui.screens.coursedetailed.presentation

import com.example.tutorplace.data.profile.model.ProfileShortInfo
import com.example.tutorplace.ui.base.BaseEvent

sealed interface CourseDetailedEvent : BaseEvent {
	data class SetProfileInfo(val profileShortInfo: ProfileShortInfo) : CourseDetailedEvent
}