package com.example.tutorplace.ui.common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.tutorplace.helpers.FormatHelper
import java.time.LocalDate
import kotlin.time.ExperimentalTime

@OptIn(ExperimentalMaterial3Api::class, ExperimentalTime::class)
@Composable
fun DataChoosePicker(
	modifier: Modifier = Modifier,
	value: LocalDate?,
	format: String,
	onValueChange: (LocalDate) -> Unit,
	label: String?,
	isError: Boolean,
) {

}

@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
@Composable
private fun DataChoosePickerPreview() {
	Column(
		modifier = Modifier.padding(16.dp),
		verticalArrangement = Arrangement.spacedBy(8.dp)
	) {
		DataChoosePicker(
			modifier = Modifier,
			value = LocalDate.now(),
			format = FormatHelper.DATE_MONTH_YEAR,
			onValueChange = { },
			label = "Дата рождения",
			isError = false
		)
		DataChoosePicker(
			modifier = Modifier,
			value = null,
			format = FormatHelper.DATE_MONTH_YEAR,
			onValueChange = { },
			label = "Дата рождения",
			isError = false
		)
		DataChoosePicker(
			modifier = Modifier,
			value = null,
			format = FormatHelper.DATE_MONTH_YEAR,
			onValueChange = { },
			label = "Дата рождения",
			isError = true
		)
	}
}