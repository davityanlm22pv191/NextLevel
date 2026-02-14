package com.example.nextlevel.ui.screens.main.presentation

import com.example.nextlevel.data.profile.model.ProfileShortInfo
import com.example.nextlevel.domain.model.DataInfo
import com.example.nextlevel.ui.base.BaseState

data class MainScreenState(
	val profileShortInfo: DataInfo<ProfileShortInfo> = DataInfo.Loading,
) : BaseState
