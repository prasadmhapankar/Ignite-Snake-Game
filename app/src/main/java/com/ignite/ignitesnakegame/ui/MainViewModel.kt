package com.ignite.ignitesnakegame.ui

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ignite.ignitesnakegame.data.MoveRequest
import com.ignite.ignitesnakegame.data.SnakeRepository
import com.ignite.ignitesnakegame.domain.dto.StateResponse
import com.ignite.ignitesnakegame.domain.entity.Cell
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

private const val TAG = "MainViewModel"

@HiltViewModel
class MainViewModel @Inject constructor(
    val repository: SnakeRepository
) : ViewModel() {

    private val _state = mutableStateOf(SnakeState())
    val state: State<SnakeState> = _state

    init {
        Log.d(TAG, ": MainViewModel init")
        initBoard()
    }

    fun onEvent(snakeStateEvent: SnakeEvent) {
        when (snakeStateEvent) {
            SnakeEvent.OnMoveUp -> postSnakeState()
        }
    }

    private fun postSnakeState() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.postSnakeState(
                MoveRequest(
                    playerId = 1,
                    moveDirection = 1,
                )
            ).collectLatest { response: Response<StateResponse> ->
                if (response.isSuccessful) {
                    Log.d(TAG, "initBoard Post response: ${response.body()}")
                }
            }
        }
    }

    private fun initBoard() {

        //Get SnakeState from server
        viewModelScope.launch(Dispatchers.IO) {
            repository.getSnakeState().collectLatest { response ->
                Log.d(TAG, "initBoard response: $response")
                if (response.isSuccessful) {
                    Log.d(TAG, "initBoard Get response: ${response.body()}")
                }
            }
        }

        val emptyBoard = List(50) { List(size = 50) { Cell.EMPTY } }
        val snake: List<Pair<Int, Int>> = listOf(Pair(3, 0), Pair(3, 1), Pair(3, 2))
        val fruit: List<Pair<Int, Int>> = listOf(Pair(5, 2), Pair(3, 8), Pair(3, 7))

        val gameBoard = emptyBoard.mapIndexed { index, cells ->

            cells.mapIndexed { column, _ ->
                if (snake.contains(Pair(index, column))) {
                    Cell.SNAKE
                } else if (fruit.contains(Pair(index, column))) {
                    Cell.FRUIT
                } else {
                    Cell.EMPTY
                }
            }
        }
        _state.value = state.value.copy(board = gameBoard)

    }

    fun buildSnake() {
        _state.value = state.value
    }


}