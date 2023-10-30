package com.mungaicodes.gamehub.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favourite_games")
data class FavouriteGame(
    @PrimaryKey
    val id: Int,
    val slug: String,
    val name: String,
    val imageUrl: String,
    val rating: Double
)