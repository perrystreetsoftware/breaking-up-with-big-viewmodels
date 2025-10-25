package com.perrystreet.nobigviewmodels.presentation.grid.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.perrystreet.nobigviewmodels.navigation.Routes
import com.perrystreet.nobigviewmodels.presentation.grid.model.TopBarState
import com.perrystreet.nobigviewmodels.presentation.grid.viewmodel.GridViewModel
import com.perrystreet.nobigviewmodels.presentation.grid.viewmodel.TopBarViewModel
import kotlinx.coroutines.rx3.asFlow
import org.koin.androidx.compose.koinViewModel
import org.koin.compose.getKoin

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(albumName: String = "Gallery") {
    val scope = getKoin().getScope(Routes.GRID)
    val viewModel: TopBarViewModel = koinViewModel(scope = scope)
    val gridViewModel: GridViewModel = koinViewModel(scope = scope)

    LaunchedEffect(Unit) {
        viewModel.onViewAppear()
    }

    val state by viewModel.state.asFlow().collectAsState(initial = TopBarState())
    var showMenu by remember { mutableStateOf(false) }

    TopAppBar(
        title = {
            Text(
                text = if (state.selectedCount > 0) "${state.selectedCount} selected" else albumName,
                fontSize = 20.sp,
                fontWeight = FontWeight.Medium,
            )
        },
        actions = {
            AnimatedVisibility(visible = state.isDeleteVisible) {
                IconButton(
                    onClick = viewModel::onDeleteTap,
                    enabled = state.isDeleteEnabled,
                ) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = "Delete selected items",
                    )
                }
            }

            IconButton(
                onClick = {
                    gridViewModel.onMediaUpload("file:///android_asset/sample_image_8.jpg")
                },
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Upload photo",
                )
            }

            IconButton(onClick = { showMenu = true }) {
                Icon(
                    imageVector = Icons.Default.MoreVert,
                    contentDescription = "More options",
                )
            }
            DropdownMenu(
                expanded = showMenu,
                onDismissRequest = { showMenu = false },
            ) {
                DropdownMenuItem(
                    text = { Text("Select items") },
                    onClick = { showMenu = false },
                )
                DropdownMenuItem(
                    text = { Text("Sort by") },
                    onClick = { showMenu = false },
                )
                DropdownMenuItem(
                    text = { Text("View options") },
                    onClick = { showMenu = false },
                )
                DropdownMenuItem(
                    text = { Text("Settings") },
                    onClick = { showMenu = false },
                )
            }
        },
    )
}

// @Preview(showBackground = true, name = "Gallery Top Bar")
// @Composable
// fun GalleryTopBarPreview() {
//    AppTheme {
//        TopBar(state = TopBarState())
//    }
// }
//
// @Preview(showBackground = true, name = "Gallery Top Bar - With Selection")
// @Composable
// fun GalleryTopBarWithSelectionPreview() {
//    AppTheme {
//        TopBar(
//            state =
//                TopBarState(
//                    selectedCount = 3,
//                    isDeleteVisible = true,
//                    isDeleteEnabled = true,
//                ),
//        )
//    }
// }
