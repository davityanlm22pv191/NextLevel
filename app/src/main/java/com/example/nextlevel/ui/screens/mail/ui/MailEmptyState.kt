package com.example.nextlevel.ui.screens.mail.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.nextlevel.R
import com.example.nextlevel.ui.theme.Black16
import com.example.nextlevel.ui.theme.GreyF8
import com.example.nextlevel.ui.theme.Typography

@Composable
fun MailEmptyState(
	modifier: Modifier = Modifier,
) {
	Column(
		modifier = modifier
			.fillMaxWidth()
			.background(GreyF8, shape = RoundedCornerShape(20.dp))
			.padding(16.dp),
		verticalArrangement = Arrangement.spacedBy(8.dp),
		horizontalAlignment = Alignment.CenterHorizontally
	) {
		Text(
			text = stringResource(R.string.mail_here_will_you_mails),
			style = Typography.headlineLarge.copy(color = Black16),
			textAlign = TextAlign.Center
		)
		Text(
			text = stringResource(R.string.mail_you_do_not_have_messages_right_now),
			style = Typography.labelMedium.copy(color = Black16),
			textAlign = TextAlign.Center
		)
	}
}

@Preview
@Composable
private fun MailEmptyStatePreview() {
	MailEmptyState()
}