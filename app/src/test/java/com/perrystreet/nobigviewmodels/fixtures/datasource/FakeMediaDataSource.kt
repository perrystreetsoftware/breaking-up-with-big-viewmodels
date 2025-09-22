package com.perrystreet.nobigviewmodels.fixtures.datasource

import com.perrystreet.nobigviewmodels.data.datasource.MediaDataSource
import com.perrystreet.nobigviewmodels.domain.model.Media
import org.koin.core.annotation.Factory
import java.util.Date

@Factory
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
