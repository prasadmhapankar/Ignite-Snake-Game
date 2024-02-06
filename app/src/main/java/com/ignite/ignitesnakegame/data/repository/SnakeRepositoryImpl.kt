package com.ignite.ignitesnakegame.data.repository

import com.ignite.ignitesnakegame.data.remote.MoveRequest
import com.ignite.ignitesnakegame.data.remote.SnakeService
import com.ignite.ignitesnakegame.data.remote.dto.SnakeBoardDto
import com.ignite.ignitesnakegame.domain.repository.SnakeRepository
import javax.inject.Inject

class SnakeRepositoryImpl @Inject constructor(
    private val apiService: SnakeService,
) : SnakeRepository {
    override suspend fun getSnakeState(): SnakeBoardDto =
        apiService.getSnakeState()

    override suspend fun postSnakeState(postMove: MoveRequest): SnakeBoardDto =
        apiService.postSnakeState(postMove)

    override suspend fun resetGame(): SnakeBoardDto =
        apiService.resetGame()

}