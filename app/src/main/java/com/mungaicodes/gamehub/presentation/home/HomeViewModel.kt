package com.mungaicodes.gamehub.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mungaicodes.gamehub.domain.repo.NetworkRepository
import com.mungaicodes.gamehub.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val networkRepository: NetworkRepository
) : ViewModel() {

    private val _state = MutableStateFlow(HomeUiState())
    val state = _state.asStateFlow()

    init {
        getTrendingGames()
        getPopularGames()
    }

    fun onEvent(event: HomeScreenEvent) {
        when (event) {
            HomeScreenEvent.PopularGameClicked -> {

            }

            HomeScreenEvent.ShowMorePopularGames -> {

            }

            HomeScreenEvent.TrendingGameClicked -> {
                getGameScreenShots()
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
                                error = result.message
                            )
                        }
                    }

                    is Resource.Loading -> {
                        _state.update { it.copy(loadingTrendingGames = true) }
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
                                error = result.message
                            )
                        }
                    }

                    is Resource.Loading -> {
                        _state.update { it.copy(loadingPopularGames = true) }
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

    fun getGameScreenShots() {
        viewModelScope.launch {
            networkRepository.getGameScreenShots("portal-2").onEach { result ->
                when (result) {
                    is Resource.Error -> {
                        _state.update {
                            it.copy(
                                loadingTrendingGames = false,
                                error = result.message
                            )
                        }
                    }

                    is Resource.Loading -> {
                        _state.update { it.copy(loadingTrendingGames = true) }
                    }

                    is Resource.Success -> {
                        _state.update { it.copy(loadingTrendingGames = false) }
                    }
                }
            }.launchIn(this)
        }
    }

}