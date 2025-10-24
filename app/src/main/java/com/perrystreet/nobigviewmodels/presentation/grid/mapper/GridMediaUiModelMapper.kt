package com.perrystreet.nobigviewmodels.presentation.grid.mapper

import android.annotation.SuppressLint
import androidx.compose.ui.graphics.Color
import com.perrystreet.nobigviewmodels.domain.model.Media
import com.perrystreet.nobigviewmodels.presentation.grid.model.GridMediaUiModel
import org.koin.core.annotation.Factory
import java.text.SimpleDateFormat
import java.util.Locale

@Factory
class GridMediaUiModelMapper() {
    @SuppressLint("ConstantLocale")
    private val dateFormatter = SimpleDateFormat("MMM dd, yyyy", Locale.getDefault())

    operator fun invoke(mediaList: List<Media>): List<GridMediaUiModel> =
        mediaList.map { media ->
            GridMediaUiModel(
                id = media.id,
                title = media.title,
                thumbnailUrl = media.thumbnailUrl,
                imageUrl = media.imageUrl,
                formattedDate = dateFormatter.format(media.date),
                typeIcon =
                    when (media.type) {
                        Media.Type.Photo -> "📷"
                        Media.Type.Video -> "🎥"
                        Media.Type.Gif -> "🎞️"
                    },
                typeColor =
                    when (media.type) {
                        Media.Type.Photo -> Color(0xFF4CAF50)
                        Media.Type.Video -> Color(0xFF2196F3)
                        Media.Type.Gif -> Color(0xFFFF9800)
                    },
            )
        }
}
