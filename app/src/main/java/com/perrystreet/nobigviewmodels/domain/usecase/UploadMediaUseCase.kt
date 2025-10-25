package com.perrystreet.nobigviewmodels.domain.usecase

import com.perrystreet.nobigviewmodels.data.repository.MediaRepository
import org.koin.core.annotation.Factory

@Factory
class UploadMediaUseCase(
    private val mediaRepository: MediaRepository,
) {
    operator fun invoke(filePath: String) {
        mediaRepository.uploadMedia(filePath)
    }
}
