package com.ignite.ignitesnakegame.ui

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.ignite.ignitesnakegame.domain.entity.Cell
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

private const val TAG = "MainViewModel"

@HiltViewModel
class MainViewModel @Inject constructor() : ViewModel() {

    private val _state = mutableStateOf(SnakeState())
    val state: State<SnakeState> = _state

    init {
        Log.d(TAG, ": MainViewModel init")
        initBoard()
    }

    private fun initBoard() {
        val emptyBoard = List(50) { List(size = 50) { Cell.EMPTY } }
        val snake: List<Pair<Int, Int>> = listOf(Pair(3, 0), Pair(3, 1), Pair(3, 2))

        val gameBoard = emptyBoard.mapIndexed { index, cells ->

            cells.mapIndexed { column, _ ->
                if (snake.contains(Pair(index, column))) {
                    Cell.SNAKE
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