package com.example.tutorplace.ui.screens.catalog

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.example.tutorplace.navigation.Navigator
import com.example.tutorplace.ui.screens.catalog.presentation.CatalogViewModel

@Composable
fun CatalogScreen(navigator: Navigator) {
	val viewModel = hiltViewModel<CatalogViewModel>()
	CatalogContent()
}

@Composable
private fun CatalogContent() {
	Text(
		modifier = Modifier
			.fillMaxSize()
			.padding(vertical = 100.dp),
		text = "Это стартовый экрана таба Каталог\n\nОн ещё не сделан",
		textAlign = TextAlign.Center,
	)
}

@Preview
@Composable
private fun CatalogContentPreview() {
	CatalogContent()
}