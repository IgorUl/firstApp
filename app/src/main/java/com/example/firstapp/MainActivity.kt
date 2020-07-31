package com.example.firstapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    private val strList = mutableListOf<String>()

    fun addString(view: View) {

        val str = editText.text.toString()
        strList.add(str)
        textView.text = strList.joinToString("\n", "", "")
        editText.setText("")
    }
}
