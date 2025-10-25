package com.perrystreet.nobigviewmodels.domain.usecase

import com.perrystreet.nobigviewmodels.data.repository.MediaRepository
import com.perrystreet.nobigviewmodels.domain.model.Media
import io.reactivex.rxjava3.core.Observable
import org.koin.core.annotation.Factory

@Factory
class GetMediaUseCase(
    private val mediaRepository: MediaRepository,
) {
    operator fun invoke(): Observable<List<Media>> =
        mediaRepository.state
            .map { it.mediaList }
            .filter { it.isNotEmpty() }
            .map { mediaList ->
                mediaList.sortedByDescending { it.date.time }
            }
}
