package com.perrystreet.nobigviewmodels.domain.usecase

import com.perrystreet.nobigviewmodels.data.repository.MediaRepository
import com.perrystreet.nobigviewmodels.utils.Optional
import io.reactivex.rxjava3.core.Observable
import org.koin.core.annotation.Factory

@Factory
class GetMediaErrorUseCase(
    private val mediaRepository: MediaRepository,
) {
    operator fun invoke(): Observable<Optional<Throwable>> =
        mediaRepository.state
            .map { it.error }
}
