package com.mungaicodes.gamehub.presentation.details

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
class DetailsViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val networkRepository: NetworkRepository
) : ViewModel() {

    private val _state = MutableStateFlow(DetailsScreenUiState())
    val state = _state.asStateFlow()

    private val _eventFlow = MutableSharedFlow<DetailsScreenUiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    init {
        savedStateHandle.get<String>("gameId")?.let { gameId ->
            getGameDetails(gameId)
        }
    }

    fun onEvent(event: DetailsScreenEvent) {
        when (event) {
            is DetailsScreenEvent.AddGameToFavourites -> {

            }

            DetailsScreenEvent.GoBack -> {
                viewModelScope.launch {
                    _eventFlow.emit(DetailsScreenUiEvent.NavigateBack)
                }
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
}