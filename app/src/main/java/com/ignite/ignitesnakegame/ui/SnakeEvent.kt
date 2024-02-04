package com.ignite.ignitesnakegame.ui

import com.ignite.ignitesnakegame.domain.util.Direction

sealed class SnakeEvent {
    data class OnMove(val direction: Direction) : SnakeEvent()
}
