package com.example.firstapp


import android.icu.text.DateFormat
import android.icu.text.SimpleDateFormat
import android.widget.EditText
import android.widget.RadioButton
import android.widget.TextView
import java.util.*


class Logic {

    val stringList = mutableListOf<String>()
    private val sort = Sort()
    var isSort: Boolean = false
    private set



    fun addString(textView: TextView, editText: EditText) {

        val str = editText.text.toString()
        stringList.add(str)
        textView.text = stringList.joinToString("\n", "", "")
        editText.setText("")
        isSort = false
    }

    //вышло страшно, пока не знаю как упростить и убрать конкатенацию
    fun sort(textView: TextView, radioButton: RadioButton) {
        val timeStart = getTime()
        var sortedListWithTime = "Start sorting: $timeStart"
        val sortedList: List<String> = if (radioButton.id == R.id.bubbleSortRadioButton) {
            sort.bubbleSort(stringList)
        } else {
            sort.mergeSort(stringList)
        }
        isSort = true
        sortedListWithTime += sortedList.joinToString("\n", "", "\n")
        val timeFinish = getTime()
        sortedListWithTime += "End sorting: $timeFinish"
        sortedListWithTime += "Time in millis: " + dateDifference(timeFinish, timeStart)
        textView.text = sortedListWithTime
    }


    private fun getTime(): String {
        val currentDate = Date()
        val timeFormat: DateFormat = SimpleDateFormat("HH:mm:ss.SSS", Locale.getDefault())
        return timeFormat.format(currentDate) + "\n"
    }


    private fun dateDifference(date1: String, date2: String): Long =
        SimpleDateFormat("HH:mm:ss.SSS").parse(date1).time - SimpleDateFormat("HH:mm:ss.SSS").parse(
            date2
        ).time
}
