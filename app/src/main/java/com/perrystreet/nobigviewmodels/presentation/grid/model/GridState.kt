package com.perrystreet.nobigviewmodels.presentation.grid.model

data class GridState(
    val media: List<GridMediaUiModel>,
    val isLoading: Boolean = false,
    val error: String? = null,
)
