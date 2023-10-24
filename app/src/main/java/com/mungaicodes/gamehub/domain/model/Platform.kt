package com.mungaicodes.gamehub.domain.model

data class Platform(
    val platform: PlatformData
)

data class PlatformData(
    val id: Int,
    val slug: String?,
    val name: String?
)
