package com.ignite.ignitesnakegame.domain.use_case

import com.ignite.ignitesnakegame.common.Constants.BOARD_SIZE
import com.ignite.ignitesnakegame.common.Constants.PLAYER_1
import com.ignite.ignitesnakegame.common.Constants.PLAYER_2
import com.ignite.ignitesnakegame.common.Resource
import com.ignite.ignitesnakegame.data.remote.MoveRequest
import com.ignite.ignitesnakegame.data.remote.dto.toSnakeBoard
import com.ignite.ignitesnakegame.domain.repository.SnakeRepository
import com.ignite.ignitesnakegame.domain.model.Cell
import com.ignite.ignitesnakegame.domain.model.Coordinate
import com.ignite.ignitesnakegame.domain.model.SnakeBoard
import com.ignite.ignitesnakegame.domain.util.BoardUtil.updateBoardData
import com.ignite.ignitesnakegame.presentation.SnakeState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class PostSnakeStateUseCase @Inject constructor(
    private val repository: SnakeRepository,
) {
    suspend operator fun invoke(postMove: MoveRequest): Flow<Resource<SnakeState>> = flow {
        try {
            emit(Resource.Loading())
            val response = repository.postSnakeState(postMove = postMove).toSnakeBoard()
            val gameBoard = updateBoardData(response)
            emit(Resource.Success(SnakeState(board = gameBoard, isGameOver = response.gameOver)))
        } catch (exception: HttpException) {
            emit(Resource.Error(exception.localizedMessage ?: "An unexpected error occured!"))
        } catch (e: IOException) {
            emit(Resource.Error("Couldn't reach server. Please check your internet connection!"))
        }
    }

}