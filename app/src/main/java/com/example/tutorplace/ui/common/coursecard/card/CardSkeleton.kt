package com.example.tutorplace.ui.common.coursecard.card

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.tutorplace.R
import com.example.tutorplace.ui.common.SkeletonShimmer
import com.example.tutorplace.ui.theme.Black16Alpha30
import com.example.tutorplace.ui.theme.GreyD5
import com.example.tutorplace.ui.theme.PurpleDE

@Composable
fun CardSkeleton(
	modifier: Modifier = Modifier,
	shape: CourseCardShapeType,
) {
	SkeletonShimmer(
		modifier = Modifier.clip(RoundedCornerShape(20.dp))
	) {
		Column(
			modifier = modifier
				.width(if (shape == CourseCardShapeType.SQUARE) 175.dp else 340.dp)
				.height(200.dp)
				.background(
					brush = Brush.linearGradient(colors = listOf(GreyD5, PurpleDE)),
					shape = RoundedCornerShape(20.dp)
				)
				.padding(16.dp),
		) {
			Box(
				modifier = Modifier
					.fillMaxWidth(0.65f)
					.height(21.dp)
					.background(color = Black16Alpha30, shape = RoundedCornerShape(20.dp)),
			)
			Box(
				modifier = Modifier
					.padding(top = 2.dp)
					.fillMaxWidth(0.4f)
					.height(21.dp)
					.background(color = Black16Alpha30, shape = RoundedCornerShape(20.dp)),
			)
			Row(
				modifier = Modifier
					.padding(top = 4.dp)
					.fillMaxWidth(),
				verticalAlignment = Alignment.CenterVertically
			) {
				Box(
					modifier = Modifier
						.padding(end = 4.dp)
						.size(width = 24.dp, height = 22.dp)
						.background(color = Black16Alpha30, shape = RoundedCornerShape(20.dp))
				)
				(1..5).forEach { _ ->
					Icon(
						modifier = Modifier.size(20.dp),
						painter = painterResource(id = R.drawable.ic_star),
						contentDescription = null,
						tint = Black16Alpha30
					)
				}
			}
			Spacer(modifier = Modifier.weight(1f))
			Box(
				modifier = Modifier
					.size(width = 60.dp, height = 26.dp)
					.background(color = Black16Alpha30, shape = RoundedCornerShape(8.dp))
			)
		}
	}
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
@Composable
private fun CardSkeletonPreview() {
	Column(
		modifier = Modifier
			.fillMaxWidth()
			.padding(16.dp),
		horizontalAlignment = Alignment.CenterHorizontally,
		verticalArrangement = Arrangement.spacedBy(16.dp)
	) {
		CardSkeleton(shape = CourseCardShapeType.SQUARE)
		CardSkeleton(shape = CourseCardShapeType.LARGE)
	}
}