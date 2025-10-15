package com.perrystreet.nobigviewmodels

import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.perrystreet.nobigviewmodels.di.koin.KoinScopedComposable
import com.perrystreet.nobigviewmodels.navigation.Routes
import com.perrystreet.nobigviewmodels.presentation.detail.ui.MediaDetailScreen
import com.perrystreet.nobigviewmodels.presentation.grid.ui.GridScreen

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun AppNavHost() {
    val navController = rememberNavController()

    SharedTransitionLayout {
        NavHost(
            navController = navController,
            startDestination = Routes.GRID,
        ) {
            composable(Routes.GRID) {
                KoinScopedComposable(scopeName = Routes.GRID) { scope ->
                    GridScreen(
                        scope = scope,
                        onMediaTap = { mediaId ->
                            navController.navigate(Routes.mediaDetail(mediaId))
                        },
                        sharedTransitionScope = this@SharedTransitionLayout,
                        animatedContentScope = this@composable,
                    )
                }
            }

            composable("${Routes.MEDIA_DETAIL}/{mediaId}") { backStackEntry ->
                val mediaId =
                    backStackEntry.arguments?.getString("mediaId") ?: return@composable
                // There's no Mediator in MediaDetailScreen, so no need to pass a scope as above
                MediaDetailScreen(
                    mediaId = mediaId,
                    onBackTap = {
                        navController.popBackStack()
                    },
                    sharedTransitionScope = this@SharedTransitionLayout,
                    animatedContentScope = this@composable,
                )
            }
        }
    }
}

/** Hilt equivalent */
// Add scopeHolder: ScopeHolder as a param in AppNavHost
// Replace KoinScopedComposable with HiltScopedComposable
// Remove the scope param from GridScreen
