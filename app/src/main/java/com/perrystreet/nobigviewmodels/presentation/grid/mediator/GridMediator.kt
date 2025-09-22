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

    fun updateState(newState: GridState) {
        _state.value = newState
    }

    fun toggleSelection(mediaId: String) {
        val currentState = _state.value
        val updatedMedia =
            currentState.media.map { media ->
                if (media.id == mediaId) {
                    media.copy(selected = !media.selected)
                } else {
                    media
                }
            }
        _state.value = currentState.copy(media = updatedMedia)
    }
}

/** Hilt equivalent */
/*
class GridMediator {
    // ...
}
*/
