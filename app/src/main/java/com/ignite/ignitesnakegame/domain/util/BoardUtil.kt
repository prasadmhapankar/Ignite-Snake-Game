package com.ignite.ignitesnakegame.domain.util

import com.ignite.ignitesnakegame.common.Constants
import com.ignite.ignitesnakegame.domain.model.Cell
import com.ignite.ignitesnakegame.domain.model.Coordinate
import com.ignite.ignitesnakegame.domain.model.SnakeBoard

object BoardUtil {
    fun updateBoardData(response: SnakeBoard): List<List<Cell>>? {
        val emptyBoard = List(Constants.BOARD_SIZE) { List(Constants.BOARD_SIZE) { Cell.EMPTY } }
        val player1: List<Coordinate>? = response.snakes?.get(Constants.PLAYER_1)
        val player2: List<Coordinate>? = response.snakes?.get(Constants.PLAYER_2)
        val fruit: List<Coordinate>? = response.fruits
        val gameBoard = emptyBoard.mapIndexed { row, cells ->
            cells.mapIndexed { column, _ ->
                if (player1?.contains(Coordinate(row, column)) == true) {
                    if (player1.first() == Coordinate(row, column)) {
                        Cell.SNAKE_PLAYER_1_HEAD
                    } else {
                        Cell.SNAKE_PLAYER_1
                    }
                } else if (player2?.contains(Coordinate(row, column)) == true) {
                    if (player2.first() == Coordinate(row, column)) {
                        Cell.SNAKE_PLAYER_2_HEAD
                    } else {
                        Cell.SNAKE_PLAYER_2
                    }
                } else if (fruit?.contains(Coordinate(row, column)) == true) {
                    Cell.FRUIT
                } else {
                    Cell.EMPTY
                }
            }
        }
        return gameBoard
    }
}