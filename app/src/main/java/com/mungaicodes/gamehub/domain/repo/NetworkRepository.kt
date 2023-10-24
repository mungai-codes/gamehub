package com.mungaicodes.gamehub.domain.repo

import com.mungaicodes.gamehub.domain.model.Game
import com.mungaicodes.gamehub.util.Resource
import kotlinx.coroutines.flow.Flow

interface NetworkRepository {

    fun getTrendingGames() : Flow<Resource<List<Game>>>
}