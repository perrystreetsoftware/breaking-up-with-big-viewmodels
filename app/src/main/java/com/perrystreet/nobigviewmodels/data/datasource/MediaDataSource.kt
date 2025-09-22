package com.perrystreet.nobigviewmodels.data.datasource

import com.perrystreet.nobigviewmodels.domain.model.Media

interface MediaDataSource {
    suspend fun getMediaList(): List<Media>
}
