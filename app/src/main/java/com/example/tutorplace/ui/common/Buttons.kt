package com.example.tutorplace.ui.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.tutorplace.ui.theme.GreyAC
import com.example.tutorplace.ui.theme.GreyF8
import com.example.tutorplace.ui.theme.PurpleCC
import com.example.tutorplace.ui.theme.PurpleD6
import com.example.tutorplace.ui.theme.PurpleD8
import com.example.tutorplace.ui.theme.Transparent
import com.example.tutorplace.ui.theme.Typography
import com.example.tutorplace.ui.theme.White

@Composable
fun PurpleButton(
	modifier: Modifier = Modifier,
	text: String,
	isLoading: Boolean = false,
	isEnabled: Boolean = true,
	onClick: () -> Unit
) {
	Button(
		modifier = modifier.height(50.dp),
		onClick = { if (!isLoading) onClick() },
		enabled = isEnabled,
		shape = RoundedCornerShape(12.dp),
		colors = ButtonColors(
			containerColor = PurpleCC,
			contentColor = White,
			disabledContainerColor = GreyF8,
			disabledContentColor = GreyAC
		)
	) {
		Box(modifier = Modifier, contentAlignment = Alignment.Center) {
			if (isLoading) {
				CircularProgressIndicator(
					modifier = Modifier.size(21.dp),
					color = White,
					strokeWidth = 2.dp,
				)
			} else {
				Text(text = text, style = Typography.bodyMedium)
			}
		}
	}
}

@Composable
fun TransparentButton(
	modifier: Modifier = Modifier,
	text: String,
	onClick: () -> Unit
) {
	Surface(
		modifier = modifier.height(50.dp),
		onClick = { onClick() },
		shape = RoundedCornerShape(12.dp),
		color = Transparent,
		content = {
			Text(
				modifier = Modifier.wrapContentSize(),
				text = text,
				style = Typography.bodyMedium.copy(color = PurpleCC),
				textAlign = TextAlign.Center
			)
		}
	)
}

@Composable
fun PurpleGradientButton(
	modifier: Modifier = Modifier,
	text: String,
	onClick: () -> Unit
) {
	Surface(
		modifier = modifier
			.height(50.dp)
			.background(
				brush = Brush.verticalGradient(colors = listOf(PurpleD6, PurpleD8)),
				shape = RoundedCornerShape(12.dp)
			),
		onClick = { onClick() },
		shape = RoundedCornerShape(12.dp),
		color = Transparent,
		content = {
			Text(
				modifier = Modifier.wrapContentSize(),
				text = text,
				style = Typography.bodyMedium.copy(color = White),
				textAlign = TextAlign.Center
			)
		}
	)
}

@Preview(backgroundColor = 0xFFFFFFFF, showBackground = true)
@Composable
private fun PurpleButtonPreview() {
	Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
		PurpleButton(
			modifier = Modifier
				.width(320.dp)
				.height(50.dp),
			text = "Войти",
			isLoading = true
		) { }
		PurpleButton(
			modifier = Modifier
				.width(320.dp)
				.height(50.dp),
			text = "Войти",
			isLoading = false
		) { }
		PurpleButton(
			modifier = Modifier
				.width(320.dp)
				.height(50.dp),
			text = "Войти",
			isEnabled = false
		) { }
		TransparentButton(
			modifier = Modifier
				.width(320.dp)
				.height(50.dp),
			text = "Пропустить",
		) { }
		PurpleGradientButton(
			modifier = Modifier
				.width(138.dp)
				.height(48.dp),
			text = "Начать урок"
		) { }
	}
}