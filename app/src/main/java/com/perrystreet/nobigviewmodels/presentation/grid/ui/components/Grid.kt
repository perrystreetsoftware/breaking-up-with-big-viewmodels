package com.perrystreet.nobigviewmodels.presentation.grid.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.perrystreet.nobigviewmodels.presentation.grid.model.GridMediaUiModel
import com.perrystreet.nobigviewmodels.presentation.grid.model.GridState
import com.perrystreet.nobigviewmodels.presentation.theme.AppTheme

@Composable
fun Grid(
    state: GridState,
    modifier: Modifier = Modifier,
    onLongTap: (String) -> Unit = {},
    onMediaTap: (String) -> Unit = {},
) {
    Surface(
        modifier = modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background,
    ) {
        if (state.media.isEmpty()) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center,
            ) {
                CircularProgressIndicator()
            }
        } else {
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                modifier =
                    Modifier
                        .fillMaxSize()
                        .padding(8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                items(state.media) { media ->
                    MediaItem(
                        media = media,
                        onLongTap = onLongTap,
                        onMediaClick = onMediaTap,
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true, name = "With Media")
@Composable
fun GalleryContentWithMediaPreview() {
    AppTheme {
        Grid(
            state =
                GridState(
                    media =
                        listOf(
                            GridMediaUiModel(
                                id = "1",
                                title = "Beautiful Landscape",
                                thumbnailUrl = "",
                                imageUrl = "",
                                formattedDate = "Sep 15, 2025",
                                typeIcon = "📷",
                                typeColor =
                                    Color(0xFF4CAF50),
                            ),
                            GridMediaUiModel(
                                id = "2",
                                title = "City View",
                                thumbnailUrl = "",
                                imageUrl = "",
                                formattedDate = "Sep 14, 2025",
                                typeIcon = "🎥",
                                typeColor =
                                    Color(0xFF2196F3),
                            ),
                            GridMediaUiModel(
                                id = "3",
                                title = "Nature Scene",
                                thumbnailUrl = "",
                                imageUrl = "",
                                formattedDate = "Sep 13, 2025",
                                typeIcon = "🎞️",
                                typeColor =
                                    Color(0xFFFF9800),
                            ),
                            GridMediaUiModel(
                                id = "4",
                                title = "Mountain View",
                                thumbnailUrl = "",
                                imageUrl = "",
                                formattedDate = "Sep 12, 2025",
                                typeIcon = "📷",
                                typeColor =
                                    Color(0xFF4CAF50),
                            ),
                        ),
                ),
        )
    }
}

@Preview(showBackground = true, name = "Loading State")
@Composable
fun GalleryContentLoadingPreview() {
    AppTheme {
        Grid(
            state = GridState(media = emptyList()),
        )
    }
}
