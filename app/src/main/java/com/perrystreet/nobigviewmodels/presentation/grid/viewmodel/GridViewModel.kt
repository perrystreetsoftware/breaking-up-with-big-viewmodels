package com.perrystreet.nobigviewmodels.presentation.grid.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.perrystreet.nobigviewmodels.domain.usecase.GetMediaUseCase
import com.perrystreet.nobigviewmodels.presentation.grid.mapper.GridMediaUiModelMapper
import com.perrystreet.nobigviewmodels.presentation.grid.mediator.GridMediator
import com.perrystreet.nobigviewmodels.presentation.grid.model.GridState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import org.koin.android.annotation.KoinViewModel

@KoinViewModel
class GridViewModel(
    private val getMediaUseCase: GetMediaUseCase,
    private val gridMediator: GridMediator,
) : ViewModel() {
    val state: StateFlow<GridState> = gridMediator.state

    init {
        viewModelScope.launch(Dispatchers.Main) {
            getMediaUseCase()
                .map {
                    GridState(GridMediaUiModelMapper.fromMediaList(it))
                }.collect { newState ->
                    gridMediator.updateState(newState)
                }
        }
    }

    fun onMediaLongTap(mediaId: String) {
        gridMediator.toggleSelection(mediaId)
    }
}

/** Hilt equivalent */
/*
@HiltViewModel
class GridViewModel @Inject constructor(
    private val getMediaUseCase: GetMediaUseCase,
    private val scopeHolder: ScopeHolder,
    @GridScopeId private val scopeId: String
) : ViewModel() {
    private val gridMediator by lazy {
        scopeHolder.getOrCreateMediator(scopeId) { GridMediator() }
    }
}
*/
