package com.mungaicodes.gamehub.data.remote

import com.mungaicodes.gamehub.domain.model.Game
import com.mungaicodes.gamehub.domain.model.ResponseSchema
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("games/lists/main")
    suspend fun getTrendingGames(
        @Query("key") apiKey: String = "b1213303647c4ba5b91c5194dd33a9d4",
        @Query("page_size") pageSize: Int = 15,
        @Query("ordering") ordering: String = "relevance"
    ) : ResponseSchema<Game>
}