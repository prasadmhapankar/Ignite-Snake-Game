package com.ignite.ignitesnakegame.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.ignite.ignitesnakegame.domain.model.Cell
import com.ignite.ignitesnakegame.domain.util.Direction
import com.ignite.ignitesnakegame.ui.SnakeEvent
import com.ignite.ignitesnakegame.ui.SnakeState
import com.ignite.ignitesnakegame.common.Constants.BOARD_SIZE

@Composable
fun SnakeScreen(
    state: State<SnakeState>,
    onEvent: (event: SnakeEvent) -> Unit,
) {
    val stateBoard = state.value.board

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceAround
    ) {
        Column {
            for (i in 0 until BOARD_SIZE)
                Row {
                    for (j in 0 until BOARD_SIZE) {
                        val isLightSquare = i % 2 == j % 2
                        val cell = stateBoard?.get(j)?.get(i)
                        cell?.let {
                            Box(
                                modifier = Modifier
                                    .aspectRatio(1f)
                                    .weight(1f)
                                    .background(
                                        when (cell) {
                                            Cell.EMPTY -> if (isLightSquare)
                                                Color.LightGray.copy(alpha = 0.2f)
                                            else Color.LightGray

                                            Cell.SNAKE -> Color.Green
                                            Cell.FRUIT -> Color.Red
                                        }
                                    )
                            )
                        }
                    }
                }
        }

        Column(modifier = Modifier.fillMaxWidth()) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
            ) {
                Button(onClick = { onEvent(SnakeEvent.OnMove(Direction.UP)) }) {
                    Text(
                        text = "UP",
                    )
                }
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
            ) {
                Button(onClick = { onEvent(SnakeEvent.OnMove(Direction.LEFT)) }) {
                    Text(
                        text = "LEFT"
                    )
                }
                Spacer(modifier = Modifier.size(30.dp))
                Button(onClick = { onEvent(SnakeEvent.OnMove(Direction.RIGHT)) }) {
                    Text(
                        text = "RIGHT"
                    )
                }
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
            ) {
                Button(
                    onClick = { onEvent(SnakeEvent.OnMove(Direction.DOWN)) }) {
                    Text(
                        text = "DOWN"
                    )
                }
            }
        }
    }
}