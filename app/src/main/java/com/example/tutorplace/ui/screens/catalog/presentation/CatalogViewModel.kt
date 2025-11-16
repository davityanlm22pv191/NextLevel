package com.example.tutorplace.ui.screens.catalog.presentation

import com.example.tutorplace.ui.base.BaseViewModel

class CatalogViewModel : BaseViewModel<CatalogEvent, CatalogState, CatalogEffect>() {

	private var navigator: CatalogNavigator? = null

	fun attachNavigator(navigator: CatalogNavigator) {
		this.navigator = navigator
	}

	override fun initialState() = CatalogState()

	override fun onEvent(event: CatalogEvent) = Unit
}