package com.example.tutorplace.ui.screens.mail.presentation

import com.example.tutorplace.data.mail.model.Mail
import com.example.tutorplace.data.profile.model.ProfileShortInfo
import com.example.tutorplace.domain.model.DataInfo
import com.example.tutorplace.ui.base.BaseState

data class MailState(
	val mails: DataInfo<List<Mail>> = DataInfo.Loading
) : BaseState