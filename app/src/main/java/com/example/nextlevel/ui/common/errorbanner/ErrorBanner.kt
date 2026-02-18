package com.example.nextlevel.ui.common.errorbanner

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import com.example.nextlevel.network.error.ErrorEvent
import com.example.nextlevel.network.error.ErrorEventBus
import com.example.nextlevel.ui.common.RoundedBottomCornerShape
import com.example.nextlevel.ui.theme.Red33
import com.example.nextlevel.ui.theme.White
import kotlinx.coroutines.delay

private const val BANNER_ANIMATION_DURATION_MS = 400
private const val BANNER_DISPLAY_DURATION_MS = 1000L

@Composable
fun ErrorBanner(
	errorEventBus: ErrorEventBus,
	modifier: Modifier = Modifier,
) {
	var currentEvent by remember { mutableStateOf<ErrorEvent?>(null) }
	var isVisible by remember { mutableStateOf(false) }

	LaunchedEffect(Unit) {
		errorEventBus.events.collect { event ->
			if (!isVisible) {
				currentEvent = event
				isVisible = true

				delay(BANNER_DISPLAY_DURATION_MS)

				isVisible = false
				delay(BANNER_ANIMATION_DURATION_MS.toLong())
				currentEvent = null
			}
		}
	}

	ErrorBannerContent(
		message = currentEvent?.message,
		isVisible = isVisible,
		modifier = modifier,
	)
}

@Composable
private fun ErrorBannerContent(
	message: String?,
	isVisible: Boolean,
	modifier: Modifier = Modifier,
) {
	AnimatedVisibility(
		visible = isVisible && message != null,
		modifier = modifier
			.fillMaxWidth()
			.zIndex(Float.MAX_VALUE),
		enter = slideInVertically(
			initialOffsetY = { fullHeight -> -fullHeight },
			animationSpec = tween(durationMillis = BANNER_ANIMATION_DURATION_MS),
		),
		exit = slideOutVertically(
			targetOffsetY = { fullHeight -> -fullHeight },
			animationSpec = tween(durationMillis = BANNER_ANIMATION_DURATION_MS),
		),
	) {
		Box(
			modifier = Modifier
				.fillMaxWidth()
				.background(Red33, shape = RoundedBottomCornerShape(20.dp))
				.statusBarsPadding()
				.padding(horizontal = 8.dp, vertical = 14.dp),
			contentAlignment = Alignment.Center,
		) {
			Text(
				text = message.orEmpty(),
				color = White,
				fontSize = 14.sp,
				fontWeight = FontWeight.Medium,
				textAlign = TextAlign.Center,
			)
		}
	}
}

@Preview(showBackground = true)
@Composable
private fun ErrorBannerPreview() {
	Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
		ErrorBannerContent(message = "Ошибка интернет соединения", isVisible = true)
		ErrorBannerContent(message = "Произошла неизвестная ошибка", isVisible = true)
	}
}
