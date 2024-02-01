package com.ignite.ignitesnakegame.ui

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ignite.ignitesnakegame.data.MoveRequest
import com.ignite.ignitesnakegame.data.SnakeRepository
import com.ignite.ignitesnakegame.domain.dto.Coordinate
import com.ignite.ignitesnakegame.domain.dto.StateResponse
import com.ignite.ignitesnakegame.domain.entity.Cell
import com.ignite.ignitesnakegame.domain.entity.Direction
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Response
import javax.inject.Inject

private const val TAG = "MainViewModel"

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: SnakeRepository
) : ViewModel() {

    private val _state = mutableStateOf(SnakeState())
    val state: State<SnakeState> = _state

    init {
        Log.d(TAG, ": MainViewModel init")
        postSnakeState(Direction.DOWN)
        //initBoard()
    }

    fun onEvent(snakeStateEvent: SnakeEvent) {
        when (snakeStateEvent) {
            is SnakeEvent.OnMove -> postSnakeState(snakeStateEvent.direction)
        }
    }

    private fun postSnakeState(direction: Direction) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.postSnakeState(
                MoveRequest(
                    playerId = "player1",
                    direction = direction.id,
                )
            ).collectLatest { response: Response<StateResponse> ->
                if (response.isSuccessful) {
                    val responseBody = response.body()
                    Log.d(TAG, "initBoard Post response: $responseBody")
                    //Update Snake state
                    responseBody?.apply {
                        updateBoard(responseBody)
                    }
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
                    val responseBody = response.body()
                    Log.d(TAG, "initBoard Get response: $responseBody")

                    responseBody?.apply {
                        updateBoard(responseBody)
                    }
                }
            }
        }
    }

    private fun updateBoard(responseBody: StateResponse) {
        val emptyBoard = List(50) { List(size = 50) { Cell.EMPTY } }
        val snake: List<Coordinate>? = responseBody.snakes?.get("player1")
        val fruit: List<Coordinate>? = responseBody.fruits

        viewModelScope.launch(Dispatchers.IO) {
            val gameBoard = emptyBoard.mapIndexed { index, cells ->
                cells.mapIndexed { column, _ ->
                    if (snake?.contains(Coordinate(index, column)) == true) {
                        Log.d(TAG, "updateBoard snake: index - $index   column - $column")
                        Cell.SNAKE
                    } else if (fruit?.contains(Coordinate(index, column)) == true) {
                        Cell.FRUIT
                    } else {
                        Cell.EMPTY
                    }
                }
            }
            withContext(Dispatchers.Main) {
                _state.value = state.value.copy(board = gameBoard)
            }
        }
    }

}