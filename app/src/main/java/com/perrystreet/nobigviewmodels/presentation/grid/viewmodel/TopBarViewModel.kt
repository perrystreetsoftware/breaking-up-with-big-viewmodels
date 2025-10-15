package com.perrystreet.nobigviewmodels.presentation.grid.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.perrystreet.nobigviewmodels.domain.usecase.DeleteMediaUseCase
import com.perrystreet.nobigviewmodels.presentation.grid.mediator.GridAction
import com.perrystreet.nobigviewmodels.presentation.grid.mediator.GridMediator
import com.perrystreet.nobigviewmodels.presentation.grid.model.TopBarState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import org.koin.android.annotation.KoinViewModel

@KoinViewModel
class TopBarViewModel(
    private val gridMediator: GridMediator,
    private val deleteMediaUseCase: DeleteMediaUseCase,
) : ViewModel() {
    val state: Flow<TopBarState> =
        gridMediator.state.map { gridState ->
            val isDeletingMedia = gridState.media.any { it.isDeleting }
            val isAnyItemSelected = gridState.media.any { it.selected }

            TopBarState(
                selectedCount = gridState.media.count { it.selected },
                isDeleteVisible = isAnyItemSelected,
                isDeleteEnabled = isAnyItemSelected && !isDeletingMedia,
            )
        }

    fun onDeleteTap() {
        viewModelScope.launch {
            val selectedIds =
                gridMediator.state.value.media
                    .filter { it.selected }
                    .map { it.id }

            gridMediator.dispatch(GridAction.DeletingMedia(selectedIds))

            val result = deleteMediaUseCase(selectedIds)

            if (result.isSuccess) {
                // No need to dispatch an action, since we reactively observe the media items
            } else {
                val errorMessage =
                    result.exceptionOrNull()?.message ?: "Failed to delete media"
                gridMediator.dispatch(GridAction.DeleteFailed(errorMessage))
            }
        }
    }
}

/** Hilt equivalent */
/*
@HiltViewModel
class TopBarViewModel @Inject constructor(
    private val scopeHolder: ScopeHolder,
    @GridScopeId private val scopeId: String
) : ViewModel() {

    private val gridMediator by lazy {
        scopeHolder.getOrCreateMediator(scopeId) { GridMediator() }
    }
}
*/
