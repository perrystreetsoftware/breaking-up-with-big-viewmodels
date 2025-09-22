package com.perrystreet.nobigviewmodels.di.koin

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import org.koin.compose.getKoin
import org.koin.core.qualifier.named
import org.koin.core.scope.Scope

@Composable
fun KoinScopedComposable(
    scopeName: String,
    content: @Composable (scope: Scope) -> Unit,
) {
    val koin = getKoin()
    val scope =
        remember {
            koin.getOrCreateScope(scopeName, named(scopeName))
        }

    DisposableEffect(scope) {
        onDispose {
            scope.close()
        }
    }

    content(scope)
}
