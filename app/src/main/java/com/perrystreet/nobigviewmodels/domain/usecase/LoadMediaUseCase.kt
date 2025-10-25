package com.perrystreet.nobigviewmodels.domain.usecase

import com.perrystreet.nobigviewmodels.data.repository.MediaRepository
import org.koin.core.annotation.Factory

@Factory
class LoadMediaUseCase(
    private val mediaRepository: MediaRepository,
) {
    operator fun invoke() {
        mediaRepository.loadMedia()
    }
}
