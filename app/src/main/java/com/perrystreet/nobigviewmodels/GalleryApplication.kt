package com.perrystreet.nobigviewmodels

import android.app.Application
import com.perrystreet.nobigviewmodels.di.koin.AppComponentScan
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.ksp.generated.module

class GalleryApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@GalleryApplication)
            modules(AppComponentScan().module)
        }
    }
}

/** Hilt equivalent */
// Add @HiltAndroidApp annotation and remove startKoin
