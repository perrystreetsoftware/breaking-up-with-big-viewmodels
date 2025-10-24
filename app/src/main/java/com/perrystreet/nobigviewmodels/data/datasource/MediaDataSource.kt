package com.perrystreet.nobigviewmodels.data.datasource

import com.perrystreet.nobigviewmodels.domain.model.Media
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable

interface MediaDataSource {
    fun getMediaList(): Observable<List<Media>>

    fun uploadMedia(filePath: String): Completable

    fun deleteMedia(mediaIds: List<String>): Completable
}
