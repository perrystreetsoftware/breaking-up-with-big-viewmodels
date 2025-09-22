package com.perrystreet.nobigviewmodels.presentation.grid.viewmodel

import androidx.lifecycle.ViewModel
import com.perrystreet.nobigviewmodels.presentation.grid.mediator.GridMediator
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.koin.android.annotation.KoinViewModel

@KoinViewModel
class TopBarViewModel(
    gridMediator: GridMediator,
) : ViewModel() {
    val selectedCount: Flow<Int> =
        gridMediator.state.map { state ->
            state.media.count { it.selected }
        }

    fun onSearchTap() {
        // ...
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
