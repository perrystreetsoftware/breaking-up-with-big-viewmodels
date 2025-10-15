package com.perrystreet.nobigviewmodels.presentation.grid.ui.components

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
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
import androidx.compose.material3.CircularProgressIndicator
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

@OptIn(ExperimentalFoundationApi::class, ExperimentalSharedTransitionApi::class)
@Composable
fun MediaItem(
    media: GridMediaUiModel,
    onLongTap: (String) -> Unit = {},
    onMediaTap: (String) -> Unit = {},
    sharedTransitionScope: SharedTransitionScope,
    animatedContentScope: AnimatedVisibilityScope,
) {
    Card(
        modifier =
            Modifier
                .fillMaxWidth()
                .aspectRatio(1f)
                .combinedClickable(
                    onClick = { onMediaTap(media.id) },
                    onLongClick = { onLongTap(media.id) },
                ),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
    ) {
        Box {
            with(sharedTransitionScope) {
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
                            .clip(RoundedCornerShape(12.dp))
                            .sharedBounds(
                                rememberSharedContentState(key = "media-${media.id}"),
                                animatedVisibilityScope = animatedContentScope,
                                clipInOverlayDuringTransition = OverlayClip(RoundedCornerShape(12.dp)),
                            ),
                    contentScale = ContentScale.Crop,
                )
            }

            if (media.isDeleting) {
                Box(
                    modifier =
                        Modifier
                            .fillMaxSize()
                            .background(Color.Black.copy(alpha = 0.6f)),
                    contentAlignment = Alignment.Center,
                ) {
                    CircularProgressIndicator(
                        color = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.size(48.dp),
                    )
                }
            }

            if (media.selected && !media.isDeleting) {
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

@OptIn(ExperimentalSharedTransitionApi::class)
@Preview(showBackground = true, name = "Single Media Item")
@Composable
fun MediaItemPreview() {
    AppTheme {
        androidx.compose.animation.SharedTransitionLayout {
            androidx.compose.animation.AnimatedVisibility(visible = true) {
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
                    sharedTransitionScope = this@SharedTransitionLayout,
                    animatedContentScope = this@AnimatedVisibility,
                )
            }
        }
    }
}
