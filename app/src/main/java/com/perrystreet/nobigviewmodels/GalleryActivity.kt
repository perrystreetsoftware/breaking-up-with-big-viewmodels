package com.perrystreet.nobigviewmodels

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent

class GalleryActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppNavHost()
        }
    }
}

/** Hilt equivalent */
/*
@AndroidEntryPoint
class GalleryActivity : ComponentActivity() {

    @Inject
    lateinit var scopeHolder: ScopeHolder

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppNavHost(scopeHolder = scopeHolder)
        }
    }
}
*/
