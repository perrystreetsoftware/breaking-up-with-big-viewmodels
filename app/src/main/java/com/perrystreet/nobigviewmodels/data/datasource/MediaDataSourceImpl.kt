package com.perrystreet.nobigviewmodels.data.datasource

import com.perrystreet.nobigviewmodels.domain.model.Media
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import java.util.Date
import java.util.concurrent.TimeUnit

@org.koin.core.annotation.Single
class MediaDataSourceImpl : MediaDataSource {
    // Simulates server-side storage
    private var nextId = 9
    private val serverMediaList =
        mutableListOf(
            Media(
                id = "1",
                title = "Mountains 1",
                imageUrl = "file:///android_asset/sample_image_1.jpg",
                thumbnailUrl = "file:///android_asset/sample_image_1.jpg",
                date = Date(System.currentTimeMillis() - 86400000 * 1),
                type = Media.Type.Photo,
                isPending = false,
            ),
            Media(
                id = "2",
                title = "Mountains 2",
                imageUrl = "file:///android_asset/sample_image_2.jpg",
                thumbnailUrl = "file:///android_asset/sample_image_2.jpg",
                date = Date(System.currentTimeMillis() - 86400000 * 2),
                type = Media.Type.Photo,
                isPending = false,
            ),
            Media(
                id = "3",
                title = "Mountains 3",
                imageUrl = "file:///android_asset/sample_image_3.jpg",
                thumbnailUrl = "file:///android_asset/sample_image_3.jpg",
                date = Date(System.currentTimeMillis() - 86400000 * 3),
                type = Media.Type.Photo,
                isPending = false,
            ),
            Media(
                id = "4",
                title = "Sunset 1",
                imageUrl = "file:///android_asset/sample_image_4.jpg",
                thumbnailUrl = "file:///android_asset/sample_image_4.jpg",
                date = Date(System.currentTimeMillis() - 86400000 * 4),
                type = Media.Type.Photo,
                isPending = false,
            ),
            Media(
                id = "5",
                title = "Waterfall 1",
                imageUrl = "file:///android_asset/sample_image_5.jpg",
                thumbnailUrl = "file:///android_asset/sample_image_5.jpg",
                date = Date(System.currentTimeMillis() - 86400000 * 5),
                type = Media.Type.Photo,
                isPending = false,
            ),
            Media(
                id = "6",
                title = "Waterfall 2",
                imageUrl = "file:///android_asset/sample_image_6.jpg",
                thumbnailUrl = "file:///android_asset/sample_image_6.jpg",
                date = Date(System.currentTimeMillis() - 86400000 * 6),
                type = Media.Type.Photo,
                isPending = false,
            ),
            Media(
                id = "7",
                title = "Beach 1",
                imageUrl = "file:///android_asset/sample_image_7.jpg",
                thumbnailUrl = "file:///android_asset/sample_image_7.jpg",
                date = Date(System.currentTimeMillis() - 86400000 * 7),
                type = Media.Type.Photo,
                isPending = false,
            ),
            Media(
                id = "8",
                title = "Beach 2",
                imageUrl = "file:///android_asset/sample_image_8.jpg",
                thumbnailUrl = "file:///android_asset/sample_image_8.jpg",
                date = Date(System.currentTimeMillis() - 86400000 * 8),
                type = Media.Type.Photo,
                isPending = false,
            ),
        )

    // Simulates API call to fetch media list
    override fun getMediaList(): Single<List<Media>> =
        Single
            .timer(2, TimeUnit.SECONDS)
            .map {
                serverMediaList.toList()
            }

    // Simulates API call to upload media
    override fun uploadMedia(filePath: String): Single<Media> =
        Single
            .fromCallable {
                val newMedia =
                    Media(
                        id = nextId.toString(),
                        title = "Photo $nextId",
                        imageUrl = filePath,
                        thumbnailUrl = filePath,
                        date = Date(),
                        type = Media.Type.Photo,
                        isPending = false,
                    )
                serverMediaList.add(0, newMedia)
                nextId++
                newMedia
            }.delaySubscription(2, TimeUnit.SECONDS)

    // Simulates API call to delete media
    override fun deleteMedia(mediaIds: List<String>): Completable =
        Completable
            .timer(2, TimeUnit.SECONDS)
            .doOnComplete {
                serverMediaList.removeAll { it.id in mediaIds }
            }
}
