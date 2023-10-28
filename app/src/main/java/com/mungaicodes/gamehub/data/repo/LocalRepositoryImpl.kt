package com.mungaicodes.gamehub.data.repo

import com.mungaicodes.gamehub.data.local.GameHubDatabase
import com.mungaicodes.gamehub.data.local.toFavouriteGame
import com.mungaicodes.gamehub.domain.model.FavouriteGame
import com.mungaicodes.gamehub.domain.model.GameDetails
import com.mungaicodes.gamehub.domain.repo.LocalRepository
import com.mungaicodes.gamehub.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject

class LocalRepositoryImpl @Inject constructor(
    database: GameHubDatabase
) : LocalRepository {

    private val dao = database.gameDao()
    override fun getAllFavouriteGames(): Flow<Resource<List<FavouriteGame>>> {
        return flow {
            emit(Resource.Loading("Loading favourites..."))
            try {
                val response = dao.getFavouriteGames()
                emit(Resource.Success(response))
            } catch (throwable: Throwable) {
                when (throwable) {
                    is IOException -> emit(
                        Resource.Error(
                            throwable.message ?: "Could not load data."
                        )
                    )

                    else -> emit(
                        Resource.Error(
                            throwable.message ?: "An unexpected error occurred"
                        )
                    )
                }
            }
        }
    }

    override suspend fun addGameToFavourites(gameDetails: GameDetails) {
        dao.addToFavourites(gameDetails.toFavouriteGame())
    }

    override suspend fun checkIfGameIsFavourite(gameId: String): Boolean {
        return dao.exists(gameId)
    }

    override suspend fun removeGameFromFavourites(gameId: String) {
        return dao.removeFromFavourites(dao.getFavouriteGameBySlug(gameId))
    }
}
