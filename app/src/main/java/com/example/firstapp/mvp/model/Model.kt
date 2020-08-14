package com.example.firstapp.mvp.model

import android.icu.text.DateFormat
import android.icu.text.SimpleDateFormat
import com.example.firstapp.common.CommentHolder
import com.example.firstapp.common.CommentSorter
import com.example.firstapp.common.SortType
import java.util.*

class Model {

    private val commentHolder = CommentHolder()
    private val commentSorter = CommentSorter()

    fun getComment(): String = commentHolder.getInputStringList.joinToString("\n")

    fun addToList(comment: String) =
        commentHolder.addStringToList(comment)

    fun isListEmpty(): Boolean =
        commentHolder.getInputStringList.isEmpty()

    fun isListSorted(): Boolean =
        !commentHolder.isSorted && commentHolder.getInputStringList.size > MIN_SORTED_LIST_SIZE

    fun clearStringList() =
        commentHolder.clearStringList()

    fun getSortedString(sortType: SortType): String {
        val sortedList: List<String> = when (sortType) {
            SortType.BUBBLE ->
                commentSorter.getBubbleSortedList(commentHolder.getInputStringList)
            else ->
                commentSorter.getMergeSortedList(commentHolder.getInputStringList)
        }
        commentHolder.isSorted = true
        return sortedList.joinToString("\n")
    }

    fun getCurrentTime(): Long =
        System.currentTimeMillis()

    fun parseDateToString(date: Date): String {
        val timeFormat: DateFormat = SimpleDateFormat(TIME_PATTERN, Locale.getDefault())
        return timeFormat.format(date)
    }

    fun getDateDifference(endSortingTime: Long, startSortingTime: Long): Long =
        endSortingTime - startSortingTime

    companion object {
        private const val MIN_SORTED_LIST_SIZE = 1
        private const val TIME_PATTERN = "HH:mm:ss.SSS"
    }
}
