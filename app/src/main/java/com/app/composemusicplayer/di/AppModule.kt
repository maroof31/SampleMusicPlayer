package com.app.composemusicplayer.di

import android.app.Application
import android.content.Context
import androidx.media3.exoplayer.ExoPlayer
import com.app.composemusicplayer.player.MyPlayer
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class AppModule {


    @Provides
    @Singleton
    fun provideContext(application: Application): Context {
        return application.applicationContext
    }


    @Provides
    @Singleton
    fun provideExoPLayer(context: Context): ExoPlayer {
        return ExoPlayer.Builder(context).build()
    }

    @Provides
    @Singleton
    fun provideMyPlayer(player: ExoPlayer): MyPlayer {
        return MyPlayer(player)
    }


}