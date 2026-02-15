package com.example.nextlevel.ui.screens.matrixoffate.detailed.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

@Serializable
@Parcelize
data class MatrixOfFateDetailedParams(
	val id: String
) : Parcelable