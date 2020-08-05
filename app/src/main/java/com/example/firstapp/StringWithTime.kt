package com.example.firstapp

import android.icu.text.DateFormat
import android.icu.text.SimpleDateFormat
import java.util.*


class StringWithTime {

    val inputStringList = mutableListOf<String>()
    private val sortingTypes = SortingTypes()
    var isSorted: Boolean = false
        private set

    fun addStringToList(inputString: String) {
        inputStringList.add(inputString)
        isSorted = false
    }

    fun getSortedStringWithTime(string: String): String {
        val startSortingTime = getTime()
        val sortedString = getSortedString(string)
        val endSortingTime = getTime()
        return "Start sorting: $startSortingTime" +
                "$sortedString\n" +
                "End sorting: $endSortingTime" +
                "Time in millis: " + getDateDifference(endSortingTime, startSortingTime)
    }

    private fun getSortedString(string: String): String {
        val sortedList: List<String> = if (string == "bubble") {
            sortingTypes.getBubbleSortingList(inputStringList)
        } else {
            sortingTypes.getMergeSortingList(inputStringList)
        }
        isSorted = true
        return sortedList.joinToString("\n", "", "")
    }

    private fun getTime(): String {
        val currentDate = Date()
        val timeFormat: DateFormat = SimpleDateFormat("HH:mm:ss.SSS", Locale.getDefault())
        return timeFormat.format(currentDate) + "\n"
    }

    private fun getDateDifference(date1: String, date2: String): Long =
        SimpleDateFormat("HH:mm:ss.SSS").parse(date1).time - SimpleDateFormat("HH:mm:ss.SSS").parse(
            date2
        ).time
}
