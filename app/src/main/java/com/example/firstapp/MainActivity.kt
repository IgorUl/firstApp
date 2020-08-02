package com.example.firstapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.view.*

class MainActivity : AppCompatActivity() {

    private val logic = Logic()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        // почему если передавать it, то приложение крашиться
        addButton.setOnClickListener {
            logic.addString(textView, editText)
            editText.setText("")
        }
        sortButton.setOnClickListener {
            logic.sort(textView)
        }
    }
}
