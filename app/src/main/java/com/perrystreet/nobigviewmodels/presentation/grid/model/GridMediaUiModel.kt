package com.perrystreet.nobigviewmodels.presentation.grid.model

import androidx.compose.ui.graphics.Color

data class GridMediaUiModel(
    val id: String,
    val title: String,
    val thumbnailUrl: String,
    val imageUrl: String,
    val formattedDate: String,
    val typeIcon: String,
    val typeColor: Color,
    val selected: Boolean = false,
    val isLoading: Boolean = false,
)
