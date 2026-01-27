package com.example.tutorplace.ui.screens.main.presentation

import androidx.navigation3.runtime.NavKey
import com.example.tutorplace.data.profile.model.ProfileShortInfo
import com.example.tutorplace.domain.model.DataInfo
import com.example.tutorplace.ui.base.BaseState

data class MainScreenState(
	val startRoute: NavKey? = null,
	val profileShortInfo: DataInfo<ProfileShortInfo> = DataInfo.Loading,
) : BaseState
