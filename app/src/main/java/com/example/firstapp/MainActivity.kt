package com.example.firstapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val stringList = mutableListOf<String>()

    //delete
    val sort = Sort()
    //

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }

    fun addString(view: View) {

        val str = editText.text.toString()
        stringList.add(str)
        textView.text = stringList.joinToString("\n", "", "")
        editText.setText("")
    }

    fun sort(view: View) {
        val sortedList = sort.mergeSort(stringList)
        textView.text = sortedList.joinToString("\n", "", "")
    }
}
