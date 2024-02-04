package com.ignite.ignitesnakegame.domain.use_case

import com.ignite.ignitesnakegame.common.Constants.BOARD_SIZE
import com.ignite.ignitesnakegame.common.Resource
import com.ignite.ignitesnakegame.data.MoveRequest
import com.ignite.ignitesnakegame.data.remote.dto.toCoordinateList
import com.ignite.ignitesnakegame.data.remote.dto.toSnakeBoard
import com.ignite.ignitesnakegame.domain.repository.SnakeRepository
import com.ignite.ignitesnakegame.domain.model.Cell
import com.ignite.ignitesnakegame.domain.model.Coordinate
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class PostSnakeStateUseCase @Inject constructor(
    private val repository: SnakeRepository,
) {
    suspend operator fun invoke(postMove: MoveRequest): Flow<Resource<List<List<Cell>>>> = flow {
        try {
            emit(Resource.Loading())
            val response = repository.postSnakeState(postMove = postMove).toSnakeBoard()
            val emptyBoard = List(BOARD_SIZE) { List(BOARD_SIZE) { Cell.EMPTY } }
            val snake: List<Coordinate>? = response.snakes?.get("player1")?.toCoordinateList()
            val fruit: List<Coordinate>? = response.fruits?.toCoordinateList()
            val gameBoard = emptyBoard.mapIndexed { index, cells ->
                List(cells.size) { column ->
                    if (snake?.contains(Coordinate(index, column)) == true) {
                        Cell.SNAKE
                    } else if (fruit?.contains(Coordinate(index, column)) == true) {
                        Cell.FRUIT
                    } else {
                        Cell.EMPTY
                    }
                }
            }
            emit(Resource.Success(gameBoard))
        } catch (exception: HttpException) {
            emit(Resource.Error(exception.localizedMessage ?: "An unexpected error occured!"))
        } catch (e: IOException) {
            emit(Resource.Error("Couldn't reach server. Please check your internet connection!"))
        }
    }
}