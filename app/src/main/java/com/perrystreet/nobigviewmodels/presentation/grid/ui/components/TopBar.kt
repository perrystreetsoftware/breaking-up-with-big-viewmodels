package com.perrystreet.nobigviewmodels.presentation.grid.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.material.icons.Icons
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.perrystreet.nobigviewmodels.presentation.grid.model.TopBarState
import com.perrystreet.nobigviewmodels.presentation.theme.AppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(
    state: TopBarState,
    albumName: String = "Camera",
    onDeleteTap: () -> Unit = {},
) {
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
                    onClick = onDeleteTap,
                    enabled = state.isDeleteEnabled,
                ) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = "Delete selected items",
                    )
                }
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

@Preview(showBackground = true, name = "Gallery Top Bar")
@Composable
fun GalleryTopBarPreview() {
    AppTheme {
        TopBar(state = TopBarState())
    }
}

@Preview(showBackground = true, name = "Gallery Top Bar - With Selection")
@Composable
fun GalleryTopBarWithSelectionPreview() {
    AppTheme {
        TopBar(
            state =
                TopBarState(
                    selectedCount = 3,
                    isDeleteVisible = true,
                    isDeleteEnabled = true,
                ),
        )
    }
}
