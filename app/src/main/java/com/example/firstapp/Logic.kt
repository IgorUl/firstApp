package com.example.firstapp

import android.widget.EditText
import android.widget.TextView

class Logic {

    private val stringList = mutableListOf<String>()
    private val sort = Sort()


    fun addString(textView: TextView, editText: EditText) {

        val str = editText.text.toString()
        stringList.add(str)
        textView.text = stringList.joinToString("\n", "", "")

    }

    fun sort(textView: TextView) {
        val sortedList = sort.mergeSort(stringList)
        textView.text = sortedList.joinToString("\n", "", "")
    }


}