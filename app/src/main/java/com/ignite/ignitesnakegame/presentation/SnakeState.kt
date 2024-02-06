package com.ignite.ignitesnakegame.presentation

import com.ignite.ignitesnakegame.domain.model.Cell
import com.ignite.ignitesnakegame.domain.util.Direction

data class SnakeState(
    val board: List<List<Cell>>? = null,
    val isGameOver: Boolean? = false,
    val direction : Direction = Direction.LEFT,
)