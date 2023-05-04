package dev.goobar.composelabs

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import dev.goobar.composelabs.domain.Planet
import dev.goobar.composelabs.domain.PlanetRepository
import dev.goobar.composelabs.planetslist.PlanetListViewModel
import dev.goobar.composelabs.planetslist.PlanetsListScreen
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.junit.Rule
import org.junit.Test

class StarWarsPlanetListTest {

    private val sampleData = listOf(
        Planet("Hoth"),
        Planet("Lothal"),
        Planet("Mandalore"),
    )

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun planetListDisplayedOnHomeScreenLoad() {
        composeTestRule.setContent {
            PlanetsListScreen(
                viewModel = PlanetListViewModel(
                    object : PlanetRepository {
                        override suspend fun getPlanets(): List<Planet> = withContext(Dispatchers.Main.immediate) {
                            sampleData
                        }
                    }
                ),
                onClick = {}
            )
        }

        composeTestRule
            .onNodeWithTag(testTag = "Planets List")
            .assertExists(errorMessageOnFail = "Planets List Missing")
    }
}