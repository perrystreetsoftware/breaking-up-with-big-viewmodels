package com.perrystreet.nobigviewmodels.presentation.grid.model

data class TopBarState(
    val selectedCount: Int = 0,
    val isDeleteVisible: Boolean = false,
    val isDeleteEnabled: Boolean = false,
)
