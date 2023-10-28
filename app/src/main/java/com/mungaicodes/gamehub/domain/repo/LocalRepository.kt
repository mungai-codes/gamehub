package com.mungaicodes.gamehub.domain.repo

import com.mungaicodes.gamehub.domain.model.FavouriteGame
import com.mungaicodes.gamehub.domain.model.GameDetails
import com.mungaicodes.gamehub.util.Resource
import kotlinx.coroutines.flow.Flow

interface LocalRepository {
    fun getAllFavouriteGames(): Flow<Resource<List<FavouriteGame>>>
    suspend fun addGameToFavourites(gameDetails: GameDetails)
    suspend fun checkIfGameIsFavourite(gameId: String): Boolean
    suspend fun removeGameFromFavourites(gameId: String)
}