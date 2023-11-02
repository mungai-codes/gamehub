package com.mungaicodes.gamehub.data.repo

import com.mungaicodes.gamehub.data.network.ApiService
import com.mungaicodes.gamehub.domain.model.Achievement
import com.mungaicodes.gamehub.domain.model.Creator
import com.mungaicodes.gamehub.domain.model.Game
import com.mungaicodes.gamehub.domain.model.GameDetails
import com.mungaicodes.gamehub.domain.model.Genre
import com.mungaicodes.gamehub.domain.model.Screenshot
import com.mungaicodes.gamehub.domain.repo.NetworkRepository
import com.mungaicodes.gamehub.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class NetworkRepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : NetworkRepository {

    override fun getTrendingGames(): Flow<Resource<List<Game>>> {
        return flow {
            emit(Resource.Loading("Loading..."))
            try {
                val response = apiService.getTrendingGames()
                emit(Resource.Success(response.results))
            } catch (throwable: Throwable) {
                when (throwable) {
                    is IOException -> emit(Resource.Error("Internet connection not available!"))
                    is HttpException -> emit(Resource.Error("There is a problem with the server!"))
                    else -> emit(
                        Resource.Error(
                            throwable.message ?: "An unexpected error occurred"
                        )
                    )
                }
            }
        }
    }

    override fun getPopularGames(): Flow<Resource<List<Game>>> {
        return flow {
            emit(Resource.Loading("Loading..."))
            try {
                val response = apiService.getPopularGames()
                emit(Resource.Success(response.results))
            } catch (throwable: Throwable) {
                when (throwable) {
                    is IOException -> emit(Resource.Error("Internet connection not available!"))
                    is HttpException -> emit(Resource.Error("There is a problem with the server!"))
                    else -> emit(
                        Resource.Error(
                            throwable.message ?: "An unexpected error occurred"
                        )
                    )
                }
            }
        }
    }

    override fun searchForGameByQuery(searchQuery: String): Flow<Resource<List<Game>>> {
        return flow {
            emit(Resource.Loading("Loading results..."))
            try {
                val response = apiService.searchForGameByQuery(searchQuery = searchQuery)
                emit(Resource.Success(response.results))

            } catch (throwable: Throwable) {
                when (throwable) {
                    is IOException -> emit(Resource.Error("Internet connection not available!"))
                    is HttpException -> emit(Resource.Error("There is a problem with the server!"))
                    else -> emit(
                        Resource.Error(
                            throwable.message ?: "An unexpected error occurred"
                        )
                    )
                }
            }
        }
    }

    override fun getRelatedGames(gameSlug: String): Flow<Resource<List<Game>>> {
        return flow {
            emit(Resource.Loading("Loading related games..."))
            try {
                val response = apiService.getRelatedGames(gameSlug)
                emit(Resource.Success(response.results))

            } catch (throwable: Throwable) {
                when (throwable) {
                    is IOException -> emit(Resource.Error("Internet connection not available!"))
                    is HttpException -> emit(Resource.Error("There is a problem with the server!"))
                    else -> emit(
                        Resource.Error(
                            throwable.message ?: "An unexpected error occurred"
                        )
                    )
                }
            }
        }
    }

    override fun getAdditions(gameSlug: String): Flow<Resource<List<Game>>> {
        return flow {
            emit(Resource.Loading("Loading related games..."))
            try {
                val response = apiService.getGameAdditions(gameSlug)
                emit(Resource.Success(response.results))

            } catch (throwable: Throwable) {
                when (throwable) {
                    is IOException -> emit(Resource.Error("Internet connection not available!"))
                    is HttpException -> emit(Resource.Error("There is a problem with the server!"))
                    else -> emit(
                        Resource.Error(
                            throwable.message ?: "An unexpected error occurred"
                        )
                    )
                }
            }
        }
    }

    override fun getGameDetails(gameId: String): Flow<Resource<GameDetails>> {
        return flow {
            emit(Resource.Loading("Loading game details..."))
            try {
                val response = apiService.getGameDetails(gameId)
                emit(Resource.Success(response))

            } catch (throwable: Throwable) {
                when (throwable) {
                    is IOException -> emit(Resource.Error("Internet connection not available!"))
                    is HttpException -> emit(Resource.Error("There is a problem with the server!"))
                    else -> emit(
                        Resource.Error(
                            throwable.message ?: "An unexpected error occurred"
                        )
                    )
                }
            }
        }
    }

    override fun getGameScreenShots(gameSlug: String): Flow<Resource<List<Screenshot>>> {
        return flow {
            emit(Resource.Loading("Loading..."))
            try {
                val response = apiService.getGameScreenShots(gameSlug)
                emit(Resource.Success(response.results))

            } catch (throwable: Throwable) {
                when (throwable) {
                    is IOException -> emit(Resource.Error("Internet connection not available!"))
                    is HttpException -> emit(Resource.Error("There is a problem with the server!"))
                    else -> emit(
                        Resource.Error(
                            throwable.message ?: "An unexpected error occurred"
                        )
                    )
                }
            }
        }
    }

    override fun getGameAchievements(gameSlug: String): Flow<Resource<List<Achievement>>> {
        return flow {
            emit(Resource.Loading("Loading achievements..."))
            try {
                val response = apiService.getGameAchievements(gameSlug)
                emit(Resource.Success(response.results))
            } catch (throwable: Throwable) {
                when (throwable) {
                    is IOException -> emit(Resource.Error("Internet connection not available!"))
                    is HttpException -> emit(Resource.Error("There is a problem with the server!"))
                    else -> emit(
                        Resource.Error(
                            throwable.message ?: "An unexpected error occurred"
                        )
                    )
                }
            }
        }
    }

    override fun getDevelopmentTeam(gameSlug: String): Flow<Resource<List<Creator>>> {
        return flow {
            emit(Resource.Loading("Loading creators..."))
            try {
                val response = apiService.getDevelopmentTeam(gameSlug)
                emit(Resource.Success(response.results))
            } catch (throwable: Throwable) {
                when (throwable) {
                    is IOException -> emit(Resource.Error("Internet connection not available!"))
                    is HttpException -> emit(Resource.Error("There is a problem with the server!"))
                    else -> emit(
                        Resource.Error(
                            throwable.message ?: "An unexpected error occurred"
                        )
                    )
                }
            }
        }
    }

    override fun getGenres(): Flow<Resource<List<Genre>>> {
        return flow {
            emit(Resource.Loading("Loading genres..."))
            try {
                val response = apiService.getGenres()
                emit(Resource.Success(response.results))
            } catch (throwable: Throwable) {
                when (throwable) {
                    is IOException -> emit(Resource.Error("Internet connection not available!"))
                    is HttpException -> emit(Resource.Error("There is a problem with the server!"))
                    else -> emit(
                        Resource.Error(
                            throwable.message ?: "An unexpected error occurred"
                        )
                    )
                }
            }
        }
    }

    override fun getGenreDetails(genreId: Int): Flow<Resource<Genre>> {
        return flow {
            emit(Resource.Loading("Loading genre details..."))
            try {
                val response = apiService.getGenreDetails(genreId)
                emit(Resource.Success(response))
            } catch (throwable: Throwable) {
                when (throwable) {
                    is IOException -> emit(Resource.Error("Internet connection not available!"))
                    is HttpException -> emit(Resource.Error("There is a problem with the server!"))
                    else -> emit(
                        Resource.Error(
                            throwable.message ?: "An unexpected error occurred"
                        )
                    )
                }
            }
        }
    }

    override fun getGamesByGenre(genre: String): Flow<Resource<List<Game>>> {
        return flow {
            emit(Resource.Loading("Loading games..."))
            try {
                val response = apiService.getGamesByGenre(genre = genre)
                emit(Resource.Success(response.results))
            } catch (throwable: Throwable) {
                when (throwable) {
                    is IOException -> emit(Resource.Error("Internet connection not available!"))
                    is HttpException -> emit(Resource.Error("There is a problem with the server!"))
                    else -> emit(
                        Resource.Error(
                            throwable.message ?: "An unexpected error occurred"
                        )
                    )
                }
            }
        }
    }
}