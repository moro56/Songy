package it.emperor.songy.di

import it.emperor.songy.data.network.ApiClient
import it.emperor.songy.data.network.api.MusicApi
import it.emperor.songy.domain.music.IMusicRepository
import it.emperor.songy.domain.music.MusicRepository
import org.koin.dsl.module

val appModule = module {
    single { ApiClient() }
    factory { get<ApiClient>().createApiService(MusicApi::class.java) }
    single<IMusicRepository> { MusicRepository(get()) }
}