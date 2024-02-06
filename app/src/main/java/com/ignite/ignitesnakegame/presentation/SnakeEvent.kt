package com.ignite.ignitesnakegame.presentation

import com.ignite.ignitesnakegame.domain.util.Direction

sealed class SnakeEvent {
    data class OnMove(val direction: Direction) : SnakeEvent()
    object OnGet : SnakeEvent()
    //object ResetAndPlay : SnakeEvent()
}