package it.emperor.songy.data.network.api

import it.emperor.songy.data.models.SongsFeedResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface MusicApi {

    @GET("api/v2/us/music/most-played/{limit}/songs.json")
    suspend fun getSongList(@Path("limit") limit: Int): Response<SongsFeedResponse>
}