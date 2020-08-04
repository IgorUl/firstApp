package com.example.firstapp

import android.widget.TextView
import java.io.*

class Data {


    private val fileName: String = "inputString"



    fun writeFile(textView: TextView) {
        val writer = File(fileName).bufferedWriter()
        writer.write(textView.text.toString())
        writer.close()
    }

//    fun readFile() {
//        File(fileName).readLines()
//    }
}