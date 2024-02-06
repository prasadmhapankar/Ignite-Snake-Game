package com.ignite.ignitesnakegame.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ignite.ignitesnakegame.common.Constants.PLAYER_1
import com.ignite.ignitesnakegame.common.Resource
import com.ignite.ignitesnakegame.data.remote.MoveRequest
import com.ignite.ignitesnakegame.domain.use_case.GetSnakeStateUseCase
import com.ignite.ignitesnakegame.domain.use_case.PostSnakeStateUseCase
import com.ignite.ignitesnakegame.domain.util.Direction
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val TAG = "MainViewModel"

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getSnakeStateUseCase: GetSnakeStateUseCase,
    private val postSnakeStateUseCase: PostSnakeStateUseCase,
) : ViewModel() {

    private val _state = MutableStateFlow(SnakeState())
    val state: StateFlow<SnakeState> = _state.asStateFlow()

    private val _eventsFlow = Channel<SnakeUiEvent>()
    val eventsFlow: Flow<SnakeUiEvent> = _eventsFlow.receiveAsFlow()

    init {
        Log.d(TAG, ": MainViewModel init")
        getSnakeState()
        //resetAndPlay()
    }

    fun onEvent(snakeStateEvent: SnakeEvent) {
        when (snakeStateEvent) {
            is SnakeEvent.OnMove -> postSnakeState(snakeStateEvent.direction)
            SnakeEvent.OnGet -> getSnakeState()
        }
    }

    private fun postSnakeState(direction: Direction) {
        Log.d(TAG, "postSnakeState: direction : $direction")
        viewModelScope.launch(Dispatchers.IO) {
            postSnakeStateUseCase(
                postMove = MoveRequest(
                    playerId = PLAYER_1,
                    direction = direction.id
                )
            ).collectLatest { response: Resource<SnakeState> ->
                when (response) {
                    is Resource.Error -> {
                        //resetAndPlay()
                    }

                    is Resource.Loading -> {}
                    is Resource.Success -> {
                        _state.update {
                            it.copy(
                                board = response.data?.board,
                                isGameOver = response.data?.isGameOver
                            )
                        }
                    }
                }
            }
        }
    }

    private fun getSnakeState() {
        //Get SnakeState from server
        viewModelScope.launch(Dispatchers.IO) {
            getSnakeStateUseCase().collectLatest { response ->
                Log.d(TAG, "getSnakeState response: ${response.data}")

                when (response) {
                    is Resource.Error -> {}
                    is Resource.Loading -> {}
                    is Resource.Success -> {
                        _state.update {
                            it.copy(
                                board = response.data?.board,
                                isGameOver = response.data?.isGameOver
                            )
                        }
                        delay(1000L)
                        cancel()
                        _eventsFlow.send(SnakeUiEvent.OnGet)
                    }
                }
            }

        }
    }

}