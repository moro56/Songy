package it.emperor.songy.domain.music

import it.emperor.songy.data.network.api.MusicApi

class MusicRepository(private val apiClient: MusicApi) : IMusicRepository {
}