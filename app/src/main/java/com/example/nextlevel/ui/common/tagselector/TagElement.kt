package com.example.nextlevel.ui.common.tagselector

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.nextlevel.R
import com.example.nextlevel.ui.common.tagselector.model.Tag
import com.example.nextlevel.ui.common.tagselector.model.getUiState
import com.example.nextlevel.ui.theme.Black16
import com.example.nextlevel.ui.theme.ScreenColor

@Composable
fun TagElement(tag: Tag, onClick: (Tag) -> Unit) {
	val uiState = tag.getUiState()
	val background by animateColorAsState(targetValue = uiState.backgroundColor)
	val textColor by animateColorAsState(targetValue = uiState.textColor)
	val checkAlpha by animateFloatAsState(targetValue = if (uiState.isCheckIconVisible) 1f else 0f)
	Box(
		modifier = Modifier
			.background(background, RoundedCornerShape(24.dp))
			.clickable(
				indication = null,
				onClick = { onClick(tag) },
				interactionSource = null,
			),
	) {
		Text(
			modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp),
			text = tag.name,
			color = textColor,
		)
		Image(
			modifier = Modifier
				.align(Alignment.TopEnd)
				.offset(x = 8.dp, y = (-8).dp),
			painter = painterResource(R.drawable.ic_check_black16_in_white_circle),
			contentDescription = null,
			alpha = checkAlpha,
			colorFilter = ColorFilter.lighting(ScreenColor, Black16)
		)
	}
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun TagElementPreview() {
	Column(
		modifier = Modifier
			.background(ScreenColor)
			.padding(16.dp),
		verticalArrangement = Arrangement.spacedBy(8.dp)
	) {
		TagElement(Tag(id = "1", "Питание"), onClick = {})
		TagElement(Tag(id = "2", "Питание", isSelected = true), onClick = {})
	}
}