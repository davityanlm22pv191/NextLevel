package com.example.nextlevel.ui.screens.mail.presentation

import com.example.nextlevel.data.mail.model.Mail
import com.example.nextlevel.domain.model.DataInfo
import com.example.nextlevel.ui.base.BaseState

data class MailState(
	val mails: DataInfo<List<Mail>> = DataInfo.Loading,
	val skeletonItemsCount: DataInfo<Int> = DataInfo.Loading,
) : BaseState