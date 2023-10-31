package com.mungaicodes.gamehub.presentation.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mungaicodes.gamehub.domain.repo.NetworkRepository
import com.mungaicodes.gamehub.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
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
class SearchViewModel @Inject constructor(
    private val networkRepository: NetworkRepository
) : ViewModel() {

    private val _state = MutableStateFlow(SearchScreenUiState())
    val state = _state.asStateFlow()

    private val _eventFlow = MutableSharedFlow<SearchScreenUiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    private var searchJob: Job? = null

    fun onEvent(event: SearchScreenEvent) {
        when (event) {
            is SearchScreenEvent.OnSearchEvent -> {
                searchForGame(_state.value.searchQuery)
            }

            is SearchScreenEvent.OnSearchQueryChanged -> {
                _state.update { it.copy(searchQuery = event.query) }
            }

            is SearchScreenEvent.OnGameClick -> {
                viewModelScope.launch {
                    _eventFlow.emit(SearchScreenUiEvent.NavigateToDetails(event.gameId))
                }
            }
        }
    }

    private fun searchForGame(searchQuery: String) {
        viewModelScope.launch {
            searchJob?.cancel()
            searchJob = networkRepository.searchForGameByQuery(searchQuery).onEach { result ->
                when (result) {
                    is Resource.Error -> {
                        _state.update { it.copy(loading = false, searchError = result.message) }
                    }

                    is Resource.Loading -> {
                        _state.update { it.copy(loading = true, searchError = null) }
                    }

                    is Resource.Success -> {
                        if (result.data?.isNotEmpty() == true) {
                            _state.update {
                                it.copy(
                                    loading = false,
                                    searchResults = result.data ?: emptyList(),
                                    supportText = "Showing results for $searchQuery"
                                )
                            }
                        } else {
                            _state.update {
                                it.copy(
                                    loading = false,
                                    searchResults = emptyList(),
                                    supportText = "No results found"
                                )
                            }
                        }
                    }
                }
            }.launchIn(this)
        }
    }
}