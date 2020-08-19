package com.example.firstapp.data

import android.content.res.Resources
import android.icu.text.DateFormat
import android.icu.text.SimpleDateFormat
import com.example.firstapp.R
import java.util.*

class Model {

    private val commentHolder = CommentHolder()
    private val commentSorter = CommentSorter()
    private var sortType: SortType = SortType.MERGE

    fun getComment(): String =
        commentHolder.getInputStringList.joinToString("\n")

    fun addToList(comment: String) =
        commentHolder.addStringToList(comment)

    fun isListEmpty(): Boolean =
        commentHolder.getInputStringList.isEmpty()

    fun isListSorted(): Boolean =
        !commentHolder.isSorted && commentHolder.getInputStringList.size > MIN_SORTED_LIST_SIZE

    fun clearStringList() =
        commentHolder.clearStringList()

    fun setSortType(sortType: SortType) {
        this.sortType = sortType
    }

    fun generateComments() {
        commentHolder.addTenRandomCommentToList()
    }

    fun checkFile() {
        commentHolder.addFromFileToList()
    }

    private fun getSortedString(sortType: SortType): String {
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

    private fun parseDateToString(date: Date): String {
        val timeFormat: DateFormat = SimpleDateFormat(TIME_PATTERN, Locale.getDefault())
        return timeFormat.format(date)
    }

    fun getDateDifference(endSortingTime: Long, startSortingTime: Long): Long =
        endSortingTime - startSortingTime

    fun getSortedStringWithTimeStamps(resources: Resources): String {

        val startSortingTime: Long = getCurrentTime()
        val sortedString: String = getSortedString(sortType)
        val endSortingTime: Long = getCurrentTime()
        return resources.getString(
            R.string.sortedStringWithTimeStamps,
            parseDateToString(Date(startSortingTime)),
            sortedString,
            parseDateToString(Date(endSortingTime)),
            getDateDifference(endSortingTime, startSortingTime).toString()
        )
    }

    companion object {
        private const val MIN_SORTED_LIST_SIZE = 1
        private const val TIME_PATTERN = "HH:mm:ss.SSS"
    }
}
