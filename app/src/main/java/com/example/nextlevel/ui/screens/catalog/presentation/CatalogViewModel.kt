package com.example.nextlevel.ui.screens.catalog.presentation

import com.example.nextlevel.ui.base.BaseViewModel

class CatalogViewModel : BaseViewModel<CatalogEvent, CatalogState, CatalogEffect>() {

	override fun initialState() = CatalogState()
	override fun onEvent(event: CatalogEvent) = Unit
}