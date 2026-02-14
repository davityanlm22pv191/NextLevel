package com.example.nextlevel.ui.common

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.nextlevel.R
import com.example.nextlevel.ui.theme.Grey82
import com.example.nextlevel.ui.theme.Typography

@Composable
fun AuthSectionDivider(modifier: Modifier) {
	Row(modifier = modifier, verticalAlignment = Alignment.CenterVertically) {
		HorizontalDivider(modifier = Modifier.weight(1f), thickness = 1.dp, color = Grey82)
		Text(
			modifier = Modifier.padding(horizontal = 16.dp),
			text = stringResource(id = R.string.common_or),
			style = Typography.bodyMedium,
			color = Grey82
		)
		HorizontalDivider(modifier = Modifier.weight(1f), thickness = 1.dp, color = Grey82)
	}
}

@Preview
@Composable
fun AuthSectionDividerPreview() {
	Box(Modifier.padding(16.dp)) {
		AuthSectionDivider(Modifier)
	}
}