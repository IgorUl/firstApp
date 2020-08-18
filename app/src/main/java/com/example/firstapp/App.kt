package com.example.firstapp

import android.app.Application
import com.example.firstapp.data.Model

class App : Application() {
    val model = Model()
}