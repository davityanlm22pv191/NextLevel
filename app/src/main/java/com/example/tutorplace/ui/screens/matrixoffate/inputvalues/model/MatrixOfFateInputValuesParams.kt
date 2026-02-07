package com.example.tutorplace.ui.screens.matrixoffate.inputvalues.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

@Serializable
@Parcelize
data class MatrixOfFateInputValuesParams(
	val id: String
) : Parcelable