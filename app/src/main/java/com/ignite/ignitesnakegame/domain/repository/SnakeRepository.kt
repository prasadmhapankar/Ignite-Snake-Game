package com.ignite.ignitesnakegame.domain.repository

import com.ignite.ignitesnakegame.data.MoveRequest
import com.ignite.ignitesnakegame.data.remote.dto.SnakeBoardDto

interface SnakeRepository {
    suspend fun getSnakeState(): SnakeBoardDto
    suspend fun postSnakeState(postMove: MoveRequest): SnakeBoardDto
}