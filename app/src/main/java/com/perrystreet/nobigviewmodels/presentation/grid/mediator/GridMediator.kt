package com.perrystreet.nobigviewmodels.presentation.grid.mediator

import com.perrystreet.nobigviewmodels.presentation.grid.model.GridState
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.subjects.BehaviorSubject
import org.koin.core.annotation.Scope
import org.koin.core.annotation.Scoped

@Scope(name = "grid")
@Scoped
class GridMediator {
    private val _state = BehaviorSubject.createDefault(GridState(emptyList()))
    val state: Observable<GridState> = _state.hide()

    fun dispatch(action: GridAction) {
        val currentState = _state.value!!
        val newState = action.reduce(currentState)
        _state.onNext(newState)
    }
}
