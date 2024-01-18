package com.ignite.ignitesnakegame.ui

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.ignite.ignitesnakegame.domain.entity.Cell
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

private const val TAG = "MainViewModel"

@HiltViewModel
class MainViewModel @Inject constructor() : ViewModel() {

    private val _state = mutableStateOf(SnakeState())
    val state: State<SnakeState> = _state

    init {
        Log.d(TAG, ": MainViewModel init")
        initBoard()
    }

    private fun initBoard() {
        
    }
}