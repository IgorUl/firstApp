package com.example.firstapp.data

import com.example.firstapp.contracts.MainContract
import org.jetbrains.annotations.TestOnly

class Model(
    private val commentHolder: CommentHolder,
    private val commentSorter: CommentSorter,
    private val fileStorage: FileStorage,
    private val timeProvider: TimeProvider
) {

    private var sortType: SortType = SortType.MERGE
    private var onScreenChangeListener: MainContract.OnScreenChangeListener? = null

    fun initScreenListener(callback: MainContract.OnScreenChangeListener) {
        onScreenChangeListener = callback
    }

    fun updateSortView() {
        onScreenChangeListener?.onScreenChange()
    }

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

    fun generateComments(inputNumber: Int) =
        commentHolder.addRandomCommentsToList(inputNumber)


    @TestOnly
    fun getSortedString(sortType: SortType): String {
        val sortedList: List<String> = when (sortType) {
            SortType.BUBBLE ->
                commentSorter.getBubbleSortedList(commentHolder.getInputStringList)
            else ->
                commentSorter.getMergeSortedList(commentHolder.getInputStringList)
        }
        return sortedList.joinToString("\n")
    }

    @TestOnly
    fun getCurrentTime(): Long =
        timeProvider.getTime()

    fun initSortData(): SortedStringWithTimeStamps {
        val startTime: Long = getCurrentTime()
        val sortedResult: String = getSortedString(sortType)
        val endTime: Long = getCurrentTime()
        return SortedStringWithTimeStamps(startTime, sortedResult, endTime)
    }

    fun readFile() =
        addListFromFile(fileStorage.readFile())

    fun writeFile(): Boolean =
        fileStorage.writeFile(getAllComment())

    @TestOnly
    fun addListFromFile(list: List<String>) =
        commentHolder.addFromFileToList(list)


    companion object {
        private const val MIN_SORTED_LIST_SIZE = 1
    }
}
