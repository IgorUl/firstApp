package com.example.firstapp

import android.app.Application
import com.example.firstapp.data.*

class App : Application() {

    lateinit var model: Model
        private set

    override fun onCreate() {
        super.onCreate()
        val fileStorage = FileStorage(this)
        model = Model(CommentHolder(), CommentSorter(), fileStorage, TimeProvider())
    }
}