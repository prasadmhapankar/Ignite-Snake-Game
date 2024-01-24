package com.ignite.ignitesnakegame.data

import com.ignite.ignitesnakegame.domain.dto.StateResponse
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface SnakeRepository {
    suspend fun getSnakeState(): Flow<Response<StateResponse>>
}