package com.example.firstapp


import android.icu.text.DateFormat
import android.icu.text.SimpleDateFormat
import android.widget.EditText
import android.widget.TextView
import java.time.LocalDate
import java.time.Period
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
        val timeStart = getTime()
        var sortedListWithTime = "Start sorting: $timeStart"
        val sortedList = sort.mergeSort(stringList)
        sortedListWithTime += sortedList.joinToString("\n", "", "\n")
        val timeFinish = getTime()
        sortedListWithTime += "End sorting: $timeFinish"
        sortedListWithTime += "Time in millis: " + dateDifference(timeFinish, timeStart)
        textView.text = sortedListWithTime
    }



    fun getTime() : String {
        val currentDate = Date()
        val timeFormat: DateFormat = SimpleDateFormat("HH:mm:ss.SSS", Locale.getDefault())
        return timeFormat.format(currentDate) + "\n"
    }


    fun dateDifference(date1: String, date2: String) : Long =
        SimpleDateFormat("HH:mm:ss.SSS").parse(date1).time - SimpleDateFormat("HH:mm:ss.SSS").parse(date2).time
    }
