package it.emperor.songy.ui.songList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import it.emperor.songy.data.models.Song
import it.emperor.songy.data.network.models.ApiResponse
import it.emperor.songy.data.network.models.wrapInApiResponse
import it.emperor.songy.domain.music.IMusicRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class SongListPageViewModel(private val musicRepository: IMusicRepository) : ViewModel() {

    private val songListStateFlow =
        MutableStateFlow<ApiResponse<List<Song>>>(ApiResponse.Loading)
    val songListState = songListStateFlow.asStateFlow()

    fun loadSongs() = viewModelScope.launch {
        val response = wrapInApiResponse { musicRepository.getSongList(60) }
        songListStateFlow.emit(response)
    }
}