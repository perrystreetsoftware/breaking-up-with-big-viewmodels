package com.perrystreet.nobigviewmodels.presentation.grid.viewmodel

import com.perrystreet.nobigviewmodels.domain.usecase.DeleteMediaUseCase
import com.perrystreet.nobigviewmodels.presentation.base.BaseLifecycleViewModel
import com.perrystreet.nobigviewmodels.presentation.grid.mediator.GridAction
import com.perrystreet.nobigviewmodels.presentation.grid.mediator.GridMediator
import com.perrystreet.nobigviewmodels.presentation.grid.model.TopBarState
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.kotlin.plusAssign
import org.koin.android.annotation.KoinViewModel

@KoinViewModel
class TopBarViewModel(
    private val gridMediator: GridMediator,
    private val deleteMediaUseCase: DeleteMediaUseCase,
) : BaseLifecycleViewModel() {
    val state: Observable<TopBarState> =
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
        disposables +=
            gridMediator.state
                .take(1)
                .map { gridState ->
                    gridState.media
                        .filter { it.selected }
                        .map { it.id }
                }.doOnNext { selectedIds ->
                    gridMediator.dispatch(GridAction.DeletingMedia(selectedIds))
                }.flatMapCompletable { selectedIds ->
                    deleteMediaUseCase(selectedIds)
                }.subscribe(
                    {},
                    { error ->
                        val errorMessage = error.message ?: "Failed to delete media"
                        gridMediator.dispatch(GridAction.DeleteFailed(errorMessage))
                    },
                )
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
