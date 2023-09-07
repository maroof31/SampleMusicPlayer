package com.dawinder.musicplayer_jetpackcompose.di

import com.app.composemusicplayer.repositories.Repository

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class DataModule {


    @Provides
    @Singleton
    fun provideRepository(): Repository {
        return Repository()
    }


}