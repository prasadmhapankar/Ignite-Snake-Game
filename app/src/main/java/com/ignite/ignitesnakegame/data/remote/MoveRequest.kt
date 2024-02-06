package com.ignite.ignitesnakegame.data.remote

data class MoveRequest(
    val playerId: String,
    val direction: Int,
)
