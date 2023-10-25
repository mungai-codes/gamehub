package com.mungaicodes.gamehub.domain.model

data class Achievement(
    val id: Int,
    val name: String,
    val description: String,
    val image: String?,
    val percent: String?
)
