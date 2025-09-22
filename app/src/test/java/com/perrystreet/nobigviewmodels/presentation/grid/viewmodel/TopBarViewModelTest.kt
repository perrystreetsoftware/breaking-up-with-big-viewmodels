package com.perrystreet.nobigviewmodels.presentation.grid.viewmodel

import com.perrystreet.nobigviewmodels.navigation.Routes
import com.perrystreet.nobigviewmodels.presentation.BaseBehaviorSpec
import io.kotest.matchers.shouldBe
import kotlinx.coroutines.flow.first
import org.junit.jupiter.api.extension.ExtendWith
import org.junit.jupiter.api.extension.Extension
import org.koin.core.qualifier.named
import org.koin.java.KoinJavaComponent

@ExtendWith(Extension::class)
class TopBarViewModelTest : BaseBehaviorSpec() {
    init {
        Given("I open the grid screen") {
            val gridScope =
                getKoin().getOrCreateScope(
                    Routes.GRID,
                    named(Routes.GRID),
                )

            val gridViewModel: GridViewModel = gridScope.get()
            val topBarViewModel: TopBarViewModel = gridScope.get()

            Then("The top bar does not show any selected items") {
                topBarViewModel.selectedCount.first() shouldBe 0
            }

            When("I long tap on a media item") {
                gridViewModel.onMediaLongTap("id1")

                Then("The top bar shows 1 selected item") {
                    topBarViewModel.selectedCount.first() shouldBe 1
                }

                And("I long tap on another media item") {
                    gridViewModel.onMediaLongTap("id2")

                    Then("The top bar shows 2 selected items") {
                        topBarViewModel.selectedCount.first() shouldBe 2
                    }

                    And("I long tap again on the same media items") {
                        gridViewModel.onMediaLongTap("id1")
                        gridViewModel.onMediaLongTap("id2")

                        Then("The top bar does not show any selected items") {
                            topBarViewModel.selectedCount.first() shouldBe 0
                        }
                    }
                }
            }
        }
    }
}
