package com.example.nextlevel.ui.common

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.nextlevel.R
import com.example.nextlevel.domain.model.Sex
import com.example.nextlevel.ui.theme.Black16
import com.example.nextlevel.ui.theme.ContainerColor
import com.example.nextlevel.ui.theme.Grey58
import com.example.nextlevel.ui.theme.Grey82
import com.example.nextlevel.ui.theme.GreyD5
import com.example.nextlevel.ui.theme.GreyF8
import com.example.nextlevel.ui.theme.Red1D
import com.example.nextlevel.ui.theme.ScreenColor
import com.example.nextlevel.ui.theme.Typography
import com.example.nextlevel.ui.theme.White

@Composable
fun SexChoosingMenu(
	modifier: Modifier = Modifier,
	selectedSex: Sex?,
	isError: Boolean,
	onSexChosen: (Sex) -> Unit
) {
	val sexList = listOf(Sex.FEMALE, Sex.MALE)
	var isOpen by remember { mutableStateOf(false) }
	val rotateDegrees by animateFloatAsState(targetValue = if (isOpen) 180f else 0f)

	Box {
		AnimatedVisibility(
			visible = isOpen,
			enter = slideInVertically { fullHeight -> -fullHeight / 10 } + fadeIn(),
			exit = slideOutVertically { fullHeight -> -fullHeight / 10 } + fadeOut()
		) {
			Column(
				modifier = Modifier then modifier
					.background(GreyF8, shape = RoundedCornerShape(12.dp))
					.padding(top = 56.dp, bottom = 8.dp)
					.padding(horizontal = 8.dp),
			) {
				sexList.forEach { sex ->
					Text(
						modifier = Modifier
							.fillMaxWidth()
							.clip(RoundedCornerShape(12.dp))
							.clickable(indication = ripple(), interactionSource = null) {
								onSexChosen(sex)
								isOpen = false
							}
							.padding(horizontal = 16.dp, vertical = 12.dp),
						text = stringResource(sex.stringResId),
						style = Typography.labelMedium.copy(Grey58),
					)
				}
			}
		}
		Surface(
			modifier = modifier,
			shape = RoundedCornerShape(12.dp),
			color = if (isError) White else ContainerColor,
			border = BorderStroke(1.dp, if (isError) Red1D else GreyD5),
			onClick = { isOpen = !isOpen }
		) {
			Row(
				modifier = Modifier
					.fillMaxWidth()
					.padding(horizontal = 16.dp, vertical = 14.dp),
				verticalAlignment = Alignment.CenterVertically
			) {
				Text(
					modifier = Modifier.weight(1f),
					text = stringResource(selectedSex?.stringResId ?: R.string.common_your_sex),
					style = Typography.labelMedium.copy(color = if (selectedSex != null) Black16 else Grey82),
					maxLines = 1
				)
				Image(
					modifier = Modifier.rotate(rotateDegrees),
					contentDescription = null,
					painter = painterResource(R.drawable.ic_arrow_down_black_16),
					alpha = if (selectedSex != null) 1f else 0.5f,
				)
			}
		}
	}

}


@Preview(showBackground = true)
@Composable
private fun SexChoosingMenuPreview_Default() {
	Column(
		modifier = Modifier
			.background(ScreenColor)
			.padding(16.dp),
		verticalArrangement = Arrangement.spacedBy(16.dp)
	) {
		SexChoosingMenu(
			selectedSex = null,
			isError = false,
			onSexChosen = {}
		)
		SexChoosingMenu(
			selectedSex = Sex.MALE,
			isError = false,
			onSexChosen = {}
		)
		SexChoosingMenu(
			selectedSex = Sex.FEMALE,
			isError = false,
			onSexChosen = {}
		)
		SexChoosingMenu(
			selectedSex = null,
			isError = true,
			onSexChosen = {}
		)
	}

}