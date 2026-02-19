package com.example.nextlevel.ui.screens.coursedetailed.ui

import androidx.annotation.DrawableRes
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.nextlevel.R
import com.example.nextlevel.ui.common.SkeletonShimmer
import com.example.nextlevel.ui.screens.coursedetailed.ui.model.BackgroundPattern
import com.example.nextlevel.ui.screens.coursedetailed.ui.model.BackgroundPattern.DASH_LINES
import com.example.nextlevel.ui.screens.coursedetailed.ui.model.BackgroundPattern.GEOMETRIC_FIGURES
import com.example.nextlevel.ui.theme.Black16
import com.example.nextlevel.ui.theme.Black33
import com.example.nextlevel.ui.theme.Green22
import com.example.nextlevel.ui.theme.Typography
import com.example.nextlevel.ui.theme.White

@Composable
fun CourseAction(
	modifier: Modifier = Modifier,
	title: String,
	tag: String,
	@DrawableRes tagLeadingIcon: Int?,
	backgroundPattern: BackgroundPattern,
	onClicked: () -> Unit
) {
	val interactionSource = remember { MutableInteractionSource() }
	val isPressed by interactionSource.collectIsPressedAsState()
	val alpha by animateFloatAsState(if (isPressed) 0.5f else 1f, label = "pressAlpha")
	Box(
		modifier = modifier
			.size(width = 175.dp, height = 200.dp)
			.background(
				Brush.verticalGradient(listOf(Black16, Black33)),
				shape = RoundedCornerShape(20.dp)
			)
			.clip(RoundedCornerShape(20.dp))
			.clickable(interactionSource = interactionSource) { onClicked() }
			.alpha(alpha)
	) {
		Image(
			modifier = Modifier.fillMaxSize(),
			painter = painterResource(backgroundPattern.drawable),
			contentDescription = null
		)
		Column(
			modifier = Modifier
				.fillMaxSize()
				.padding(16.dp)
		) {
			Text(
				text = title,
				style = Typography.titleMedium.copy(color = White),
			)
			Spacer(Modifier.weight(1f))
			Row(
				modifier = Modifier
					.background(Green22, shape = RoundedCornerShape(8.dp))
					.padding(horizontal = 8.dp, vertical = 3.dp),
				horizontalArrangement = Arrangement.spacedBy(4.dp)
			) {
				if (tagLeadingIcon != null) {
					Icon(
						painterResource(tagLeadingIcon),
						contentDescription = null,
						tint = Black16
					)
				}
				Text(
					text = tag,
					style = Typography.labelSmall.copy(color = Black16)
				)
			}
		}
	}
}

@Composable
fun CourseActionSkeleton(
	modifier: Modifier = Modifier,
	backgroundPattern: BackgroundPattern
) {
	Box(
		modifier = modifier
			.size(width = 175.dp, height = 200.dp)
			.background(
				Brush.verticalGradient(listOf(Black16, Black33)),
				shape = RoundedCornerShape(20.dp)
			)
			.clip(RoundedCornerShape(20.dp)),
	) {
		SkeletonShimmer {
			Image(
				modifier = Modifier.fillMaxSize(),
				painter = painterResource(backgroundPattern.drawable),
				contentDescription = null
			)
		}
	}
}

@Preview
@Composable
private fun CourseActionPreview() {
	LazyVerticalGrid(
		modifier = Modifier
			.background(Black16, RoundedCornerShape(16.dp))
			.padding(16.dp),
		columns = GridCells.Fixed(2),
		verticalArrangement = Arrangement.spacedBy(8.dp),
		horizontalArrangement = Arrangement.spacedBy(8.dp),
	) {
		item {
			CourseAction(
				title = "Расчет матрицы",
				tag = "Дашборд",
				tagLeadingIcon = null,
				backgroundPattern = DASH_LINES,
				onClicked = {}
			)
		}
		item {
			CourseAction(
				title = "Сертификат",
				tag = "1/21",
				tagLeadingIcon = R.drawable.ic_check_inside_progress_circle,
				backgroundPattern = GEOMETRIC_FIGURES,
				onClicked = {}
			)
		}
		item(span = { GridItemSpan(2) }) {
			CourseAction(
				title = "Сертификат",
				tag = "1/21",
				tagLeadingIcon = R.drawable.ic_check_inside_progress_circle,
				backgroundPattern = GEOMETRIC_FIGURES,
				onClicked = {}
			)
		}
		item {
			CourseActionSkeleton(
				modifier = Modifier.clip(RoundedCornerShape(20.dp)),
				backgroundPattern = DASH_LINES
			)
		}
		item {
			CourseActionSkeleton(
				modifier = Modifier.clip(RoundedCornerShape(20.dp)),
				backgroundPattern = GEOMETRIC_FIGURES
			)
		}
		item(span = { GridItemSpan(2) }) {
			CourseActionSkeleton(
				modifier = Modifier.clip(RoundedCornerShape(20.dp)),
				backgroundPattern = GEOMETRIC_FIGURES
			)
		}
	}
}