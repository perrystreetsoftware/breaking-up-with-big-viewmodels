package com.perrystreet.nobigviewmodels.presentation.grid.mediator

import com.perrystreet.nobigviewmodels.presentation.grid.model.GridMediaUiModel
import com.perrystreet.nobigviewmodels.presentation.grid.model.GridState

sealed interface GridAction {
    fun reduce(state: GridState): GridState

    data class MediaLoaded(
        val media: List<GridMediaUiModel>,
    ) : GridAction {
        override fun reduce(state: GridState): GridState =
            state.copy(
                media = media,
                isLoading = false,
                error = null,
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

    data object ClearSelections : GridAction {
        override fun reduce(state: GridState): GridState {
            val updatedMedia = state.media.map { it.copy(selected = false) }
            return state.copy(media = updatedMedia)
        }
    }

    data class SelectMultiple(
        val mediaIds: List<String>,
    ) : GridAction {
        override fun reduce(state: GridState): GridState {
            val updatedMedia =
                state.media.map { media ->
                    if (mediaIds.contains(media.id)) {
                        media.copy(selected = true)
                    } else {
                        media
                    }
                }
            return state.copy(media = updatedMedia)
        }
    }

    data class DeletingMedia(
        val mediaIds: List<String>,
    ) : GridAction {
        override fun reduce(state: GridState): GridState {
            val updatedMedia =
                state.media.map { media ->
                    if (mediaIds.contains(media.id)) {
                        media.copy(isDeleting = true)
                    } else {
                        media
                    }
                }
            return state.copy(
                media = updatedMedia,
                error = null,
            )
        }
    }

    data class DeleteFailed(
        val error: String,
    ) : GridAction {
        override fun reduce(state: GridState): GridState {
            val updatedMedia = state.media.map { it.copy(isDeleting = false) }
            return state.copy(
                media = updatedMedia,
                error = error,
            )
        }
    }

    data object MediaUploading : GridAction {
        override fun reduce(state: GridState): GridState =
            state.copy(
                isLoading = true,
                error = null,
            )
    }

    data class UploadFailed(
        val error: String,
    ) : GridAction {
        override fun reduce(state: GridState): GridState =
            state.copy(
                isLoading = false,
                error = error,
            )
    }

    data object ClearError : GridAction {
        override fun reduce(state: GridState): GridState = state.copy(error = null)
    }
}
