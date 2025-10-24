package com.perrystreet.nobigviewmodels.presentation.detail.viewmodel

import com.perrystreet.nobigviewmodels.domain.usecase.GetMediaUseCase
import com.perrystreet.nobigviewmodels.presentation.base.BaseLifecycleViewModel
import com.perrystreet.nobigviewmodels.presentation.grid.mapper.GridMediaUiModelMapper
import com.perrystreet.nobigviewmodels.presentation.grid.model.GridMediaUiModel
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.kotlin.plusAssign
import io.reactivex.rxjava3.subjects.BehaviorSubject
import org.koin.android.annotation.KoinViewModel
import org.koin.core.annotation.InjectedParam

@KoinViewModel
class MediaDetailViewModel(
    private val getMediaUseCase: GetMediaUseCase,
    private val gridMediaUiModelMapper: GridMediaUiModelMapper,
    @InjectedParam private val mediaId: String,
) : BaseLifecycleViewModel() {
    private val _state = BehaviorSubject.create<GridMediaUiModel>()
    val state: Observable<GridMediaUiModel> = _state

    override fun onFirstAppear() {
        disposables +=
            getMediaUseCase()
                .subscribe { mediaList ->
                    val uiMediaList = gridMediaUiModelMapper(mediaList)
                    val selectedMedia = uiMediaList.find { it.id == mediaId } ?: return@subscribe
                    _state.onNext(selectedMedia)
                }
    }
}

/** Hilt equivalent */
/*
@HiltViewModel
class MediaDetailViewModel @Inject constructor(
    private val getMediaUseCase: GetMediaUseCase
) : ViewModel() {
    // ...
}
*/
