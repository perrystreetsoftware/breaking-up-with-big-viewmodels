package com.perrystreet.nobigviewmodels.data.repository

import com.perrystreet.nobigviewmodels.data.datasource.MediaDataSource
import com.perrystreet.nobigviewmodels.domain.model.Media
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.onStart
import org.koin.core.annotation.Single

@Single
class MediaRepository(
    private val mediaDataSource: MediaDataSource,
) {
    private val _cachedMedia = MutableStateFlow<List<Media>>(emptyList())

    fun getMediaList(): Flow<List<Media>> =
        _cachedMedia.onStart {
            if (_cachedMedia.value.isEmpty()) {
                val mediaList = mediaDataSource.getMediaList()
                _cachedMedia.value = mediaList
            }
        }

    suspend fun deleteMedia(mediaIds: List<String>) {
        delay(1000) // Simulate network request

        val updatedList =
            _cachedMedia.value.filter { media ->
                !mediaIds.contains(media.id)
            }
        _cachedMedia.value = updatedList
    }
}
