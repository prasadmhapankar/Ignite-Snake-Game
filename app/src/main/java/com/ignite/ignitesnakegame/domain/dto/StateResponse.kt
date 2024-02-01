package com.ignite.ignitesnakegame.domain.dto

import com.google.gson.annotations.SerializedName

data class StateResponse(
    @SerializedName("fruits")
    val fruits: List<Coordinate>? = null,
    @SerializedName("gameOver")
    val gameOver: Boolean? = null,
    @SerializedName("isFruitEaten")
    val isFruitEaten: Boolean? = null,
    @SerializedName("message")
    val message: String? = null,
    @SerializedName("snakes")
    val snakes: Map<String, List<Coordinate>>? = null
)