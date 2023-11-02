package com.mungaicodes.gamehub.data.repo

import com.mungaicodes.gamehub.domain.model.DarkThemeConfig
import com.mungaicodes.gamehub.domain.model.ThemeBrand
import com.mungaicodes.gamehub.domain.model.UserData
import com.mungaicodes.gamehub.domain.repo.UserDataRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UserDataRepositoryImpl @Inject constructor(
    private val gameHubPreferencesDataSource: GameHubPreferencesDataSource
) : UserDataRepository {
    override val userData: Flow<UserData>
        get() = gameHubPreferencesDataSource.userData

    override suspend fun setThemeBrand(themeBrand: ThemeBrand) {
        gameHubPreferencesDataSource.setThemeBrand(themeBrand)
    }

    override suspend fun setDarkThemeConfig(darkThemeConfig: DarkThemeConfig) {
        gameHubPreferencesDataSource.setDarkThemeConfig(darkThemeConfig)
    }

    override suspend fun setDynamicColorPreference(useDynamicColor: Boolean) {
        gameHubPreferencesDataSource.setDynamicColorPreference(useDynamicColor)
    }
}