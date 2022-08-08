package it.emperor.songy

import androidx.compose.ui.res.stringResource
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import it.emperor.songy.data.models.Song
import it.emperor.songy.data.network.models.ApiResponse
import it.emperor.songy.ui.songlist.SongListLayout
import it.emperor.songy.ui.songlist.state.SongListPageState
import it.emperor.songy.ui.theme.SongyTheme
import org.junit.Rule
import org.junit.Test

class SongListLayoutTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun test_loading() {
        var title = ""
        composeTestRule.setContent {
            title = stringResource(id = R.string.song_list_title_txt)
            SongyTheme {
                SongListLayout(SongListPageState(ApiResponse.Loading), {}, {}, {})
            }
        }

        composeTestRule.onNodeWithText(title).assertIsDisplayed()
        composeTestRule.onNodeWithTag("loading").assertIsDisplayed()
    }

    @Test
    fun test_error() {
        var title = ""
        var error = ""
        composeTestRule.setContent {
            title = stringResource(id = R.string.song_list_title_txt)
            error = stringResource(id = R.string.song_list_error_message_txt)
            SongyTheme {
                SongListLayout(SongListPageState(ApiResponse.Error(null)), {}, {}, {})
            }
        }

        composeTestRule.onNodeWithText(title).assertIsDisplayed()
        composeTestRule.onNodeWithText(error).assertIsDisplayed()
    }

    @Test
    fun test_success() {
        var title = ""
        composeTestRule.setContent {
            title = stringResource(id = R.string.song_list_title_txt)
            SongyTheme {
                SongListLayout(
                    SongListPageState(
                        ApiResponse.Success(listOf(song, song, song)),
                        currentSongs = listOf(song, song, song),
                        currentPage = 1,
                        maxPages = 3
                    ), {}, {}, {})
            }
        }

        composeTestRule.onNodeWithText(title).assertIsDisplayed()
        composeTestRule.onRoot().onChildAt(1).onChildren().assertCountEquals(4)
        composeTestRule.onNodeWithTag("loadingMore").assertIsDisplayed()
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