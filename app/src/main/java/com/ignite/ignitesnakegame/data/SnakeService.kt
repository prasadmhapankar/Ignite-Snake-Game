package com.ignite.ignitesnakegame.data

import com.ignite.ignitesnakegame.domain.dto.StateResponse
import retrofit2.Response
import retrofit2.http.GET

interface SnakeService {
    @GET("state")
    suspend fun getSnakeState(): Response<StateResponse>
}