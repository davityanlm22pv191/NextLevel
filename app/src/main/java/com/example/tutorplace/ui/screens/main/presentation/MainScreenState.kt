package com.example.tutorplace.ui.screens.main.presentation

import com.example.tutorplace.data.profile.model.ProfileShortInfo
import com.example.tutorplace.domain.model.DataInfo
import com.example.tutorplace.ui.base.BaseState

data class MainScreenState(
	val profileShortInfo: DataInfo<ProfileShortInfo> = DataInfo.Loading,
): BaseState
