package com.perrystreet.nobigviewmodels.presentation.grid.ui.preview

import com.perrystreet.nobigviewmodels.data.datasource.MediaDataSource
import com.perrystreet.nobigviewmodels.domain.model.Media
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import java.util.Date

val gridPreviewModule =
    module {
        singleOf(::FakeMediaDataSource) { bind<MediaDataSource>() }
    }

class FakeMediaDataSource : MediaDataSource {
    override suspend fun getMediaList(): List<Media> =
        listOf(
            Media(
                id = "id1",
                title = "Beautiful Landscape",
                imageUrl = "file:///android_asset/sample_image_1.jpg",
                thumbnailUrl = "file:///android_asset/sample_image_1.jpg",
                date = Date(System.currentTimeMillis() - 86400000 * 1), // 1 day ago
                type = Media.Type.Photo,
            ),
            Media(
                id = "id2",
                title = "City View",
                imageUrl = "file:///android_asset/sample_image_2.jpg",
                thumbnailUrl = "file:///android_asset/sample_image_2.jpg",
                date = Date(System.currentTimeMillis() - 86400000 * 2), // 2 days ago
                type = Media.Type.Photo,
            ),
        )
}
