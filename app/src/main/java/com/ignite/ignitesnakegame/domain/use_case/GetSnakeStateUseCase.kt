package com.ignite.ignitesnakegame.domain.use_case

import com.ignite.ignitesnakegame.common.Resource
import com.ignite.ignitesnakegame.data.remote.dto.toSnakeBoard
import com.ignite.ignitesnakegame.domain.repository.SnakeRepository
import com.ignite.ignitesnakegame.domain.util.BoardUtil
import com.ignite.ignitesnakegame.presentation.SnakeState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetSnakeStateUseCase @Inject constructor(
    val repository: SnakeRepository
) {
    suspend operator fun invoke(): Flow<Resource<SnakeState>> = flow {

        //emit(Resource.Loading())
        val response = repository.getSnakeState().toSnakeBoard()
        val gameBoard = BoardUtil.updateBoardData(response)
        emit(Resource.Success(SnakeState(board = gameBoard, isGameOver = response.gameOver)))
    }
}