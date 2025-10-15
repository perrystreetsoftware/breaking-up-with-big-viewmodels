package com.perrystreet.nobigviewmodels.presentation.grid.mediator

import com.perrystreet.nobigviewmodels.presentation.grid.model.GridMediaUiModel

sealed class GridAction {
    data class MediaLoaded(
        val media: List<GridMediaUiModel>,
    ) : GridAction()

    data class ToggleSelection(
        val mediaId: String,
    ) : GridAction()

    data object ClearSelections : GridAction()

    data class SelectMultiple(
        val mediaIds: List<String>,
    ) : GridAction()

    data class DeletingMedia(
        val mediaIds: List<String>,
    ) : GridAction()

    data class DeleteFailed(
        val error: String,
    ) : GridAction()

    data object ClearError : GridAction()
}
