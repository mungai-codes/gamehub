package com.mungaicodes.gamehub.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mungaicodes.gamehub.domain.model.FavouriteGame

@Dao
interface GameDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addToFavourites(game: FavouriteGame)

    @Delete
    suspend fun removeFromFavourites(game: FavouriteGame)

    @Query("SELECT * FROM favourite_games")
    fun getFavouriteGames(): List<FavouriteGame>

    @Query("SELECT * FROM favourite_games WHERE name LIKE '%' || :searchQuery || '%' ")
    fun getFavouriteGamesByName(searchQuery: String): List<FavouriteGame>

    @Query("SELECT * FROM favourite_games WHERE slug = :slug")
    fun getFavouriteGameBySlug(slug: String): FavouriteGame

    @Query("SELECT EXISTS (SELECT 1 FROM favourite_games WHERE slug = :slug)")
    fun exists(slug: String): Boolean
}