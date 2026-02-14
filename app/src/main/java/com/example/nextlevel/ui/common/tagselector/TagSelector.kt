package com.example.nextlevel.ui.common.tagselector

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.nextlevel.ui.common.tagselector.model.Tag

@Composable
fun TagSelector(
	modifier: Modifier = Modifier,
	tags: List<Tag>,
	onTagSelected: (Tag) -> Unit
) {
	FlowRow(
		modifier = modifier.padding(16.dp),
		horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterHorizontally),
		verticalArrangement = Arrangement.spacedBy(8.dp),
	) {
		tags.forEach { tag -> TagElement(tag, onClick = { onTagSelected(tag) }) }
	}
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
@Composable
private fun TagSelectorPreview() {
	TagSelector(
		tags = listOf(
			Tag(id = "1", name = "Android"),
			Tag(id = "2", name = "Kotlin", isSelected = true),
			Tag(id = "3", name = "Jetpack Compose"),
			Tag(id = "4", name = "Coroutines", isSelected = true),
			Tag(id = "5", name = "Architecture"),
			Tag(id = "6", name = "UI/UX", isSelected = true),
			Tag(id = "7", name = "RxJava"),
			Tag(id = "8", name = "MVI", isSelected = true),
			Tag(id = "9", name = "MVP"),
			Tag(id = "10", name = "Clean Architecture", isSelected = true)
		)
	) { }
}