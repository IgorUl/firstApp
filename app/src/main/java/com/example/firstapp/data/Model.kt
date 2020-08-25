package com.example.firstapp.data

import android.icu.text.DateFormat
import android.icu.text.SimpleDateFormat
import java.util.*

class Model(
    private val commentHolder: CommentHolder,
    private val commentSorter: CommentSorter,
    private val fileStorage: FileStorage,
    private val timeProvider: TimeProvider
) {

    private var sortType: SortType = SortType.MERGE

    fun getAllComment(): String =
        commentHolder.getInputStringList.joinToString("\n")

    fun addToList(comment: String) =
        commentHolder.addStringToList(comment)

    fun isListEmpty(): Boolean =
        commentHolder.getInputStringList.isEmpty()

    fun isListCanSort(): Boolean =
        commentHolder.getInputStringList.size > MIN_SORTED_LIST_SIZE

    fun clearStringList() =
        commentHolder.clearStringList()

    fun setSortType(sortType: SortType) {
        this.sortType = sortType
    }

    fun generateComments(commentCount: Int) {
        commentHolder.addRandomCommentToList(commentCount)
    }

    private fun getSortedString(sortType: SortType): String {
        val sortedList: List<String> = when (sortType) {
            SortType.BUBBLE ->
                commentSorter.getBubbleSortedList(commentHolder.getInputStringList)
            else ->
                commentSorter.getMergeSortedList(commentHolder.getInputStringList)
        }
        return sortedList.joinToString("\n")
    }

    private fun getCurrentTime(): Long =
        timeProvider.getTime()

    fun parseDateToString(date: Date): String {
        val timeFormat: DateFormat = SimpleDateFormat(TIME_PATTERN, Locale.getDefault())
        return timeFormat.format(date)
    }

    fun getDateDifference(endSortingTime: Long, startSortingTime: Long): Long =
        endSortingTime - startSortingTime

    fun initSortData(): SortedStringWithTimeStamps =
        SortedStringWithTimeStamps(getCurrentTime(), getSortedString(sortType), getCurrentTime())

    fun readFile() =
        addListFromFile(fileStorage.readFile())

    fun writeFile(): Boolean =
        fileStorage.writeFile(getAllComment())

    private fun addListFromFile(list: List<String>) {
        commentHolder.addFromFileToList(list)
    }

    companion object {
        private const val MIN_SORTED_LIST_SIZE = 1
        private const val TIME_PATTERN = "HH:mm:ss.SSS"
    }
}
