package com.ignite.ignitesnakegame.domain.model

data class SnakeBoard(
    val fruits: List<Coordinate>? = null,
    val gameOver: Boolean? = null,
    val isFruitEaten: Boolean? = null,
    val message: String? = null,
    val snakes: Map<String, List<Coordinate>>? = null
)
