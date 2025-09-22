package com.perrystreet.nobigviewmodels.presentation.grid.viewmodel

import com.perrystreet.nobigviewmodels.navigation.Routes
import com.perrystreet.nobigviewmodels.presentation.BaseBehaviorSpec
import io.kotest.matchers.equals.shouldBeEqual
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.extension.ExtendWith
import org.junit.jupiter.api.extension.Extension
import org.koin.core.qualifier.named

@ExtendWith(Extension::class)
class GridViewModelTest : BaseBehaviorSpec() {
    init {
        Given("I open the grid screen") {
            val gridScope =
                getKoin().getOrCreateScope(
                    Routes.GRID,
                    named(Routes.GRID),
                )

            val gridViewModel: GridViewModel = gridScope.get()

            Then("I see a list of media") {
                with(gridViewModel.state.value) {
                    media.size shouldBe 2
                    media[0].id shouldBeEqual "id1"
                    media[0].title shouldBeEqual "Beautiful Landscape"
                    media[1].id shouldBeEqual "id2"
                    media[1].title shouldBeEqual "City View"
                }
            }
        }
    }
}
