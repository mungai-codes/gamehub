package com.mungaicodes.gamehub.domain.model

import com.squareup.moshi.Json

data class GameDetails(
    val id: Int,
    val slug: String,
    val name: String,
    @field:Json(name = "name_original")
    val nameOriginal: String,
    val description: String,
    val metacritic: Int?,
    val released: String?,
    @field:Json(name = "background_image")
    val backgroundImage: String?,
    @field:Json(name = "background_image_additional")
    val additionalBackgroundImage: String?,
    val website: String,
    val rating: Double,
    val playtime: Int,
    @field:Json(name = "reddit_url")
    val redditUrl: String,
    @field:Json(name = "reddit_name")
    val redditName: String,
    @field:Json(name = "reddit_description")
    val redditDescription: String,
    @field:Json(name = "reddit_logo")
    val redditLogo: String,
    @field:Json(name = "metacritic_url")
    val metacriticUrl: String,
    @field:Json(name = "esrb_rating")
    val esrbRating: Esrb?,
    val platforms: List<Platform>,
    @field:Json(name = "alternative_names")
    val alternativeNames: List<String>,
    @field:Json(name = "suggestions_count")
    val suggestionsCount: Int,
    @field:Json(name = "ratings_count")
    val ratingsCount: Int
)
