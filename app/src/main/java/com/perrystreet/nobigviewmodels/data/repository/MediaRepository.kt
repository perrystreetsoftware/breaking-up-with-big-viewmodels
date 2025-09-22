package com.perrystreet.nobigviewmodels.data.repository

import com.perrystreet.nobigviewmodels.data.datasource.MediaDataSource
import com.perrystreet.nobigviewmodels.domain.model.Media
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import org.koin.core.annotation.Single

@Single
class MediaRepository(
    private val mediaDataSource: MediaDataSource,
) {
    private val _cachedMedia = MutableStateFlow<List<Media>>(emptyList())

    fun getMediaList(): Flow<List<Media>> =
        flow {
            val mediaList = mediaDataSource.getMediaList()
            _cachedMedia.value = mediaList
            emit(mediaList)
        }.flowOn(Dispatchers.IO)
}
