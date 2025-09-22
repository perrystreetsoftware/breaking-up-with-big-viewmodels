package com.perrystreet.nobigviewmodels.presentation.grid.viewmodel

import androidx.lifecycle.ViewModel
import com.perrystreet.nobigviewmodels.presentation.grid.mediator.GridMediator
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.koin.android.annotation.KoinViewModel

@KoinViewModel
class BottomBarViewModel(
    gridMediator: GridMediator,
) : ViewModel() {
    val isVisible: Flow<Boolean> =
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
