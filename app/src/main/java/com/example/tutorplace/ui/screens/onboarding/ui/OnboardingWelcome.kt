package com.example.tutorplace.ui.screens.onboarding.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.tutorplace.domain.model.DataInfo
import com.example.tutorplace.ui.screens.onboarding.presentation.OnboardingState
import com.example.tutorplace.ui.theme.GreyD5

@Composable
fun OnboardingWelcome(
	state: OnboardingState,
	columnScope: ColumnScope
) = with(columnScope) {
	AsyncImage(
		modifier = Modifier
			.fillMaxWidth()
			.height(200.dp)
			.padding(horizontal = 16.dp)
			.clip(RoundedCornerShape(20.dp))
			.graphicsLayer(scaleX = 1.6f, scaleY = 1.6f),
		model = if (state.onboardingInfo is DataInfo.Success) state.onboardingInfo.data.coverUrl else null,
		contentDescription = null,
		placeholder = ColorPainter(GreyD5),
		contentScale = ContentScale.Crop,
	)
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
@Composable
private fun OnboardingMainPreview() {
	Column {
		OnboardingWelcome(
			state = OnboardingState(step = OnboardingState.Step.WELCOME),
			columnScope = this
		)
	}
}