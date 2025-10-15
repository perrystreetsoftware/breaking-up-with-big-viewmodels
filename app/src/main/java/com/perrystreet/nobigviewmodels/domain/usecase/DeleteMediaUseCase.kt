package com.perrystreet.nobigviewmodels.domain.usecase

import com.perrystreet.nobigviewmodels.data.repository.MediaRepository
import org.koin.core.annotation.Factory

@Factory
class DeleteMediaUseCase(
    private val mediaRepository: MediaRepository,
) {
    suspend operator fun invoke(mediaIds: List<String>): Result<Unit> =
        try {
            mediaRepository.deleteMedia(mediaIds)
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
}
