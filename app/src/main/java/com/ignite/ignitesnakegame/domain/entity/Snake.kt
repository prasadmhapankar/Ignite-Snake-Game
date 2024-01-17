package com.ignite.ignitesnakegame.domain.entity

data class Snake(
    val id: String,
    val head: Pair<Int, Int> = Pair(0, 0),
    val body: List<Pair<Int, Int>> = listOf(),
    val tail: Pair<Int, Int> = Pair(0, 0),
)
