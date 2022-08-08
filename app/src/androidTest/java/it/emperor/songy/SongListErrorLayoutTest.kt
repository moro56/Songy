package it.emperor.songy

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import it.emperor.songy.ui.songlist.views.SongListErrorLayout
import it.emperor.songy.ui.theme.SongyTheme
import org.junit.Rule
import org.junit.Test

class SongListErrorLayoutTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun test_error() {
        composeTestRule.setContent {
            SongyTheme {
                SongListErrorLayout("Error message") {}
            }
        }

        composeTestRule.onNodeWithText("Error message").assertIsDisplayed()
    }
}