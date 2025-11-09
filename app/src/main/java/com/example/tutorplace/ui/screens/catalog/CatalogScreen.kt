package com.example.tutorplace.ui.screens.catalog

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.tutorplace.ui.screens.catalog.presentation.CatalogNavigator
import com.example.tutorplace.ui.screens.catalog.presentation.CatalogViewModel

@Composable
fun CatalogScreen(navController: NavHostController) {
	val viewModel = hiltViewModel<CatalogViewModel>()
	LaunchedEffect(Unit) { viewModel.attachNavigator(CatalogNavigator(navController)) }
	CatalogScreen()
}

@Composable
private fun CatalogScreen() {
	Text(text = "This is Catalog Screen")
}

@Preview
@Composable
private fun CatalogPreview() {
	CatalogScreen()
}