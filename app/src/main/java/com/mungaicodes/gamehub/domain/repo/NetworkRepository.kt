package com.mungaicodes.gamehub.domain.repo

import com.mungaicodes.gamehub.domain.model.Game
import com.mungaicodes.gamehub.domain.model.GameDetails
import com.mungaicodes.gamehub.domain.model.Screenshot
import com.mungaicodes.gamehub.util.Resource
import kotlinx.coroutines.flow.Flow

interface NetworkRepository {

    fun getTrendingGames(): Flow<Resource<List<Game>>>
    fun getPopularGames(): Flow<Resource<List<Game>>>
    fun getGameDetails(gameId: String): Flow<Resource<GameDetails>>
    fun getGameScreenShots(gameSlug: String): Flow<Resource<List<Screenshot>>>
}