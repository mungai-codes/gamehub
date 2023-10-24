package com.mungaicodes.gamehub.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.mungaicodes.gamehub.domain.model.FavouriteGame

@Database(entities = [FavouriteGame::class], version = 1)
abstract class GameHubDatabase : RoomDatabase() {
    abstract fun gameDao() : GameDao
}