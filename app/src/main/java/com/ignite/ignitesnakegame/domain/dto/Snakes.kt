package com.ignite.ignitesnakegame.domain.dto

import com.google.gson.annotations.SerializedName

data class Snakes(
    @SerializedName("player1")
    val player1: List<Player1>
)