package com.example.nextlevel.ui.screens.catalog.presentation

import com.example.nextlevel.ui.base.BaseState

data class CatalogState(
	val isLoading: Boolean = false,
	val throwable: Throwable? = null
) : BaseState