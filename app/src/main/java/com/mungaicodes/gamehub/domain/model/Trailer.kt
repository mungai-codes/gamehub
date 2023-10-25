package com.mungaicodes.gamehub.domain.model

data class Trailer(
    val id: Int,
    val name: String,
    val preview: String?,
    val data: TrailerData
)

data class TrailerData(
    val data: String
)

