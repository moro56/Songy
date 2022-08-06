package it.emperor.songy.domain.music

import it.emperor.songy.data.models.Song

interface IMusicRepository {

    suspend fun getSongList(limit: Int): List<Song>?
}