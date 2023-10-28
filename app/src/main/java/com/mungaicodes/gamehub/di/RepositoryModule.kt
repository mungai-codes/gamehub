package com.mungaicodes.gamehub.di

import com.mungaicodes.gamehub.data.repo.LocalRepositoryImpl
import com.mungaicodes.gamehub.data.repo.NetworkRepositoryImpl
import com.mungaicodes.gamehub.domain.repo.LocalRepository
import com.mungaicodes.gamehub.domain.repo.NetworkRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindsNetworkRepository(
        networkRepositoryImpl: NetworkRepositoryImpl
    ): NetworkRepository

    @Binds
    @Singleton
    abstract fun bindsLocalRepository(
        localRepositoryImpl: LocalRepositoryImpl
    ): LocalRepository
}