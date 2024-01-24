package com.ignite.ignitesnakegame.data

import android.util.Log
import com.ignite.ignitesnakegame.domain.dto.StateResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Response
import javax.inject.Inject

private const val TAG = "SnakeRepositoryImpl"

class SnakeRepositoryImpl @Inject constructor(
    private val apiService: SnakeService,
) : SnakeRepository {
    override suspend fun getSnakeState(): Flow<Response<StateResponse>> =
        flow {
            val response = apiService.getSnakeState()
            Log.d(TAG, "getSnakeState: $response")
            if(response.isSuccessful){
                emit(response)
            }
        }
}