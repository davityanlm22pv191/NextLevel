package com.example.nextlevel.ui.screens.home.ui.fortunewheel

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.nextlevel.ui.common.SkeletonShimmer
import com.example.nextlevel.ui.theme.GreyD5
import com.example.nextlevel.ui.theme.GreyF8
import com.example.nextlevel.ui.theme.PurpleDE
import com.example.nextlevel.ui.theme.Typography
import com.example.nextlevel.ui.theme.White

@Composable
fun FortuneWheelShortItemSkeleton(modifier: Modifier = Modifier) {
	SkeletonShimmer(
		modifier = modifier
			.fillMaxWidth()
			.clip(RoundedCornerShape(20.dp))
			.background(
				Brush.linearGradient(colors = listOf(GreyD5, PurpleDE)),
				shape = RoundedCornerShape(20.dp)
			)
	) {
		Column(
			modifier = Modifier
				.height(163.dp)
				.padding(top = 20.dp, start = 20.dp, end = 20.dp, bottom = 16.dp)
		) {
			Box(
				modifier = Modifier
					.fillMaxWidth()
					.height(32.dp)
					.background(GreyF8.copy(alpha = 0.8f), RoundedCornerShape(20.dp))
			)
			Box(
				modifier = Modifier
					.padding(top = 4.dp)
					.fillMaxWidth(0.5f)
					.height(17.dp)
					.background(GreyF8.copy(alpha = 0.8f), RoundedCornerShape(20.dp))
			)
			Spacer(Modifier.weight(1f))
			Row(
				modifier = Modifier
					.padding(top = 4.dp)
					.fillMaxWidth()
					.height(54.dp)
					.background(
						color = GreyF8.copy(alpha = 0.8f),
						shape = RoundedCornerShape(20.dp)
					)
					.alpha(0.5f),
				verticalAlignment = Alignment.CenterVertically
			) {
				Column(
					modifier = Modifier
						.fillMaxHeight()
						.padding(12.dp),
					verticalArrangement = Arrangement.spacedBy(2.dp),
				) {
					Box(
						modifier = Modifier
							.width(68.dp)
							.weight(1f)
							.background(GreyD5, shape = CircleShape)
					)
					Box(
						modifier = Modifier
							.width(56.dp)
							.weight(1f)
							.background(GreyD5, shape = CircleShape)
					)
					Box(
						modifier = Modifier
							.width(40.dp)
							.weight(1f)
							.background(GreyD5, shape = CircleShape)
					)
				}
				Spacer(modifier = Modifier.weight(1f))
				Box(
					modifier = Modifier
						.size(36.dp)
						.background(GreyD5, CircleShape)
				)
				Text(
					modifier = Modifier.padding(2.dp),
					text = ":",
					style = Typography.bodyMedium.copy(
						color = White,
						fontWeight = FontWeight.SemiBold
					)
				)
				Box(
					modifier = Modifier
						.size(36.dp)
						.background(GreyD5, CircleShape)
				)
				Text(
					modifier = Modifier.padding(2.dp),
					text = ":",
					style = Typography.bodyMedium.copy(
						color = White,
						fontWeight = FontWeight.SemiBold
					)
				)
				Box(
					modifier = Modifier
						.size(36.dp)
						.background(GreyD5, CircleShape)
				)
				Spacer(modifier = Modifier.weight(1f))
				Box(
					modifier = Modifier
						.width(100.dp)
						.height(50.dp)
						.padding(vertical = 6.dp)
						.padding(end = 8.dp)
						.background(color = GreyD5, shape = RoundedCornerShape(12.dp))
				)
			}
		}
	}
}

@Preview
@Composable
private fun FortuneWheelShortItemSkeletonPreview() {
	FortuneWheelShortItemSkeleton()
}