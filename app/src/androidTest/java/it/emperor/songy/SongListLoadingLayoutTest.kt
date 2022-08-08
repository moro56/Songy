package it.emperor.songy

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import it.emperor.songy.ui.songlist.views.SongListLoadingLayout
import it.emperor.songy.ui.theme.SongyTheme
import org.junit.Rule
import org.junit.Test

class SongListLoadingLayoutTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun test_loading() {
        composeTestRule.setContent {
            SongyTheme {
                SongListLoadingLayout()
            }
        }

        composeTestRule.onNodeWithTag("loading").assertIsDisplayed()
    }
}