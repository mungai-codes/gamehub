package com.mungaicodes.gamehub.domain.model

import com.squareup.moshi.Json

data class Creator(
    val id: Int,
    val name: String,
    val slug: String,
    val image: String?,
    @field:Json(name = "image_background")
    val imageBackground: String?,
    @field:Json(name = "games_count")
    val gamesCount: String?
)
