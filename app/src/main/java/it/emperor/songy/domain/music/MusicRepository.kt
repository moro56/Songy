package it.emperor.songy.domain.music

import it.emperor.songy.data.models.Song
import it.emperor.songy.data.network.api.MusicApi

class MusicRepository(private val apiClient: MusicApi) : IMusicRepository {

    override suspend fun getSongList(limit: Int): List<Song>? {
        val response = apiClient.getSongList(limit)
        return if (response.isSuccessful) response.body()?.feed?.results else null
    }
}