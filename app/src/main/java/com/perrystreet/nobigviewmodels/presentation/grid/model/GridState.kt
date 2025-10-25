package com.perrystreet.nobigviewmodels.presentation.grid.model

import com.perrystreet.nobigviewmodels.utils.Optional

data class GridState(
    val media: List<GridMediaUiModel>,
    val isLoading: Boolean = false,
    val error: Optional<Throwable> = Optional.empty(),
)
