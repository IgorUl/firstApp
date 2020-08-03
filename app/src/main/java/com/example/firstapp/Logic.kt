package com.example.firstapp


import android.icu.text.DateFormat
import android.icu.text.SimpleDateFormat
import android.widget.EditText
import android.widget.TextView
import java.util.*


class Logic {

    private val stringList = mutableListOf<String>()
    private val sort = Sort()


    fun addString(textView: TextView, editText: EditText) {

        val str = editText.text.toString()
        stringList.add(str)
        textView.text = stringList.joinToString("\n", "", "")

    }

    //вышло страшно, пока не знаю как упростить и убрать конкатенацию
    fun sort(textView: TextView) {

        var sortedListWithTime = "Start sorting: " + getTime()
        //textView.text = getTime().toString()
        val sortedList = sort.mergeSort(stringList)
        sortedListWithTime += sortedList.joinToString("\n", "", "\n")
        //textView.text = sortedList.joinToString("\n", "", "")
        sortedListWithTime += "End sorting: " + getTime()
        textView.text = sortedListWithTime

    }



    fun getTime() : String {
        val currentDate = Date()
        val timeFormat: DateFormat = SimpleDateFormat("HH:mm:ss.SSS", Locale.getDefault())
        return timeFormat.format(currentDate) + "\n"
    }
}