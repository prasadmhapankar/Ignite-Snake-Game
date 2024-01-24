package com.ignite.ignitesnakegame.domain.dto

import com.google.gson.annotations.SerializedName

data class Fruit(
    @SerializedName("x")
    val x: Double? = null,
    @SerializedName("y")
    val y: Double? = null
)