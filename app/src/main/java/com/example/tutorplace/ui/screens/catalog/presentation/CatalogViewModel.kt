package com.example.tutorplace.ui.screens.catalog.presentation

import com.example.tutorplace.ui.base.BaseViewModel

class CatalogViewModel : BaseViewModel<CatalogEvent, CatalogState, CatalogEffect>() {

	override fun initialState() = CatalogState()
	override fun onEvent(event: CatalogEvent) = Unit
}