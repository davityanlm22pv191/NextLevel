package com.example.nextlevel.ui.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.nextlevel.R
import com.example.nextlevel.ui.theme.Black16
import com.example.nextlevel.ui.theme.GreyF8
import com.example.nextlevel.ui.theme.Typography

@Composable
fun YandexButton(
	modifier: Modifier = Modifier,
	onClick: () -> Unit = {},
) {
	Button(
		modifier = modifier
			.fillMaxWidth()
			.height(50.dp),
		shape = RoundedCornerShape(12.dp),
		colors = ButtonDefaults.buttonColors().copy(containerColor = GreyF8),
		onClick = { onClick() }
	) {
		Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
			Image(
				modifier = Modifier.align(Alignment.CenterStart),
				painter = painterResource(R.drawable.ic_yandex_logo),
				contentDescription = null
			)
			Text(
				text = stringResource(R.string.authorization_entry_by_yandex_id),
				style = Typography.bodyLarge,
				color = Black16
			)
		}
	}
}

@Preview(showBackground = true, backgroundColor = 0xFF000000)
@Composable
private fun YandexButtonPreview() = YandexButton(Modifier.padding(8.dp))
