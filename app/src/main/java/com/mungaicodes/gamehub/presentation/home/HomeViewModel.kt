package com.mungaicodes.gamehub.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mungaicodes.gamehub.domain.repo.LocalRepository
import com.mungaicodes.gamehub.domain.repo.NetworkRepository
import com.mungaicodes.gamehub.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val networkRepository: NetworkRepository,
    private val localRepository: LocalRepository
) : ViewModel() {

    private val _state = MutableStateFlow(HomeScreenUiState())
    val state = _state.asStateFlow()

    private val _eventFlow = MutableSharedFlow<HomeScreenUiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    init {
        getTrendingGames()
        getPopularGames()
        getFavouriteGames()
    }

    fun onEvent(event: HomeScreenEvent) {
        when (event) {
            is HomeScreenEvent.OnGameClick -> {
                viewModelScope.launch {
                    _eventFlow.emit(HomeScreenUiEvent.NavigateToDetails(event.gameId))
                }
            }

            HomeScreenEvent.RetryPopularLoad -> {
                getPopularGames()
            }
            HomeScreenEvent.RetryTrendingLoad -> {
                getTrendingGames()
            }
        }
    }

    fun getTrendingGames() {
        viewModelScope.launch {
            networkRepository.getTrendingGames().onEach { result ->
                when (result) {
                    is Resource.Error -> {
                        _state.update {
                            it.copy(
                                loadingTrendingGames = false,
                                trendingGamesError = result.message
                            )
                        }
                    }

                    is Resource.Loading -> {
                        _state.update { it.copy(loadingTrendingGames = true, trendingGamesError = null) }
                    }

                    is Resource.Success -> {
                        _state.update {
                            it.copy(
                                loadingTrendingGames = false,
                                trendingGames = result.data ?: emptyList()
                            )
                        }
                    }
                }
            }.launchIn(this)
        }
    }

    fun getPopularGames() {
        viewModelScope.launch {
            networkRepository.getPopularGames().onEach { result ->
                when (result) {
                    is Resource.Error -> {
                        _state.update {
                            it.copy(
                                loadingPopularGames = false,
                                popularGamesError = result.message
                            )
                        }
                    }

                    is Resource.Loading -> {
                        _state.update { it.copy(loadingPopularGames = true, popularGamesError = null) }
                    }

                    is Resource.Success -> {
                        _state.update {
                            it.copy(
                                loadingPopularGames = false,
                                popularGames = result.data ?: emptyList()
                            )
                        }
                    }
                }
            }.launchIn(this)
        }
    }

    fun getFavouriteGames() {
        viewModelScope.launch(Dispatchers.IO) {
            localRepository.getAllFavouriteGames().onEach { result ->
                when(result) {
                    is Resource.Error -> {
                        _state.update { it.copy(loadingFavouriteGames = false, favouriteGamesError = result.message) }
                    }
                    is Resource.Loading -> {
                        _state.update { it.copy(loadingFavouriteGames = true, favouriteGamesError = null) }
                    }
                    is Resource.Success -> {
                        _state.update { it.copy(loadingFavouriteGames = false, favouriteGames = result.data ?: emptyList()) }

                    }
                }
            }.launchIn(this)
        }
    }

}