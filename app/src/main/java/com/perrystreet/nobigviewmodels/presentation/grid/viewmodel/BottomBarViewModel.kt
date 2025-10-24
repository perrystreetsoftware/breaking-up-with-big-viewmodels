package com.perrystreet.nobigviewmodels.presentation.grid.viewmodel

import com.perrystreet.nobigviewmodels.presentation.base.BaseLifecycleViewModel
import com.perrystreet.nobigviewmodels.presentation.grid.mediator.GridMediator
import io.reactivex.rxjava3.core.Observable
import org.koin.android.annotation.KoinViewModel

@KoinViewModel
class BottomBarViewModel(
    gridMediator: GridMediator,
) : BaseLifecycleViewModel() {
    val state: Observable<Boolean> =
        gridMediator.state.map { state ->
            state.media.any { it.selected }
        }

    fun onFavoriteTap() {
        // ...
    }

    fun onShareTap() {
        // ...
    }

    fun onStarTap() {
        // ...
    }

    fun onDeleteTap() {
        // ...
    }
}

/** Hilt equivalent */
/*
@HiltViewModel
class BottomBarViewModel @Inject constructor(
    private val scopeHolder: ScopeHolder,
    @GridScopeId private val scopeId: String
) : ViewModel() {

    private val gridMediator by lazy {
        scopeHolder.getOrCreateMediator(scopeId) { GridMediator() }
    }
}
*/
