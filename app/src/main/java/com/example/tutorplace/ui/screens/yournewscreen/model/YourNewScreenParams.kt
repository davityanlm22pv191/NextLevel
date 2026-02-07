package com.example.tutorplace.ui.screens.yournewscreen.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

@Serializable
@Parcelize
data class YourNewScreenParams(
	val id: String
) : Parcelable