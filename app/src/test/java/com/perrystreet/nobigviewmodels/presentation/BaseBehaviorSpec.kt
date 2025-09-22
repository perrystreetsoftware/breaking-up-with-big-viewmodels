package com.perrystreet.nobigviewmodels.presentation

import com.perrystreet.nobigviewmodels.di.koin.AppComponentScan
import com.perrystreet.nobigviewmodels.fixtures.TestComponentScan
import io.kotest.core.spec.IsolationMode
import io.kotest.core.spec.Spec
import io.kotest.core.spec.style.BehaviorSpec
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.ksp.generated.module
import org.koin.test.KoinTest

open class BaseBehaviorSpec :
    BehaviorSpec(),
    KoinTest {
    override fun isolationMode() = IsolationMode.InstancePerLeaf

    @OptIn(ExperimentalCoroutinesApi::class)
    private val testDispatcher = UnconfinedTestDispatcher()

    override suspend fun beforeSpec(spec: Spec) {
        super.beforeSpec(spec)
        Dispatchers.setMain(testDispatcher)

        startKoin {
            modules(
                AppComponentScan().module,
                TestComponentScan().module,
            )
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    override suspend fun afterSpec(spec: Spec) {
        super.afterSpec(spec)
        stopKoin()
        Dispatchers.resetMain()
    }
}
