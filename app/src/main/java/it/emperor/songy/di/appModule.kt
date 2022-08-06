package it.emperor.songy.di

import it.emperor.songy.data.network.ApiClient
import org.koin.dsl.module

val appModule = module {
    factory { ApiClient() }
}