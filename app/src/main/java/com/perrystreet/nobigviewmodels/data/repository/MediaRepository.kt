package com.perrystreet.nobigviewmodels.data.repository

import com.perrystreet.nobigviewmodels.data.datasource.MediaDataSource
import com.perrystreet.nobigviewmodels.domain.model.Media
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.plusAssign
import io.reactivex.rxjava3.subjects.BehaviorSubject

@org.koin.core.annotation.Single
class MediaRepository(
    private val mediaDataSource: MediaDataSource,
) {
    data class State(
        val mediaList: List<Media> = emptyList(),
        val error: Throwable? = null,
    )

    private val _state = BehaviorSubject.createDefault<State>(State())
    val state: Observable<State> = _state.hide()

    private val disposables = CompositeDisposable()

    fun loadMedia() {
        disposables +=
            mediaDataSource
                .getMediaList()
                .subscribe(
                    { mediaList ->
                        updateState { it.copy(mediaList = mediaList) }
                    },
                    { error ->
                        updateState { it.copy(error = error) }
                    },
                )
    }

    fun uploadMedia(filePath: String) {
        disposables +=
            mediaDataSource
                .uploadMedia(filePath)
                .subscribe(
                    { media ->
                        updateState { state ->
                            val updatedList = state.mediaList + media
                            state.copy(mediaList = updatedList)
                        }
                    },
                    { error ->
                        updateState { it.copy(error = error) }
                    },
                )
    }

    fun deleteMedia(mediaIds: List<String>) {
        disposables +=
            mediaDataSource
                .deleteMedia(mediaIds)
                .doOnSubscribe {
                    updateState { state ->
                        val updatedList =
                            state.mediaList.map { if (it.id in mediaIds) it.copy(isPending = true) else it }
                        state.copy(mediaList = updatedList)
                    }
                }.subscribe(
                    {
                        updateState { state ->
                            val updatedList = state.mediaList.filterNot { it.id in mediaIds }
                            state.copy(mediaList = updatedList)
                        }
                    },
                    { error ->
                        updateState { it.copy(error = error) }
                    },
                )
    }

    private fun updateState(transform: (State) -> State) {
        _state.onNext(transform(_state.value!!))
    }
}
