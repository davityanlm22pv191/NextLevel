package com.example.nextlevel.ui.common.upscreenmessage

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
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.example.nextlevel.R
import com.example.nextlevel.ui.common.RoundedBottomCornerShape
import com.example.nextlevel.ui.common.upscreenmessage.UpScreenMessages.Companion.BANNER_DISPLAY_DURATION_MS
import com.example.nextlevel.ui.theme.Typography
import com.example.nextlevel.ui.theme.White
import kotlinx.coroutines.delay

private const val BANNER_ANIMATION_DURATION_MS = 400

@Composable
fun UpScreenMessage(
	modifier: Modifier = Modifier,
	message: UpScreenMessages,
	onDismiss: () -> Unit,
) {
	var isVisible by remember { mutableStateOf(false) }

	LaunchedEffect(Unit) {
		if (!isVisible) {
			isVisible = true
			delay(BANNER_DISPLAY_DURATION_MS)
			isVisible = false
			delay(BANNER_ANIMATION_DURATION_MS.toLong())
			onDismiss()
		}
	}

	UpScreenMessageContent(
	 	message = message.text,
		backgroundColor = message.backgroundColor,
		isVisible = isVisible,
		modifier = modifier,
	)
}

@Composable
fun UpScreenMessageContent(
	message: String?,
	backgroundColor: Brush,
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
				.background(backgroundColor, shape = RoundedBottomCornerShape(20.dp))
				.statusBarsPadding()
				.padding(horizontal = 8.dp, vertical = 16.dp),
			contentAlignment = Alignment.Center,
		) {
			Text(
				text = message.orEmpty(),
				style = Typography.labelMedium,
				color = White,
				fontWeight = FontWeight.Medium,
			)
		}
	}
}

@Preview(showBackground = true)
@Composable
private fun UpScreenMessagePreview() {
	val error = UpScreenMessages.Error("Ошибка интернет соединения")
	val info = UpScreenMessages.Info(stringResource(R.string.common_back_again_to_close_app))

	Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
		UpScreenMessageContent(
			message = error.text,
			backgroundColor = error.backgroundColor,
			isVisible = true
		)
		UpScreenMessageContent(
			message = info.text,
			backgroundColor = info.backgroundColor,
			isVisible = true
		)
	}
}
