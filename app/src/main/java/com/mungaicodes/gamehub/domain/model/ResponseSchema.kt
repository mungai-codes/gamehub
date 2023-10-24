package com.mungaicodes.gamehub.domain.model

data class ResponseSchema<T>(
    val count: Int,
    val results: List<T>
)
