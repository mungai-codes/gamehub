package com.mungaicodes.gamehub.data.local

import com.mungaicodes.gamehub.domain.model.FavouriteGame
import com.mungaicodes.gamehub.domain.model.GameDetails

fun GameDetails.toFavouriteGame(): FavouriteGame {
    return FavouriteGame(
        id = id,
        slug = slug,
        name = name,
        imageUrl = backgroundImage,
        rating = rating
    )
}