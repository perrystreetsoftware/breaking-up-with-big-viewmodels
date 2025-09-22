package com.perrystreet.nobigviewmodels.presentation.grid.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.perrystreet.nobigviewmodels.presentation.grid.ui.components.BottomBar
import com.perrystreet.nobigviewmodels.presentation.grid.ui.components.Grid
import com.perrystreet.nobigviewmodels.presentation.grid.ui.components.TopBar
import com.perrystreet.nobigviewmodels.presentation.grid.viewmodel.BottomBarViewModel
import com.perrystreet.nobigviewmodels.presentation.grid.viewmodel.GridViewModel
import com.perrystreet.nobigviewmodels.presentation.grid.viewmodel.TopBarViewModel
import com.perrystreet.nobigviewmodels.presentation.theme.AppTheme
import org.koin.androidx.compose.koinViewModel
import org.koin.core.scope.Scope

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GridScreen(
    scope: Scope,
    onMediaTap: (String) -> Unit,
) {
    val gridViewModel: GridViewModel = koinViewModel(scope = scope)
    val gridState by gridViewModel.state.collectAsState()

    val topBarViewModel: TopBarViewModel = koinViewModel(scope = scope)
    val selectedCount by topBarViewModel.selectedCount.collectAsState(initial = 0)

    val bottomBarViewModel: BottomBarViewModel = koinViewModel(scope = scope)
    val isBottomBarVisible by bottomBarViewModel.isVisible.collectAsState(initial = false)

    AppTheme {
        Scaffold(
            topBar = { TopBar(selectedCount = selectedCount) },
            bottomBar = {
                BottomBar(
                    isVisible = isBottomBarVisible,
                    onFavoriteTap = bottomBarViewModel::onFavoriteTap,
                    onShareTap = bottomBarViewModel::onShareTap,
                    onStarTap = bottomBarViewModel::onStarTap,
                    onDeleteTap = bottomBarViewModel::onDeleteTap,
                )
            },
        ) { paddingValues ->
            Grid(
                state = gridState,
                modifier = Modifier.padding(paddingValues),
                onLongTap = gridViewModel::onMediaLongTap,
                onMediaTap = onMediaTap,
            )
        }
    }
}
