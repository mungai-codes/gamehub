package com.mungaicodes.gamehub.domain.repo

import com.mungaicodes.gamehub.domain.model.DarkThemeConfig
import com.mungaicodes.gamehub.domain.model.ThemeBrand
import com.mungaicodes.gamehub.domain.model.UserData
import kotlinx.coroutines.flow.Flow

interface UserDataRepository {

    val userData: Flow<UserData>
    suspend fun setThemeBrand(themeBrand: ThemeBrand)
    suspend fun setDarkThemeConfig(darkThemeConfig: DarkThemeConfig)
    suspend fun setDynamicColorPreference(useDynamicColor: Boolean)
}