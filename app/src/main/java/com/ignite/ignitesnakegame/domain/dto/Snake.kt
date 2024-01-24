package com.ignite.ignitesnakegame.domain.dto

import com.google.gson.annotations.SerializedName

data class Snake(
    @SerializedName("id")
    val id: Int? = null,
    @SerializedName("score")
    val score: Int? = null,
    @SerializedName("snake")
    val snake: List<Point?>? = null
)