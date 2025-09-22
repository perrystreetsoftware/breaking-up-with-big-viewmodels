package com.perrystreet.nobigviewmodels.presentation.grid.ui.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.perrystreet.nobigviewmodels.presentation.grid.model.GridMediaUiModel
import com.perrystreet.nobigviewmodels.presentation.theme.AppTheme

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MediaItem(
    media: GridMediaUiModel,
    onLongTap: (String) -> Unit = {},
    onMediaClick: (String) -> Unit = {},
) {
    Card(
        modifier =
            Modifier
                .fillMaxWidth()
                .aspectRatio(1f)
                .combinedClickable(
                    onClick = { onMediaClick(media.id) },
                    onLongClick = { onLongTap(media.id) },
                ),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
    ) {
        Box {
            AsyncImage(
                model =
                    ImageRequest
                        .Builder(LocalContext.current)
                        .data(media.thumbnailUrl)
                        .crossfade(true)
                        .build(),
                contentDescription = media.title,
                modifier =
                    Modifier
                        .fillMaxSize()
                        .clip(RoundedCornerShape(12.dp)),
                contentScale = ContentScale.Crop,
            )

            // Selection checkbox
            if (media.selected) {
                Box(
                    modifier =
                        Modifier
                            .align(Alignment.TopEnd)
                            .padding(8.dp)
                            .size(24.dp)
                            .background(
                                MaterialTheme.colorScheme.primary,
                                CircleShape,
                            ),
                    contentAlignment = Alignment.Center,
                ) {
                    Icon(
                        imageVector = Icons.Default.Check,
                        contentDescription = "Selected",
                        tint = MaterialTheme.colorScheme.onPrimary,
                        modifier = Modifier.size(16.dp),
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true, name = "Single Media Item")
@Composable
fun MediaItemPreview() {
    AppTheme {
        MediaItem(
            media =
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
        )
    }
}
