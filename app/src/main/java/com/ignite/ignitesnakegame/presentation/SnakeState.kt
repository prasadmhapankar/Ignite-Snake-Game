package com.ignite.ignitesnakegame.presentation

import com.ignite.ignitesnakegame.domain.model.Cell

data class SnakeState(
    val board: List<List<Cell>>? = null,
)