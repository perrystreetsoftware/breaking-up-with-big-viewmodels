package com.perrystreet.nobigviewmodels.presentation.grid.mediator

import com.perrystreet.nobigviewmodels.presentation.grid.model.GridState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import org.koin.core.annotation.Scope
import org.koin.core.annotation.Scoped

@Scope(name = "grid")
@Scoped
class GridMediator {
    private val _state = MutableStateFlow(GridState(emptyList()))
    val state: StateFlow<GridState> = _state.asStateFlow()

    fun dispatch(action: GridAction) {
        val currentState = _state.value
        val newState = reduce(currentState, action)
        _state.value = newState
    }

    private fun reduce(
        state: GridState,
        action: GridAction,
    ): GridState =
        when (action) {
            is GridAction.MediaLoaded -> {
                state.copy(
                    media = action.media,
                    isLoading = false,
                    error = null,
                )
            }

            is GridAction.ToggleSelection -> {
                val updatedMedia =
                    state.media.map { media ->
                        if (media.id == action.mediaId) {
                            media.copy(selected = !media.selected)
                        } else {
                            media
                        }
                    }
                state.copy(media = updatedMedia)
            }

            is GridAction.ClearSelections -> {
                val updatedMedia =
                    state.media.map { media ->
                        media.copy(selected = false)
                    }
                state.copy(media = updatedMedia)
            }

            is GridAction.SelectMultiple -> {
                val updatedMedia =
                    state.media.map { media ->
                        if (action.mediaIds.contains(media.id)) {
                            media.copy(selected = true)
                        } else {
                            media
                        }
                    }
                state.copy(media = updatedMedia)
            }

            is GridAction.DeletingMedia -> {
                val updatedMedia =
                    state.media.map { media ->
                        if (action.mediaIds.contains(media.id)) {
                            media.copy(isDeleting = true)
                        } else {
                            media
                        }
                    }
                state.copy(
                    media = updatedMedia,
                    error = null,
                )
            }

            is GridAction.DeleteFailed -> {
                val updatedMedia =
                    state.media.map { media ->
                        media.copy(isDeleting = false)
                    }
                state.copy(
                    media = updatedMedia,
                    error = action.error,
                )
            }

            is GridAction.ClearError -> {
                state.copy(error = null)
            }
        }
}

// TODO generate with KSP
//@ReducerAction
//fun reduce(
//    state: GridState,
//    action: GridAction.MediaLoaded,
//) {
//    _state.value =
//        state.copy(
//            media = action.media,
//            isLoading = false,
//            error = null,
//        )
//}

/** Hilt equivalent */
/*
class GridMediator {
    // ...
}
*/
