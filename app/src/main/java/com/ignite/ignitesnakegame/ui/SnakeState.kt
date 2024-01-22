package com.ignite.ignitesnakegame.ui

import com.ignite.ignitesnakegame.domain.entity.Cell

data class SnakeState(
  val board : List<List<Cell>> = List(50) { List(size = 50) { Cell.EMPTY } }
) {

}
