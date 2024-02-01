package com.ignite.ignitesnakegame.ui

import com.ignite.ignitesnakegame.domain.entity.Direction

sealed class SnakeEvent {
    data class OnMove(val direction: Direction) : SnakeEvent()
}
