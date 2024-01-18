package com.ignite.ignitesnakegame.ui

import com.ignite.ignitesnakegame.domain.entity.Cell

data class SnakeState(
  val board: Array<Array<Cell>> = Array(50) { Array(size = 50) { Cell.EMPTY } },
) {

}
