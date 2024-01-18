package com.ignite

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class SnakeApplication : Application() {

    override fun onCreate() {
        super.onCreate()
    }
}