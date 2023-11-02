package com.mungaicodes.gamehub.di

import android.content.Context
import androidx.room.Room
import com.mungaicodes.gamehub.data.local.GameHubDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    @Singleton
    fun providesGameHubDatabase(@ApplicationContext context: Context): GameHubDatabase {
        return Room.databaseBuilder(
            context,
            GameHubDatabase::class.java,
            "gamehub_database"
        ).build()
    }
}