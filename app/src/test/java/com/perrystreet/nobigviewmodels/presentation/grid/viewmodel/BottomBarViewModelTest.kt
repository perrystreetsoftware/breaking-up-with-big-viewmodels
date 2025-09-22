package com.perrystreet.nobigviewmodels.presentation.grid.viewmodel

import com.perrystreet.nobigviewmodels.navigation.Routes
import com.perrystreet.nobigviewmodels.presentation.BaseBehaviorSpec
import io.kotest.matchers.shouldBe
import kotlinx.coroutines.flow.first
import org.junit.jupiter.api.extension.ExtendWith
import org.junit.jupiter.api.extension.Extension
import org.koin.core.qualifier.named

@ExtendWith(Extension::class)
class BottomBarViewModelTest : BaseBehaviorSpec() {
    init {
        Given("I open the grid screen") {
            val gridScope =
                getKoin().getOrCreateScope(
                    Routes.GRID,
                    named(Routes.GRID),
                )

            val gridViewModel: GridViewModel = gridScope.get()
            val bottomBarViewModel: BottomBarViewModel = gridScope.get()

            Then("The bottom bar is hidden") {
                bottomBarViewModel.isVisible.first() shouldBe false
            }

            When("I long tap on a media item") {
                gridViewModel.onMediaLongTap("id1")

                Then("The bottom bar is visible") {
                    bottomBarViewModel.isVisible.first() shouldBe true
                }

                And("I long tap again on the same media item") {
                    gridViewModel.onMediaLongTap("id1")

                    Then("The bottom bar is hidden") {
                        bottomBarViewModel.isVisible.first() shouldBe false
                    }
                }
            }
        }
    }
}
