package it.emperor.songy

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import it.emperor.songy.data.models.Song
import it.emperor.songy.ui.songlist.views.SongListSuccessLayout
import it.emperor.songy.ui.songlist.views.SongView
import it.emperor.songy.ui.theme.SongyTheme
import org.junit.Rule
import org.junit.Test

class SongListSuccessLayoutTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun test_success() {
        composeTestRule.setContent {
            SongyTheme {
                SongListSuccessLayout(listOf(song, song, song), true, {}, {})
            }
        }

        composeTestRule.onRoot().onChild().onChildren().assertCountEquals(4)
        composeTestRule.onNodeWithTag("loadingMore").assertIsDisplayed()
    }

    @Test
    fun test_song() {
        composeTestRule.setContent {
            SongyTheme {
                SongView(song) {}
            }
        }

        composeTestRule.onNodeWithText("artistName").assertIsDisplayed()
        composeTestRule.onNodeWithText("name").assertIsDisplayed()
        composeTestRule.onNodeWithText("release").assertIsDisplayed()
    }

    /** MOCKS */

    private val song = Song(
        "artistName",
        "name",
        "release",
        "https://is1-ssl.mzstatic.com/image/thumb/Music112/v4/4d/54/ec/4d54eccd-8e71-4217-cb81-445adc77136b/075679750006.jpg/100x100bb.jpg",
        "https://music.apple.com/us/album/free-dem-5s/1617170958?i=1617170971"
    )
}