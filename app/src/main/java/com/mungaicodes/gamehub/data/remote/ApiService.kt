package com.mungaicodes.gamehub.data.remote

import com.mungaicodes.gamehub.domain.model.Game
import com.mungaicodes.gamehub.domain.model.GameDetails
import com.mungaicodes.gamehub.domain.model.ResponseSchema
import com.mungaicodes.gamehub.domain.model.Screenshot
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("games/lists/main")
    suspend fun getTrendingGames(
        @Query("key") apiKey: String = "b1213303647c4ba5b91c5194dd33a9d4",
        @Query("page_size") pageSize: Int = 15,
        @Query("ordering") ordering: String = "relevance"
    ): ResponseSchema<Game>

    @GET("games/lists/popular")
    suspend fun getPopularGames(
        @Query("key") apiKey: String = "b1213303647c4ba5b91c5194dd33a9d4",
        @Query("page_size") pageSize: Int = 15,
        @Query("ordering") ordering: String = "relevance"
    ): ResponseSchema<Game>

    @GET("games/{id}")
    suspend fun getGameDetails(
        @Path("id") gameId: String,
        @Query("key") apiKey: String = "b1213303647c4ba5b91c5194dd33a9d4"
    ): GameDetails

    @GET("games/{game_pk}/screenshots")
    suspend fun getGameScreenShots(
        @Path("game_pk") gameSlug: String,
        @Query("key") apiKey: String = "b1213303647c4ba5b91c5194dd33a9d4",
        @Query("page_size") pageSize: Int = 5,
        @Query("ordering") ordering: String = "relevance"
    ): ResponseSchema<Screenshot>
}

data class SomeObject(
    val nothing: String
)