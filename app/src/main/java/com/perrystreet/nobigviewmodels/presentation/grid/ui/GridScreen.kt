package com.perrystreet.nobigviewmodels.presentation.grid.ui

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.perrystreet.nobigviewmodels.di.koin.AppComponentScan
import com.perrystreet.nobigviewmodels.di.koin.KoinScopedComposable
import com.perrystreet.nobigviewmodels.navigation.Routes
import com.perrystreet.nobigviewmodels.presentation.grid.ui.components.BottomBar
import com.perrystreet.nobigviewmodels.presentation.grid.ui.components.Grid
import com.perrystreet.nobigviewmodels.presentation.grid.ui.components.TopBar
import com.perrystreet.nobigviewmodels.presentation.grid.ui.preview.gridPreviewModule
import com.perrystreet.nobigviewmodels.presentation.theme.AppTheme
import org.koin.compose.KoinApplication
import org.koin.ksp.generated.module

@OptIn(ExperimentalMaterial3Api::class, ExperimentalSharedTransitionApi::class)
@Composable
fun GridScreen(
    onMediaTap: (String) -> Unit,
    sharedTransitionScope: SharedTransitionScope,
    animatedContentScope: AnimatedVisibilityScope,
) {
    AppTheme {
        Scaffold(
            topBar = { TopBar() },
            bottomBar = { BottomBar() },
        ) { paddingValues ->
            Grid(
                modifier = Modifier.padding(paddingValues),
                onMediaTap = onMediaTap,
                sharedTransitionScope = sharedTransitionScope,
                animatedContentScope = animatedContentScope,
            )
        }
    }
}

// TODO do we want to move previews to the test folder?
// TODO or can we at least re-use the testFixtures

@SuppressLint("UnusedContentLambdaTargetStateParameter")
@OptIn(ExperimentalSharedTransitionApi::class)
@Preview(showBackground = true)
@Composable
private fun GridScreenPreview() {
    KoinApplication(application = {
        modules(AppComponentScan().module, gridPreviewModule)
    }) {
        SharedTransitionLayout {
            AnimatedContent(
                targetState = "grid",
                label = "preview",
            ) {
                KoinScopedComposable(scopeName = Routes.GRID) { scope ->
                    GridScreen(
                        onMediaTap = {},
                        sharedTransitionScope = this@SharedTransitionLayout,
                        animatedContentScope = this@AnimatedContent,
                    )
                }
            }
        }
    }
}


