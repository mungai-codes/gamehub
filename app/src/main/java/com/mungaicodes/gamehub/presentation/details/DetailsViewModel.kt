package com.mungaicodes.gamehub.presentation.details

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mungaicodes.gamehub.domain.model.GameDetails
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
class DetailsViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val networkRepository: NetworkRepository,
    private val localRepository: LocalRepository
) : ViewModel() {

    private val _state = MutableStateFlow(DetailsScreenUiState())
    val state = _state.asStateFlow()

    private val _eventFlow = MutableSharedFlow<DetailsScreenUiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    init {
        savedStateHandle.get<String>("gameId")?.let { gameId ->
            _state.update { it.copy(gameId = gameId) }
            getGameDetails(gameId)
            getGameScreenShots(gameId)
            getGameAchievements(gameId)
            getRelatedGames(gameId)
            getAdditions(gameId)
            getDevelopmentTeam(gameId)
        }
    }

    private fun reLoadData() {
        _state.update { it.copy(error = null) }
        getGameDetails(_state.value.gameId!!)
        getGameScreenShots(_state.value.gameId!!)
        getGameAchievements(_state.value.gameId!!)
        getRelatedGames(_state.value.gameId!!)
        getAdditions(_state.value.gameId!!)
        getDevelopmentTeam(_state.value.gameId!!)
    }

    fun onEvent(event: DetailsScreenEvent) {
        when (event) {
            is DetailsScreenEvent.AddGameToFavourites -> {
                addGameToFavourites(event.game)
            }

            DetailsScreenEvent.GoBack -> {
                viewModelScope.launch {
                    _eventFlow.emit(DetailsScreenUiEvent.NavigateBack)
                }
            }

            is DetailsScreenEvent.OnGameClick -> {
                viewModelScope.launch {
                    _eventFlow.emit(DetailsScreenUiEvent.NavigateToDetails(event.gameId))
                }
            }

            DetailsScreenEvent.OnRetryLoad -> {
                reLoadData()
            }

            is DetailsScreenEvent.RemoveGameFromFavourites -> {
                removeGameFromFavourites()
            }
        }
    }

    fun getGameDetails(gameId: String) {
        viewModelScope.launch {
            networkRepository.getGameDetails(gameId).onEach { result ->
                when (result) {
                    is Resource.Error -> {
                        _state.update { it.copy(loading = false, error = result.message) }
                    }

                    is Resource.Loading -> {
                        _state.update { it.copy(loading = true) }
                    }

                    is Resource.Success -> {
                        _state.update { it.copy(loading = false, gameDetails = result.data) }
                    }
                }
            }.launchIn(this)
        }
    }

    fun getGameScreenShots(gameId: String) {
        viewModelScope.launch {
            networkRepository.getGameScreenShots(gameId).onEach { result ->
                when (result) {
                    is Resource.Error -> {
                        _state.update {
                            it.copy(
                                loading = false,
                                error = result.message
                            )
                        }
                    }

                    is Resource.Loading -> {
                        _state.update { it.copy(loading = true) }
                    }

                    is Resource.Success -> {
                        _state.update {
                            it.copy(
                                loading = false,
                                screenShots = result.data ?: emptyList()
                            )
                        }
                    }
                }
            }.launchIn(this)
        }
    }

    fun getGameAchievements(gameId: String) {
        viewModelScope.launch {
            networkRepository.getGameAchievements(gameId).onEach { result ->
                when (result) {
                    is Resource.Error -> {
                        _state.update { it.copy(loading = false, error = result.message) }
                    }

                    is Resource.Loading -> {
                        _state.update { it.copy(loading = true) }
                    }

                    is Resource.Success -> {
                        _state.update {
                            it.copy(
                                loading = false,
                                achievements = result.data ?: emptyList()
                            )
                        }
                    }
                }
            }.launchIn(this)
        }
    }

    fun getRelatedGames(gameId: String) {
        viewModelScope.launch {
            networkRepository.getRelatedGames(gameId).onEach { result ->
                when (result) {
                    is Resource.Error -> {
                        _state.update {
                            it.copy(
                                loading = false,
                                error = result.message
                            )
                        }
                    }

                    is Resource.Loading -> {
                        _state.update { it.copy(loading = true) }
                    }

                    is Resource.Success -> {
                        _state.update {
                            it.copy(
                                loading = false,
                                relatedGames = result.data ?: emptyList()
                            )
                        }
                    }
                }
            }.launchIn(this)
        }
    }

    fun getAdditions(gameId: String) {
        viewModelScope.launch {
            networkRepository.getAdditions(gameId).onEach { result ->
                when (result) {
                    is Resource.Error -> {
                        _state.update {
                            it.copy(
                                loading = false,
                                error = result.message
                            )
                        }
                    }

                    is Resource.Loading -> {
                        _state.update { it.copy(loading = true) }
                    }

                    is Resource.Success -> {
                        _state.update {
                            it.copy(
                                loading = false,
                                additions = result.data ?: emptyList()
                            )
                        }
                    }
                }
            }.launchIn(this)
        }
    }

    fun getDevelopmentTeam(gameId: String) {
        viewModelScope.launch {
            networkRepository.getDevelopmentTeam(gameId).onEach { result ->
                when (result) {
                    is Resource.Error -> {
                        _state.update {
                            it.copy(
                                loading = false,
                                error = result.message
                            )
                        }
                    }

                    is Resource.Loading -> {
                        _state.update { it.copy(loading = true) }
                    }

                    is Resource.Success -> {
                        _state.update {
                            it.copy(
                                loading = false,
                                creators = result.data ?: emptyList()
                            )
                        }
                    }
                }
            }.launchIn(this)
        }
    }

    fun checkIfFavourite() {
        viewModelScope.launch(Dispatchers.IO) {
            _state.update { it.copy(isFavourite = localRepository.checkIfGameIsFavourite(_state.value.gameId!!)) }
        }
    }

    fun addGameToFavourites(gameDetails: GameDetails) {
        viewModelScope.launch(Dispatchers.IO) {
            localRepository.addGameToFavourites(gameDetails)
            _state.update { it.copy(isFavourite = true) }
        }
    }

    fun removeGameFromFavourites() {
        viewModelScope.launch(Dispatchers.IO) {
            localRepository.removeGameFromFavourites(_state.value.gameId!!)
            _state.update { it.copy(isFavourite = false) }
        }
    }
}