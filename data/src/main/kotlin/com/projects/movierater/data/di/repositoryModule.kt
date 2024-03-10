package com.projects.movierater.data.di

import com.projects.movierater.data.repository.MovieRepository
import kotlinx.coroutines.Dispatchers
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val repositoryModule = module {

    single {
        MovieRepository(get(), Dispatchers.IO)
    }

}