package com.ignite.ignitesnakegame.domain.repository

import com.ignite.ignitesnakegame.data.remote.MoveRequest
import com.ignite.ignitesnakegame.data.remote.dto.SnakeBoardDto

interface SnakeRepository {
    suspend fun getSnakeState(): SnakeBoardDto
    suspend fun postSnakeState(postMove: MoveRequest): SnakeBoardDto
    suspend fun resetGame(): SnakeBoardDto
}