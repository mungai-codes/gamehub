package com.mungaicodes.gamehub.di

import com.mungaicodes.gamehub.data.repo.LocalRepositoryImpl
import com.mungaicodes.gamehub.data.repo.NetworkRepositoryImpl
import com.mungaicodes.gamehub.data.repo.UserDataRepositoryImpl
import com.mungaicodes.gamehub.domain.repo.LocalRepository
import com.mungaicodes.gamehub.domain.repo.NetworkRepository
import com.mungaicodes.gamehub.domain.repo.UserDataRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Binds
    fun bindsNetworkRepository(
        networkRepositoryImpl: NetworkRepositoryImpl
    ): NetworkRepository

    @Binds
    fun bindsLocalRepository(
        localRepositoryImpl: LocalRepositoryImpl
    ): LocalRepository

    @Binds
    fun bindsUserDataRepository(
        userDataRepositoryImpl: UserDataRepositoryImpl
    ): UserDataRepository
}