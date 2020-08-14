package com.example.firstapp

import android.app.Application
import com.example.firstapp.model.Model

class App : Application() {
    val model = Model()
}