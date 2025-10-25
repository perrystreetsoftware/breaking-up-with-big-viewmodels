package com.perrystreet.nobigviewmodels.presentation.grid.mediator

import com.perrystreet.nobigviewmodels.presentation.grid.model.GridMediaUiModel
import com.perrystreet.nobigviewmodels.presentation.grid.model.GridState
import com.perrystreet.nobigviewmodels.utils.Optional

sealed interface GridAction {
    fun reduce(state: GridState): GridState

    data class MediaLoaded(
        val media: List<GridMediaUiModel>,
    ) : GridAction {
        override fun reduce(state: GridState): GridState =
            state.copy(
                media = media,
                isLoading = false,
            )
    }

    data class MediaError(
        val error: Optional<Throwable>,
    ) : GridAction {
        override fun reduce(state: GridState): GridState =
            state.copy(
                error = error,
            )
    }

    data object MediaLoading : GridAction {
        override fun reduce(state: GridState): GridState =
            state.copy(
                media = emptyList(),
                isLoading = true,
            )
    }

    data class ToggleSelection(
        val mediaId: String,
    ) : GridAction {
        override fun reduce(state: GridState): GridState {
            val updatedMedia =
                state.media.map { media ->
                    if (media.id == mediaId) {
                        media.copy(selected = !media.selected)
                    } else {
                        media
                    }
                }
            return state.copy(media = updatedMedia)
        }
    }

    data object MediaUploading : GridAction {
        override fun reduce(state: GridState): GridState =
            state.copy(
                isLoading = true,
            )
    }
}
