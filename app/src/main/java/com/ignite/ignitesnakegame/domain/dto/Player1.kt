package com.ignite.ignitesnakegame.domain.dto

import com.google.gson.annotations.SerializedName

data class Player1(
    @SerializedName("x")
    val x: Int? = null,
    @SerializedName("y")
    val y: Int? = null
)