package com.perrystreet.nobigviewmodels.data.datasource

import com.perrystreet.nobigviewmodels.domain.model.Media
import io.reactivex.rxjava3.core.Observable
import org.koin.core.annotation.Factory
import java.util.Date

@Factory
class MediaDataSourceImpl : MediaDataSource {
    override fun getMediaList(): Observable<List<Media>> =
        Observable.just(
            listOf(
                Media(
                    id = "1",
                    title = "Mountains 1",
                    imageUrl = "file:///android_asset/sample_image_1.jpg",
                    thumbnailUrl = "file:///android_asset/sample_image_1.jpg",
                    date = Date(System.currentTimeMillis() - 86400000 * 1), // 1 day ago
                    type = Media.Type.Photo,
                ),
                Media(
                    id = "2",
                    title = "Mountains 2",
                    imageUrl = "file:///android_asset/sample_image_2.jpg",
                    thumbnailUrl = "file:///android_asset/sample_image_2.jpg",
                    date = Date(System.currentTimeMillis() - 86400000 * 2), // 2 days ago
                    type = Media.Type.Photo,
                ),
                Media(
                    id = "3",
                    title = "Mountains 3",
                    imageUrl = "file:///android_asset/sample_image_3.jpg",
                    thumbnailUrl = "file:///android_asset/sample_image_3.jpg",
                    date = Date(System.currentTimeMillis() - 86400000 * 3), // 3 days ago
                    type = Media.Type.Photo,
                ),
                Media(
                    id = "4",
                    title = "Sunset 1",
                    imageUrl = "file:///android_asset/sample_image_4.jpg",
                    thumbnailUrl = "file:///android_asset/sample_image_4.jpg",
                    date = Date(System.currentTimeMillis() - 86400000 * 4), // 4 days ago
                    type = Media.Type.Photo,
                ),
                Media(
                    id = "5",
                    title = "Waterfall 1",
                    imageUrl = "file:///android_asset/sample_image_5.jpg",
                    thumbnailUrl = "file:///android_asset/sample_image_5.jpg",
                    date = Date(System.currentTimeMillis() - 86400000 * 5), // 5 days ago
                    type = Media.Type.Photo,
                ),
                Media(
                    id = "6",
                    title = "Waterfall 2",
                    imageUrl = "file:///android_asset/sample_image_6.jpg",
                    thumbnailUrl = "file:///android_asset/sample_image_6.jpg",
                    date = Date(System.currentTimeMillis() - 86400000 * 6), // 6 days ago
                    type = Media.Type.Photo,
                ),
                Media(
                    id = "7",
                    title = "Beach 1",
                    imageUrl = "file:///android_asset/sample_image_7.jpg",
                    thumbnailUrl = "file:///android_asset/sample_image_7.jpg",
                    date = Date(System.currentTimeMillis() - 86400000 * 7), // 7 days ago
                    type = Media.Type.Photo,
                ),
                Media(
                    id = "8",
                    title = "Beach 2",
                    imageUrl = "file:///android_asset/sample_image_8.jpg",
                    thumbnailUrl = "file:///android_asset/sample_image_8.jpg",
                    date = Date(System.currentTimeMillis() - 86400000 * 8), // 8 days ago
                    type = Media.Type.Photo,
                ),
            ),
        )
}
