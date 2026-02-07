package com.example.tutorplace.ui.screens.home.ui.fortunewheel

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tutorplace.R
import com.example.tutorplace.ui.theme.Black16
import com.example.tutorplace.ui.theme.Blue36Alpha90
import com.example.tutorplace.ui.theme.BlueCE
import com.example.tutorplace.ui.theme.Green22
import com.example.tutorplace.ui.theme.PurpleC3
import com.example.tutorplace.ui.theme.Typography
import com.example.tutorplace.ui.theme.White
import kotlinx.coroutines.delay
import java.time.Duration
import java.time.LocalDateTime

@Composable
fun FortuneWheelShortItem(
	modifier: Modifier = Modifier,
	lastRotationTime: LocalDateTime,
	onInformationClick: () -> Unit,
	onItemClick: () -> Unit,
) {
	var durationTime by remember {
		mutableStateOf(
			run {
				val sinceLastRotation = Duration.between(lastRotationTime, LocalDateTime.now())
				val remaining = Duration.ofDays(1).minus(sinceLastRotation)
				if (remaining.isNegative) Duration.ZERO else remaining
			}
		)
	}

	LaunchedEffect(Unit) {
		while (durationTime > Duration.ZERO) {
			delay(1000L)
			durationTime = durationTime.minusSeconds(1)
		}
	}

	Box(
		modifier = modifier
			.fillMaxWidth()
			.clip(RoundedCornerShape(16.dp))
			.clickable { onItemClick() },
	) {
		Image(
			modifier = Modifier.matchParentSize(),
			painter = painterResource(R.drawable.bg_fortune_wheel_short_item),
			contentDescription = null,
			contentScale = ContentScale.Crop
		)
		Column(
			modifier = Modifier.padding(
				top = 20.dp,
				start = 20.dp,
				end = 5.dp,
				bottom = 16.dp
			)
		) {
			Text(
				modifier = Modifier.fillMaxWidth(),
				text = stringResource(R.string.fortune_wheel_try_your_luck),
				style = Typography.displayMedium.copy(color = White, fontWeight = FontWeight.Normal)
			)
			Text(
				modifier = Modifier
					.fillMaxWidth()
					.padding(top = 2.dp),
				text = stringResource(R.string.fortune_wheel),
				style = Typography.labelMedium.copy(color = White, fontWeight = FontWeight.Normal)
			)
			AnimatedContent(
				targetState = durationTime.isZero or durationTime.isNegative
			) {
				val alpha = animateFloatAsState(
					targetValue = if (it) 0f else 1f,
					label = "free_spin_through_alpha"
				)
				FreeSpinThrough(
					modifier = Modifier
						.padding(top = 4.dp)
						.alpha(alpha.value),
					remainingTime = durationTime
				)
			}

		}
		Image(
			modifier = Modifier
				.align(Alignment.TopEnd)
				.padding(top = 12.dp, end = 12.dp)
				.clip(CircleShape)
				.clickable { onInformationClick() },
			painter = painterResource(R.drawable.ic_info_in_grey_f8_circle),
			contentDescription = null
		)
	}
}

@Composable
private fun FreeSpinThrough(modifier: Modifier = Modifier, remainingTime: Duration) {
	Row(
		modifier = modifier
			.fillMaxWidth()
			.background(color = Blue36Alpha90, shape = RoundedCornerShape(20.dp))
			.padding(vertical = 4.dp)
			.padding(start = 12.dp, end = 8.dp),
		verticalAlignment = Alignment.CenterVertically
	) {
		Text(
			modifier = Modifier.padding(vertical = 2.dp),
			text = stringResource(R.string.fortune_wheel_free_spin_through),
			style = Typography.bodySmall.copy(color = White)
		)
		Spacer(modifier = Modifier.weight(1f))
		TimerCounter(value = remainingTime.toHours().toInt(), amount = 24)
		Text(
			modifier = Modifier.padding(2.dp),
			text = ":",
			style = Typography.bodyMedium.copy(color = White, fontWeight = FontWeight.SemiBold)
		)
		TimerCounter(value = (remainingTime.toMinutes() % 60).toInt(), amount = 60)
		Text(
			modifier = Modifier.padding(2.dp),
			text = ":",
			style = Typography.bodyMedium.copy(color = White, fontWeight = FontWeight.SemiBold)
		)
		TimerCounter(value = (remainingTime.seconds % 60).toInt(), amount = 60)
		Spacer(modifier = Modifier.weight(1f))
		Surface(
			modifier = Modifier.height(50.dp),
			shape = RoundedCornerShape(12.dp),
			color = Green22,
			content = {
				Text(
					modifier = Modifier
						.wrapContentSize()
						.padding(horizontal = 30.dp),
					text = stringResource(R.string.fortune_wheel_play),
					style = Typography.labelMedium.copy(
						color = Black16,
						fontWeight = FontWeight.SemiBold
					),
					textAlign = TextAlign.Center
				)
			}
		)
	}
}

@Composable
fun GradientCircularProgress(progress: Float, size: Dp, strokeWidth: Dp) {
	val gradient = Brush.sweepGradient(colors = listOf(PurpleC3, BlueCE))

	Canvas(modifier = Modifier.size(size)) {
		drawArc(
			brush = gradient,
			startAngle = -90f,
			sweepAngle = -360 * progress,
			useCenter = false,
			style = Stroke(width = strokeWidth.toPx(), cap = StrokeCap.Round)
		)
	}
}


@Composable
private fun TimerCounter(value: Int, amount: Int) {
	Box(contentAlignment = Alignment.Center) {
		GradientCircularProgress(
			progress = value / amount.toFloat(),
			size = 36.dp,
			strokeWidth = 2.dp
		)
		Text(
			text = value.toString(),
			style = Typography.bodyMedium.copy(
				color = White,
				fontSize = 14.sp,
				fontWeight = FontWeight.SemiBold
			)
		)
	}
}

@RequiresApi(Build.VERSION_CODES.S)
@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
@Composable
private fun FortuneWheelShortItemPreview() {
	FortuneWheelShortItem(
		lastRotationTime = LocalDateTime.now(),
		onInformationClick = {},
		onItemClick = {}
	)
}