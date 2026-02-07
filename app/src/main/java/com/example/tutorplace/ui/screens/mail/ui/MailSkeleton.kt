package com.example.tutorplace.ui.screens.mail.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.tutorplace.ui.common.SkeletonShimmer
import com.example.tutorplace.ui.theme.GreyD5
import com.example.tutorplace.ui.theme.GreyF8

@Composable
fun MailSkeleton(modifier: Modifier = Modifier) {
	SkeletonShimmer(
		modifier = Modifier.clip(RoundedCornerShape(20.dp))
	) {
		Box(
			modifier = modifier
				.fillMaxWidth()
				.height(80.dp)
				.background(GreyF8, shape = RoundedCornerShape(20.dp))
				.padding(horizontal = 16.dp),
		) {
			Row(
				modifier = Modifier.fillMaxSize(),
				verticalAlignment = Alignment.CenterVertically
			) {
				Box(
					modifier = Modifier
						.size(56.dp)
						.background(GreyD5, shape = RoundedCornerShape(16.dp)),
				)
				Column(
					modifier = Modifier.padding(start = 16.dp),
					verticalArrangement = Arrangement.spacedBy(4.dp)
				) {
					Box(
						modifier = Modifier
							.fillMaxWidth(fraction = 0.4f)
							.height(20.dp)
							.background(GreyD5, shape = RoundedCornerShape(16.dp))
					)
					Box(
						modifier = Modifier
							.fillMaxWidth()
							.height(20.dp)
							.background(GreyD5, shape = RoundedCornerShape(16.dp))
					)
				}
			}
		}
	}
}

@Preview
@Composable
private fun MailSkeletonPreview() {
	MailSkeleton()
}