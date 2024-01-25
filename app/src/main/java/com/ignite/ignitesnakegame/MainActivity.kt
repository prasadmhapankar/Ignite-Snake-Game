package com.ignite.ignitesnakegame

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.ignite.ignitesnakegame.domain.entity.Cell
import com.ignite.ignitesnakegame.ui.MainViewModel
import com.ignite.ignitesnakegame.ui.SnakeEvent
import com.ignite.ignitesnakegame.ui.SnakeState
import com.ignite.ignitesnakegame.ui.theme.IgniteSnakegameTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val mainViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            IgniteSnakegameTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Board(
                        mainViewModel.state,
                        mainViewModel::onEvent,
                    )
                }
            }
        }
    }
}

@Composable
fun Board(
    state: State<SnakeState>,
    onEvent: (event: SnakeEvent) -> Unit,
    modifier: Modifier = Modifier
) {
    val stateBoard = state.value.board
    Column {
        stateBoard.forEach { row ->
            Row {
                row.forEach { cell ->
                    Box(
                        modifier = Modifier
                            .size(6.dp)
                            .padding(1.dp)
                            .background(
                                color = when (cell) {
                                    Cell.EMPTY -> Color.LightGray
                                    Cell.SNAKE -> Color.Green
                                    Cell.FRUIT -> Color.Red
                                }
                            ),
                    )
                }
            }
        }
        Button(onClick = { onEvent(SnakeEvent.OnMoveUp) }) {
            Text(
                text = "UP", modifier = Modifier
                    .size(20.dp)
            )
        }
    }
}