package com.perrystreet.nobigviewmodels.presentation.grid.ui.components

import androidx.compose.material.icons.Icons
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
import com.perrystreet.nobigviewmodels.presentation.theme.AppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(
    selectedCount: Int,
    albumName: String = "Camera",
) {
    var showMenu by remember { mutableStateOf(false) }

    TopAppBar(
        title = {
            Text(
                text = if (selectedCount > 0) "$selectedCount selected" else albumName,
                fontSize = 20.sp,
                fontWeight = FontWeight.Medium,
            )
        },
        actions = {
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
        TopBar(selectedCount = 0)
    }
}

@Preview(showBackground = true, name = "Gallery Top Bar - With Selection")
@Composable
fun GalleryTopBarWithSelectionPreview() {
    AppTheme {
        TopBar(selectedCount = 3)
    }
}
