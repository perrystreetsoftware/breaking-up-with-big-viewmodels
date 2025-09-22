package com.perrystreet.nobigviewmodels.presentation.grid.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.perrystreet.nobigviewmodels.presentation.theme.AppTheme

@Composable
fun BottomBar(
    isVisible: Boolean,
    onFavoriteTap: () -> Unit = {},
    onShareTap: () -> Unit = {},
    onStarTap: () -> Unit = {},
    onDeleteTap: () -> Unit = {},
) {
    if (!isVisible) return
    BottomAppBar(
        containerColor = MaterialTheme.colorScheme.surface,
        contentColor = MaterialTheme.colorScheme.onSurface,
    ) {
        Row(
            modifier =
                Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.SpaceEvenly,
        ) {
            IconButton(onClick = onFavoriteTap) {
                Icon(
                    imageVector = Icons.Default.Favorite,
                    contentDescription = "Add to favorites",
                    tint = MaterialTheme.colorScheme.primary,
                )
            }

            IconButton(onClick = onShareTap) {
                Icon(
                    imageVector = Icons.Default.Share,
                    contentDescription = "Share",
                    tint = MaterialTheme.colorScheme.primary,
                )
            }

            IconButton(onClick = onStarTap) {
                Icon(
                    imageVector = Icons.Default.Star,
                    contentDescription = "Add to starred",
                    tint = MaterialTheme.colorScheme.primary,
                )
            }

            IconButton(onClick = onDeleteTap) {
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = "Delete",
                    tint = MaterialTheme.colorScheme.error,
                )
            }
        }
    }
}

@Preview(showBackground = true, name = "Bottom Bar")
@Composable
fun BottomBarPreview() {
    AppTheme {
        BottomBar(isVisible = true)
    }
}
