package com.example.nextlevel.ui.common.header

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.nextlevel.R
import com.example.nextlevel.ui.theme.Black16
import com.example.nextlevel.ui.theme.Typography

@Composable
fun Header(
	logo: HeaderLogoType,
	title: String,
	description: String?,
	throwable: Throwable? = null,
	onBackButtonClicked: (() -> Unit)?
) {
	Box(modifier = Modifier.fillMaxWidth()) {
		if (onBackButtonClicked != null) {
			Surface(
				modifier = Modifier.padding(top = 16.dp, start = 16.dp),
				shape = CircleShape,
				color = Color.Transparent,
				onClick = { onBackButtonClicked() }
			) {
				Image(
					painter = painterResource(R.drawable.ic_arrow_left_in_grey_f8_circle),
					contentDescription = null
				)
			}
		}
		Column {
			AnimatedContent(
				modifier = Modifier.fillMaxWidth(),
				targetState = logo
			) { logoType ->
				when (logoType) {
					is HeaderLogoType.Image -> Image(
						modifier = Modifier
							.size(100.dp)
							.padding(top = logoType.paddingTop.dp),
						painter = painterResource(logoType.image),
						contentDescription = null
					)
					is HeaderLogoType.Text -> Text(
						modifier = Modifier.fillMaxWidth(),
						text = stringResource(logoType.text),
						style = Typography.displayLarge,
						color = Black16,
						textAlign = TextAlign.Center
					)
					else -> {}
				}
			}

			Text(
				modifier = Modifier
					.fillMaxWidth()
					.padding(top = if (logo !is HeaderLogoType.None) 24.dp else 0.dp),
				text = title,
				style = Typography.headlineLarge,
				color = Black16,
				textAlign = TextAlign.Center,
			)
			AnimatedContent(targetState = description) { animatedDescription ->
				if (animatedDescription != null) {
					Text(
						modifier = Modifier
							.fillMaxWidth()
							.padding(horizontal = 16.dp)
							.padding(top = 8.dp),
						text = animatedDescription,
						style = Typography.labelMedium,
						color = Black16,
						textAlign = TextAlign.Center
					)
				}
			}
		}
	}
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
@Composable
private fun HeaderPreview() {
	Column(
		modifier = Modifier.padding(horizontal = 16.dp),
	) {
		Header(
			logo = HeaderLogoType.Image(R.drawable.ic_letter_n),
			title = "Восстановление пароля",
			description = "Введите свой email для восстановления пароля",
			onBackButtonClicked = {}
		)
		Header(
			logo = HeaderLogoType.Image(R.drawable.ic_letter_n),
			title = "Добро пожаловать на Tutor Place",
			description = "Введите свой email для восстановления пароля",
			onBackButtonClicked = null
		)
		Header(
			logo = HeaderLogoType.Text(R.string.onboarding_provide_details_logo),
			title = "Укажите данные для доступа к платформе",
			description = null,
			onBackButtonClicked = {}
		)
		Header(
			logo = HeaderLogoType.Text(R.string.onboarding_provide_details_logo),
			title = "Укажите данные для доступа к платформе",
			description = null,
			throwable = Throwable(),
			onBackButtonClicked = {}
		)
	}

}