package com.example.firstapp

import android.icu.text.DateFormat
import android.icu.text.SimpleDateFormat
import java.util.*

class CommentSorterWithTimeStamp {

    val result: List<String>
        get() = inputStringList

    private val inputStringList = mutableListOf<String>()

    private val commentSorter = CommentSorter()

    var isSorted: Boolean = false
        private set

    fun clearStringList() {
        inputStringList.clear()
    }


    fun addStringToList(inputString: String) {
        inputStringList.add(inputString)
        isSorted = false
    }

    fun getSortedStringWithTime(sortType: SortType): String {
        val startSortingTime: Date = getCurrentTime()
        val sortedString: String = getSortedString(sortType)
        val endSortingTime: Date = getCurrentTime()
        return "Start sorting: ${parseDateToString(startSortingTime)}" + /// todo extract in res
                "$sortedString\n" +
                "End sorting: ${parseDateToString(endSortingTime)}" +
                "Time in millis: " + getDateDifference(endSortingTime, startSortingTime)
    }

    private fun getSortedString(sortType: SortType): String {
        val sortedList: List<String> = when (sortType) {
            SortType.BUBBLE ->
                commentSorter.getBubbleSortedList(result)
            else ->
                commentSorter.getMergeSortedList(result)
        }
        isSorted = true
        return sortedList.joinToString("\n", "", "")
    }

    private fun getCurrentTime(): Date = Date(System.currentTimeMillis() + 25*24*60*60*1000L)


    private fun parseDateToString(date: Date): String {
        val timeFormat: DateFormat = SimpleDateFormat("HH:mm:ss.SSS", Locale.getDefault())
        return timeFormat.format(date) + "\n"
    }

    private fun getDateDifference(startSortingTime: Date, endSortingTime: Date): Long =
        startSortingTime.time - endSortingTime.time

}
