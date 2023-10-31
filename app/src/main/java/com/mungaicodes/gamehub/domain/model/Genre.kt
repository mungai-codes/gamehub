package com.mungaicodes.gamehub.domain.model

import com.squareup.moshi.Json

data class Genre(
    val id: Int,
    val name: String,
    val slug: String,
    @field:Json(name = "image_background")
    val imageBackground: String?,
    val description: String?
)
