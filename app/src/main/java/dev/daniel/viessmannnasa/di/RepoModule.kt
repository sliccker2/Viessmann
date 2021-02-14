package dev.daniel.viessmannnasa.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.daniel.viessmannnasa.data.repositories.DefaultNasaRepository
import dev.daniel.viessmannnasa.data.repositories.NasaRepository
import dev.daniel.viessmannnasa.network.ImagesApi

@Module
@InstallIn(SingletonComponent::class)
object RepoModule {

    @Provides
    fun provideNasaRepo(api: ImagesApi): NasaRepository = DefaultNasaRepository(api)
}