package com.ignite.ignitesnakegame.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

private const val TAG = "MainViewModel"

@HiltViewModel
class MainViewModel @Inject constructor() : ViewModel() {


    init {
        Log.d(TAG, ": MainViewModel init")
    }
}