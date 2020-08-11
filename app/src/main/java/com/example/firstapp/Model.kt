package com.example.firstapp

import android.icu.text.DateFormat
import android.icu.text.SimpleDateFormat
import java.util.*


class Model {

    private val stringHolder = StringHolder("")
    private val commentSorter = CommentSorter()
    lateinit var startSortingTime: Date
    lateinit var sortedString: String
    lateinit var endSortingTime: Date


    fun addToList(string: String) {
        stringHolder.addStringToList(string)
    }

    fun isListEmpty(): Boolean =
        stringHolder.getInputStringList.isEmpty()

    fun isListSorted(): Boolean = !stringHolder.isSorted && stringHolder.getInputStringList.size > 1

    fun getInputString(): String = stringHolder.getInputStringList.joinToString("\n", "", "")

    fun clearStringList() {
        stringHolder.clearStringList()
    }

    fun initTimeStamps(sortType: SortType) {
        startSortingTime = getCurrentTime()
        sortedString = getSortedString(sortType)
        endSortingTime = getCurrentTime()
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

    fun parseDateToString(date: Date): String {
        val timeFormat: DateFormat = SimpleDateFormat("HH:mm:ss.SSS", Locale.getDefault())
        return timeFormat.format(date)
    }

    fun getDateDifference(endSortingTime: Date, startSortingTime: Date): String =
        (endSortingTime.time - startSortingTime.time).toString()
}