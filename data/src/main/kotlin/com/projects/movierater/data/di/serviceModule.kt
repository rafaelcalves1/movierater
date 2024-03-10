package com.projects.movierater.data.di

import com.projects.movierater.data.network.NetworkProvider
import com.projects.movierater.data.network.api.MovieApi
import org.koin.dsl.module

val serviceModule = module {

    factory { NetworkProvider.mainRetrofit.create(MovieApi::class.java) }
}