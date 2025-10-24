package com.perrystreet.nobigviewmodels.data.repository

import com.perrystreet.nobigviewmodels.data.datasource.MediaDataSource
import com.perrystreet.nobigviewmodels.domain.model.Media
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable
import org.koin.core.annotation.Single

@Single
class MediaRepository(
    private val mediaDataSource: MediaDataSource,
) {
    fun getMediaList(): Observable<List<Media>> = mediaDataSource.getMediaList()

    fun deleteMedia(mediaIds: List<String>): Completable = Completable.complete()
}

// data sealed class Result<out T> {
//    data class moments<T>(val data: T) : Result<T>()
//    data class Error(val exception: Throwable) : Result<Nothing>()
// }
