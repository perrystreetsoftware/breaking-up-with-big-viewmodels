package com.perrystreet.nobigviewmodels.presentation.detail.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.perrystreet.nobigviewmodels.domain.usecase.GetMediaUseCase
import com.perrystreet.nobigviewmodels.presentation.grid.mapper.GridMediaUiModelMapper
import com.perrystreet.nobigviewmodels.presentation.grid.model.GridMediaUiModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.koin.android.annotation.KoinViewModel
import org.koin.core.annotation.InjectedParam

@KoinViewModel
class MediaDetailViewModel(
    private val getMediaUseCase: GetMediaUseCase,
    @InjectedParam private val mediaId: String,
) : ViewModel() {
    private val _media = MutableStateFlow<GridMediaUiModel?>(null)
    val media: StateFlow<GridMediaUiModel?> = _media.asStateFlow()

    init {
        viewModelScope.launch {
            getMediaUseCase().collect { mediaList ->
                val uiMediaList = GridMediaUiModelMapper.fromMediaList(mediaList)
                val selectedMedia = uiMediaList.find { it.id == mediaId }
                _media.value = selectedMedia
            }
        }
    }
}

/** Hilt equivalent */
/*
@HiltViewModel
class MediaDetailViewModel @Inject constructor(
    private val getMediaUseCase: GetMediaUseCase
) : ViewModel() {
    // ...
}
*/
