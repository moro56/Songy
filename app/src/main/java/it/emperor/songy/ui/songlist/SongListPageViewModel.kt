package it.emperor.songy.ui.songlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import it.emperor.songy.data.network.models.ApiResponse
import it.emperor.songy.data.network.models.wrapInApiResponse
import it.emperor.songy.domain.music.IMusicRepository
import it.emperor.songy.ui.songlist.state.SongListPageState
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.ensureActive
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class SongListPageViewModel(private val musicRepository: IMusicRepository) : ViewModel() {

    private val uiStateFlow = MutableStateFlow(SongListPageState(ApiResponse.Loading))
    val uiState = uiStateFlow.asStateFlow()

    private var showMoreJob: Job? = null

    fun loadSongs() = viewModelScope.launch {
        if (uiStateFlow.value.songList is ApiResponse.Error) {
            uiStateFlow.emit(uiStateFlow.value.copy(songList = ApiResponse.Loading))
        }
        val response = wrapInApiResponse { musicRepository.getSongList(60) }
        if (response is ApiResponse.Success) {
            val currentPage = uiStateFlow.value.currentPage
            uiStateFlow.emit(
                uiStateFlow.value.copy(
                    songList = response,
                    currentSongs = response.value.take(currentPage * 20)
                )
            )
        } else {
            uiStateFlow.emit(uiStateFlow.value.copy(songList = response, currentPage = 1))
        }
    }

    fun showMoreSongs() {
        if (showMoreJob != null) return
        showMoreJob = viewModelScope.launch {
            // Fake delay
            delay(1000)
            ensureActive()
            var currentPage = uiStateFlow.value.currentPage
            val songList = uiStateFlow.value.songList
            if (uiStateFlow.value.canShowMore && songList is ApiResponse.Success) {
                currentPage++
                uiStateFlow.emit(
                    uiStateFlow.value.copy(
                        currentSongs = songList.value.take(currentPage * 20),
                        currentPage = currentPage
                    )
                )
            }
            showMoreJob = null
        }
    }

    override fun onCleared() {
        super.onCleared()
        showMoreJob?.cancel()
        showMoreJob = null
    }
}