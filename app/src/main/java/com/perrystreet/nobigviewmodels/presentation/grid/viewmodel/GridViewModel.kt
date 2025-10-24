package com.perrystreet.nobigviewmodels.presentation.grid.viewmodel

import com.perrystreet.nobigviewmodels.data.datasource.ISchedulerProvider
import com.perrystreet.nobigviewmodels.domain.usecase.GetMediaUseCase
import com.perrystreet.nobigviewmodels.presentation.base.BaseLifecycleViewModel
import com.perrystreet.nobigviewmodels.presentation.grid.mapper.GridMediaUiModelMapper
import com.perrystreet.nobigviewmodels.presentation.grid.mediator.GridAction
import com.perrystreet.nobigviewmodels.presentation.grid.mediator.GridMediator
import com.perrystreet.nobigviewmodels.presentation.grid.model.GridState
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.kotlin.plusAssign
import org.koin.android.annotation.KoinViewModel

@KoinViewModel
class GridViewModel(
    private val getMediaUseCase: GetMediaUseCase,
    private val gridMediator: GridMediator,
    private val gridMediaUiModelMapper: GridMediaUiModelMapper,
    private val schedulerProvider: ISchedulerProvider,
) : BaseLifecycleViewModel() {
    val state: Observable<GridState> = gridMediator.state

    override fun onFirstAppear() {
        loadMedia()
    }

    private fun loadMedia() {
        disposables +=
            getMediaUseCase()
                .map { gridMediaUiModelMapper(it) }
                .subscribeOn(schedulerProvider.ioScheduler)
                .observeOn(schedulerProvider.mainScheduler)
                .subscribe { uiModels ->
                    gridMediator.dispatch(GridAction.MediaLoaded(uiModels))
                }
    }

    fun onMediaLongTap(mediaId: String) {
        gridMediator.dispatch(GridAction.ToggleSelection(mediaId))
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
