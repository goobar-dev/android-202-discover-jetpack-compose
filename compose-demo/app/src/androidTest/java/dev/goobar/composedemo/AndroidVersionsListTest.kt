package dev.goobar.composedemo

import androidx.compose.ui.test.assert
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onChildAt
import androidx.compose.ui.test.onChildren
import androidx.compose.ui.test.onFirst
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onRoot
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.printToLog
import androidx.test.ext.junit.runners.AndroidJUnit4
import dev.goobar.composedemo.data.AndroidVersionInfo
import dev.goobar.composedemo.data.AndroidVersionsRepository
import dev.goobar.composedemo.ui.config.ScreenConfiguration
import dev.goobar.composedemo.versionslist.AndroidVersionsListScreen
import dev.goobar.composedemo.versionslist.AndroidVersionsListViewModel
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class AndroidVersionsListTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun versionsListDisplayedOnHomeScreenLoad() {
        composeTestRule.setContent {
            AndroidVersionsListScreen(ScreenConfiguration(), onClick = {})
        }

        // verify that the MainScreenPhoneContent composable includes
        // an element with testTag == "Versions List"
        composeTestRule
            .onNodeWithTag(testTag = "Versions List")
            .assertExists(errorMessageOnFail = "Versions List Missing")
    }

    @Test
    fun versionsListDisplaysFirstVersionInfo() {
        val testData = getTestData()
        composeTestRule.setContent {
            AndroidVersionsListScreen(ScreenConfiguration(),onClick = {})
        }

        // find the list
        // get the first child of the list
        // verify that the child includes the expected title text
        composeTestRule
            .onNodeWithTag(testTag = "Versions List")
            .assertExists(errorMessageOnFail = "Versions List Missing")
            .onChildren()
            .onFirst()
            .assert(hasText(testData.versionsList.first().title))
    }

    @Test
    fun printTreeToLog() {
        composeTestRule.setContent {
            AndroidVersionsListScreen(ScreenConfiguration(),onClick = {})
        }

        // print the semantics tree to logcat to examine it
        // add custom testTag to individual list items to make them easier to identify
        // print a third time using unmerged tree
        composeTestRule.onRoot(useUnmergedTree = true).printToLog("Semantics")
    }

    @Test
    fun versionInfoClickHandlerCalledWhenCardIsClicked() {
        val testData = getTestData()
        var selectedItem: AndroidVersionInfo? = null

        composeTestRule.setContent {
            AndroidVersionsListScreen(ScreenConfiguration()) { clickedInfo ->
                selectedItem = clickedInfo
            }
        }

        // verify that the selected item is correctly updated when the first card is clicked
        composeTestRule
            .onNodeWithTag("Versions List")
            .onChildAt(0)
            .performClick()

        assert(selectedItem == testData.versionsList.first().info)
    }

}

private fun getTestData() = AndroidVersionsListViewModel.State(
    versionsList = AndroidVersionsRepository.data.map {
        AndroidVersionsListViewModel.State.AndroidVersionViewItem(
            it.publicName,
            it.codename,
            it.details,
            it
        )
    }
)