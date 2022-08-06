package it.emperor.songy.ui.songList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import it.emperor.songy.data.network.models.ApiResponse
import it.emperor.songy.data.network.models.wrapInApiResponse
import it.emperor.songy.domain.music.IMusicRepository
import it.emperor.songy.ui.songList.state.SongListPageState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class SongListPageViewModel(private val musicRepository: IMusicRepository) : ViewModel() {

    private val uiStateFlow = MutableStateFlow(SongListPageState(ApiResponse.Loading))
    val uiState = uiStateFlow.asStateFlow()

    fun loadSongs() = viewModelScope.launch {
        if (uiStateFlow.value.songList is ApiResponse.Error) {
            uiStateFlow.emit(uiStateFlow.value.copy(songList = ApiResponse.Loading))
        }
        val response = wrapInApiResponse { musicRepository.getSongList(60) }
        uiStateFlow.emit(uiStateFlow.value.copy(songList = response))
    }
}