package com.ignite.ignitesnakegame.data.remote

import com.ignite.ignitesnakegame.data.remote.dto.SnakeBoardDto
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface SnakeService {
    @GET("state")
    suspend fun getSnakeState(): SnakeBoardDto
    @GET("reset")
    suspend fun resetGame(): SnakeBoardDto

    @POST("move")
    suspend fun postSnakeState(
        @Body request: MoveRequest,
    ): SnakeBoardDto
}