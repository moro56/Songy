package it.emperor.songy.ui.songlist.state

import it.emperor.songy.data.models.Song
import it.emperor.songy.data.network.models.ApiResponse

data class SongListPageState(
    val songList: ApiResponse<List<Song>>,
    val currentSongs: List<Song> = listOf(),
    val currentPage: Int = 1,
    val maxPages: Int = 3
) {
    val canShowMore: Boolean
        get() = currentPage < maxPages
}