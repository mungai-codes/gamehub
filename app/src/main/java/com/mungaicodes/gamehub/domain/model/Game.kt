package com.mungaicodes.gamehub.domain.model

import com.squareup.moshi.Json


data class Game(
    val id: Int,
    val slug: String,
    val name: String,
    @field:Json(name = "background_image")
    val backgroundImage: String?,
    val rating: Double,
    val playtime: Int,
    @field:Json(name = "esrb_rating")
    val esrbRating: Esrb,
    val platforms: List<Platform>
)
