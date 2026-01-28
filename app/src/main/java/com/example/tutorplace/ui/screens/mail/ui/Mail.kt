package com.example.tutorplace.ui.screens.mail.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.tutorplace.R
import com.example.tutorplace.data.mail.model.Mail
import com.example.tutorplace.data.mail.model.MailType.GIFT
import com.example.tutorplace.data.mail.model.MailType.NOTIFICATION
import com.example.tutorplace.ui.theme.Black16
import com.example.tutorplace.ui.theme.GreyF8
import com.example.tutorplace.ui.theme.Red33
import com.example.tutorplace.ui.theme.Typography

@Composable
fun Mail(modifier: Modifier = Modifier, mail: Mail) {
	Box(
		modifier = modifier
			.fillMaxWidth()
			.background(GreyF8, shape = RoundedCornerShape(20.dp))
			.alpha(if (mail.isViewed) 0.5f else 1f)
	) {
		Row(
			modifier = Modifier
				.fillMaxWidth()
				.height(80.dp)
				.padding(horizontal = 16.dp),
			verticalAlignment = Alignment.CenterVertically
		) {
			Image(
				modifier = Modifier.clip(RoundedCornerShape(16.dp)),
				painter = when (mail.type) {
					NOTIFICATION -> painterResource(R.drawable.ic_notification_3d)
					GIFT -> painterResource(R.drawable.ic_gift_3d)
				},
				contentDescription = null
			)
			Column(
				modifier = Modifier.padding(start = 16.dp),
				verticalArrangement = Arrangement.spacedBy(4.dp)
			) {
				Text(
					modifier = Modifier.fillMaxWidth(),
					text = mail.title,
					style = Typography.labelMedium.copy(color = Black16)
				)
				Text(
					modifier = Modifier.fillMaxWidth(),
					text = mail.description,
					style = Typography.labelMedium.copy(color = Black16)
				)
			}
		}
		if (!mail.isViewed) {
			Box(
				modifier = Modifier
					.size(10.dp)
					.background(Red33, shape = CircleShape)
					.align(Alignment.TopEnd)
			)
		}
	}
}

@Preview
@Composable
private fun MailPreview() {
	Column(
		modifier = Modifier.fillMaxWidth(),
		verticalArrangement = Arrangement.spacedBy(8.dp),
	) {
		Mail(
			mail = Mail(
				id = "123",
				title = "Вам подарок",
				description = "Заберите свой долгожданный подарок",
				type = GIFT,
				isViewed = false
			)
		)
		Mail(
			mail = Mail(
				id = "456",
				title = "Вам подарок",
				description = "Заберите свой долгожданный подарок",
				type = GIFT,
				isViewed = true
			)
		)
		Mail(
			mail = Mail(
				id = "789",
				title = "Уведомление",
				description = "У вас одно непрочитанное сообщение",
				type = NOTIFICATION,
				isViewed = false
			)
		)
		Mail(
			mail = Mail(
				id = "149",
				title = "Уведомление",
				description = "У вас одно непрочитанное сообщение",
				type = NOTIFICATION,
				isViewed = true
			)
		)
	}
}