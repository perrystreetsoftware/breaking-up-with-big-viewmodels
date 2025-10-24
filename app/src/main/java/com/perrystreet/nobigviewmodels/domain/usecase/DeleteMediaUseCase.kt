package com.perrystreet.nobigviewmodels.domain.usecase

import com.perrystreet.nobigviewmodels.data.repository.MediaRepository
import io.reactivex.rxjava3.core.Completable
import org.koin.core.annotation.Factory

@Factory
class DeleteMediaUseCase(
    private val mediaRepository: MediaRepository,
) {
    operator fun invoke(mediaIds: List<String>): Completable =
        mediaRepository.deleteMedia(mediaIds)
}
