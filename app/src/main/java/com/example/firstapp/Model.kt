package com.example.firstapp

import android.icu.text.DateFormat
import android.icu.text.SimpleDateFormat
import java.util.*

class Model {

    private val stringHolder = StringHolder("")
    private val commentSorter = CommentSorter()

    fun addToList(string: String) {
        stringHolder.addStringToList(string)
    }

    fun isListEmpty(): Boolean =
        stringHolder.getInputStringList.isEmpty()

    fun isListSorted(): Boolean = !stringHolder.isSorted && stringHolder.getInputStringList.size > 1

    fun getInputString(): String = stringHolder.getInputStringList.joinToString("\n", "", "")

    fun getStringSortingBy(sortType: SortType): String =
        getSortedStringWithTime(sortType)

    fun clearStringList() {
        stringHolder.clearStringList()
    }


    private fun getSortedStringWithTime(sortType: SortType): String {
        val startSortingTime: Date = getCurrentTime()
        val sortedString: String = getSortedString(sortType)
        val endSortingTime: Date =
            getCurrentTime()
        return "Start sorting: ${parseDateToString(startSortingTime)}" + /// todo extract in res
                "$sortedString\n" +
                "End sorting: ${parseDateToString(endSortingTime)}" +
                "Time in millis: " + getDateDifference(endSortingTime, startSortingTime)
    }

    private fun getSortedString(sortType: SortType): String {
        val sortedList: List<String> = when (sortType) {
            SortType.BUBBLE ->
                commentSorter.getBubbleSortedList(stringHolder.getInputStringList)
            else ->
                commentSorter.getMergeSortedList(stringHolder.getInputStringList)
        }
        stringHolder.isSorted = true
        return sortedList.joinToString("\n", "", "")
    }

    private fun getCurrentTime(): Date = Date(System.currentTimeMillis())


    private fun parseDateToString(date: Date): String {
        val timeFormat: DateFormat = SimpleDateFormat("HH:mm:ss.SSS", Locale.getDefault())
        return timeFormat.format(date) + "\n"
    }

    private fun getDateDifference(endSortingTime: Date, startSortingTime: Date): Long =
        endSortingTime.time - startSortingTime.time

}