package com.example.firstapp.common

import android.app.Application
import com.example.firstapp.mvp.model.Model

class App : Application() {
    val model = Model()
}