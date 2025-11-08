package com.example.tutorplace.ui.screens.home.ui.mycourses

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.tutorplace.R
import com.example.tutorplace.ui.common.PurpleButton
import com.example.tutorplace.ui.theme.Black16
import com.example.tutorplace.ui.theme.Typography
import com.example.tutorplace.ui.theme.White

@Composable
fun MyCoursesEmptyItem(
	modifier: Modifier = Modifier,
	onCatalogClick: () -> Unit
) {
	Column(
		modifier = modifier
			.background(color = White, shape = RoundedCornerShape(20.dp))
			.padding(16.dp),
		horizontalAlignment = Alignment.CenterHorizontally
	) {
		Text(
			text = stringResource(R.string.home_my_courses_empty_title),
			style = Typography.headlineLarge.copy(color = Black16),
			textAlign = TextAlign.Center
		)
		Text(
			modifier = Modifier.padding(top = 8.dp),
			text = stringResource(R.string.home_my_courses_empty_description),
			style = Typography.labelMedium.copy(color = Black16),
			textAlign = TextAlign.Center
		)
		PurpleButton(
			modifier = Modifier
				.fillMaxWidth()
				.padding(top = 16.dp),
			text = stringResource(R.string.home_my_courses_empty_button_title),
			isLoading = false,
			isEnabled = true,
			onClick = { onCatalogClick() }
		)
	}
}

@Preview
@Composable
private fun MyCoursesEmptyItemPreview() {
	MyCoursesEmptyItem(onCatalogClick = {})
}