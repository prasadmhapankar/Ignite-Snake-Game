package com.ignite.ignitesnakegame.data

import com.ignite.ignitesnakegame.domain.dto.StateResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface SnakeService {
    @GET("state")
    suspend fun getSnakeState(): Response<StateResponse>

    @POST("move")
    suspend fun postSnakeState(
        @Body request: MoveRequest,
    ): Response<StateResponse>
}