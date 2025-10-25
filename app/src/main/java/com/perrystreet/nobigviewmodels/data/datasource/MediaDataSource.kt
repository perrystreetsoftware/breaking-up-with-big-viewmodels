package com.perrystreet.nobigviewmodels.data.datasource

import com.perrystreet.nobigviewmodels.domain.model.Media
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

interface MediaDataSource {
    fun getMediaList(): Single<List<Media>>

    fun uploadMedia(filePath: String): Single<Media>

    fun deleteMedia(mediaIds: List<String>): Completable
}
