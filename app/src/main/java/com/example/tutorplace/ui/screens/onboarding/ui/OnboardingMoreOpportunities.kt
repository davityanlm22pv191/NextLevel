package com.example.tutorplace.ui.screens.onboarding.ui

import androidx.compose.animation.core.animateIntAsState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
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
fun OnboardingMoreOpportunities(
	state: OnboardingState,
	columnScope: ColumnScope
) = with(columnScope) {
	val pagerState = rememberPagerState(
		initialPage = (state.onboardingInfo as DataInfo.Success).data.coursesCoverUrls.size / 2,
		pageCount = { state.onboardingInfo.data.coursesCoverUrls.size }
	)
	HorizontalPager(
		modifier = Modifier.height(200.dp),
		state = pagerState,
		pageSpacing = 12.dp,
		contentPadding = PaddingValues(horizontal = 40.dp)
	) { pageIndex ->
		val isSelected = pageIndex == pagerState.currentPage
		val height = animateIntAsState(if (isSelected) 200 else 160)
		AsyncImage(
			modifier = Modifier
				.fillMaxWidth()
				.height(height.value.dp)
				.clip(RoundedCornerShape(20.dp))
				.graphicsLayer(scaleX = 1.6f, scaleY = 1.6f),
			model = state.onboardingInfo.data.coursesCoverUrls[pageIndex],
			contentDescription = null,
			placeholder = ColorPainter(GreyD5),
			contentScale = ContentScale.Crop
		)
	}
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
@Composable
private fun OnboardingMoreOpportunitiesPreview() {
	Column {
		OnboardingMoreOpportunities(
			state = OnboardingState(
				step = OnboardingState.Step.MORE_OPPORTUNITIES
			),
			columnScope = this
		)
	}
}