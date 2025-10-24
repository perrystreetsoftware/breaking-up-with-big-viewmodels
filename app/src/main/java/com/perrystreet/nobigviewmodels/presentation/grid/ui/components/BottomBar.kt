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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.perrystreet.nobigviewmodels.navigation.Routes
import com.perrystreet.nobigviewmodels.presentation.grid.viewmodel.BottomBarViewModel
import kotlinx.coroutines.rx3.asFlow
import org.koin.androidx.compose.koinViewModel
import org.koin.compose.getKoin

@Composable
fun BottomBar() {
    val scope = getKoin().getScope(Routes.GRID)
    val viewModel: BottomBarViewModel = koinViewModel(scope = scope)

    LaunchedEffect(Unit) {
        viewModel.onViewAppear()
    }

    val isVisible by viewModel.state.asFlow().collectAsState(initial = false)
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
            IconButton(onClick = viewModel::onFavoriteTap) {
                Icon(
                    imageVector = Icons.Default.Favorite,
                    contentDescription = "Add to favorites",
                    tint = MaterialTheme.colorScheme.primary,
                )
            }

            IconButton(onClick = viewModel::onShareTap) {
                Icon(
                    imageVector = Icons.Default.Share,
                    contentDescription = "Share",
                    tint = MaterialTheme.colorScheme.primary,
                )
            }

            IconButton(onClick = viewModel::onStarTap) {
                Icon(
                    imageVector = Icons.Default.Star,
                    contentDescription = "Add to starred",
                    tint = MaterialTheme.colorScheme.primary,
                )
            }

            IconButton(onClick = viewModel::onDeleteTap) {
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = "Delete",
                    tint = MaterialTheme.colorScheme.error,
                )
            }
        }
    }
}

//@Preview(showBackground = true, name = "Bottom Bar")
//@Composable
//fun BottomBarPreview() {
//    AppTheme {
//        BottomBar(isVisible = true)
//    }
//}
