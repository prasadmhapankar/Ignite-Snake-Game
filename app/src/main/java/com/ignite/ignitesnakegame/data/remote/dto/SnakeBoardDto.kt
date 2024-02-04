package com.ignite.ignitesnakegame.data.remote.dto

import com.google.gson.annotations.SerializedName
import com.ignite.ignitesnakegame.domain.model.SnakeBoard

data class SnakeBoardDto(
    @SerializedName("fruits")
    val fruits: List<CoordinateDto>? = null,
    @SerializedName("gameOver")
    val gameOver: Boolean? = null,
    @SerializedName("isFruitEaten")
    val isFruitEaten: Boolean? = null,
    @SerializedName("message")
    val message: String? = null,
    @SerializedName("snakes")
    val snakes: Map<String, List<CoordinateDto>>? = null
)

fun SnakeBoardDto.toSnakeBoard(): SnakeBoard = SnakeBoard(
    fruits = fruits,
    gameOver = gameOver,
    isFruitEaten = isFruitEaten,
    message = message,
    snakes = snakes,
)