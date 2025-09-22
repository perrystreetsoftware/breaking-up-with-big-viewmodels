package com.perrystreet.nobigviewmodels.domain.usecase

import com.perrystreet.nobigviewmodels.data.repository.MediaRepository
import com.perrystreet.nobigviewmodels.domain.model.Media
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.koin.core.annotation.Factory

@Factory
class GetMediaUseCase(
    private val mediaRepository: MediaRepository,
) {
    operator fun invoke(): Flow<List<Media>> =
        mediaRepository.getMediaList().map { mediaList ->
            mediaList.sortedByDescending { it.date.time }
        }
}
