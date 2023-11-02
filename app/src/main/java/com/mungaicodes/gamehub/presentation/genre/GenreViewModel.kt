package com.mungaicodes.gamehub.presentation.genre

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mungaicodes.gamehub.domain.repo.NetworkRepository
import com.mungaicodes.gamehub.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
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
class GenreViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val networkRepository: NetworkRepository
) : ViewModel() {

    private val _state = MutableStateFlow(GenreScreenUiState())
    val state = _state.asStateFlow()

    private val _eventFlow = MutableSharedFlow<GenreScreenUiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    init {
        savedStateHandle.get<Int>("genreId")?.let { genreId ->
            _state.update { it.copy(genreId = genreId) }
            getGenreDetails(genreId)
        }
        savedStateHandle.get<String>("genre")?.let { genre ->
            _state.update { it.copy(currentGenre = genre) }
            getGames(genre)
        }
    }

    fun onEvent(event: GenreScreenEvent) {
        when (event) {
            is GenreScreenEvent.OnGameClick -> {
                viewModelScope.launch {
                    _eventFlow.emit(GenreScreenUiEvent.NavigateToDetails(event.gameId))
                }
            }

            GenreScreenEvent.OnRetryClick -> {
                getGenreDetails(_state.value.genreId)
                getGames(_state.value.currentGenre)
            }
        }
    }

    fun getGames(genre: String) {
        viewModelScope.launch {
            networkRepository.getGamesByGenre(genre)
                .onEach { result ->
                    when (result) {
                        is Resource.Error -> {
                            _state.update {
                                it.copy(
                                    loadingGames = false,
                                    gameError = result.message
                                )
                            }
                        }

                        is Resource.Loading -> {
                            _state.update { it.copy(loadingGames = true, gameError = null) }
                        }

                        is Resource.Success -> {
                            if (result.data?.isNotEmpty() == true) {
                                _state.update {
                                    it.copy(
                                        loadingGames = false,
                                        games = result.data
                                    )
                                }
                            } else {
                                _state.update {
                                    it.copy(
                                        loadingGames = false,
                                        games = emptyList(),
                                        gameError = "Results for ${_state.value.currentGenre} not found"
                                    )
                                }
                            }
                        }
                    }
                }.launchIn(this)
        }
    }

    fun getGenreDetails(genreId: Int) {
        viewModelScope.launch {
            networkRepository.getGenreDetails(genreId).onEach { result ->
                when (result) {
                    is Resource.Error -> {
                        _state.update { it.copy(loadingGenreDetails = false, genreLoadError = result.message) }
                    }

                    is Resource.Loading -> {
                        _state.update { it.copy(loadingGenreDetails = true, genreLoadError = null) }
                    }

                    is Resource.Success -> {
                        _state.update { it.copy(loadingGenreDetails = false, genre = result.data) }
                    }
                }
            }.launchIn(this)
        }
    }
}