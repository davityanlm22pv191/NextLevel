package com.example.tutorplace.ui.screens.main.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

@Serializable
@Parcelize
data class MainScreenParams(
	val isShouldShowOnboarding: Boolean = false
) : Parcelable
