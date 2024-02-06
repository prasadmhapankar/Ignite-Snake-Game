package com.ignite.ignitesnakegame.presentation.component

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.ignite.ignitesnakegame.R
import com.ignite.ignitesnakegame.common.Constants.BOARD_SIZE
import com.ignite.ignitesnakegame.domain.model.Cell
import com.ignite.ignitesnakegame.domain.util.Direction
import com.ignite.ignitesnakegame.presentation.SnakeEvent
import com.ignite.ignitesnakegame.presentation.SnakeState
import com.ignite.ignitesnakegame.presentation.SnakeUiEvent
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest

@Composable
fun SnakeScreen(
    state: SnakeState,
    onEvent: (event: SnakeEvent) -> Unit,
    eventsFlow: Flow<SnakeUiEvent>,
) {
    val stateBoard = state.board

    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.SpaceAround
    ) {
        Column(
            modifier = Modifier
                .padding(4.dp)
                .border(
                    width = 4.dp,
                    shape = RoundedCornerShape(2.dp),
                    color = Color.Black
                )
                .padding(4.dp),
        ) {
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

                                            Cell.SNAKE_PLAYER_1_HEAD -> Color.Green
                                            Cell.SNAKE_PLAYER_2_HEAD -> Color.Magenta
                                            Cell.SNAKE_PLAYER_1 -> Color.Green.copy(alpha = 0.5f)
                                            Cell.SNAKE_PLAYER_2 -> Color.Magenta.copy(alpha = 0.5f)
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
                IconArrow(rotateDegree = 90f,
                    onClick = {
                        onEvent(SnakeEvent.OnMove(Direction.UP))
                    }
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
            ) {
                IconArrow(rotateDegree = 0f,
                    onClick = {
                        onEvent(SnakeEvent.OnMove(Direction.LEFT))
                    }
                )
                Spacer(modifier = Modifier.size(72.dp))
                IconArrow(rotateDegree = 180f,
                    onClick = {
                        onEvent(SnakeEvent.OnMove(Direction.RIGHT))
                    }
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
            ) {
                IconArrow(rotateDegree = 270f,
                    onClick = {
                        onEvent(SnakeEvent.OnMove(Direction.DOWN))
                    }
                )
            }
        }
    }

    LaunchedEffect(key1 = true) {

        eventsFlow.collectLatest { event ->
            when (event) {
                SnakeUiEvent.OnGet -> onEvent(SnakeEvent.OnGet)
            }
        }
    }
}

@Composable
fun IconArrow(rotateDegree: Float = 0f, onClick: () -> Unit) {
    IconButton(
        modifier = Modifier
            .size(96.dp)
            .padding(8.dp),
        onClick = onClick
    ) {
        Icon(
            modifier = Modifier
                .size(72.dp)
                .rotate(rotateDegree),
            painter = painterResource(id = R.drawable.arrow_circle_left),
            contentDescription = "Left"
        )
    }
}