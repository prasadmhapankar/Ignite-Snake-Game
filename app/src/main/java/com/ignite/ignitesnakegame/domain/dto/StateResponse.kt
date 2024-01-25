package com.ignite.ignitesnakegame.domain.dto

import com.google.gson.annotations.SerializedName

data class StateResponse(
    @SerializedName("fruits")
    val fruits: List<Fruit>,
    @SerializedName("gameOver")
    val gameOver: Boolean,
    @SerializedName("isFruitEaten")
    val isFruitEaten: Boolean,
    @SerializedName("message")
    val message: String,
    @SerializedName("snakes")
    val snakes: Snakes
)