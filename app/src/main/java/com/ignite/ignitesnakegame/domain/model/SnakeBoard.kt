package com.ignite.ignitesnakegame.domain.model

import com.ignite.ignitesnakegame.data.remote.dto.CoordinateDto

data class SnakeBoard(
    val fruits: List<CoordinateDto>? = null,
    val gameOver: Boolean? = null,
    val isFruitEaten: Boolean? = null,
    val message: String? = null,
    val snakes: Map<String, List<CoordinateDto>>? = null
)
