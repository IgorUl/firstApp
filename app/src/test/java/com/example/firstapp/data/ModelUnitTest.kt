package com.example.firstapp.data

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import org.junit.After
import org.junit.Assert.*
import org.junit.Test
import org.mockito.Mockito.*

class ModelUnitTest {

    private val commentHolder: CommentHolder = mock()
    private val commentSorter: CommentSorter = mock()
    private val fileStorage: FileStorage = mock()
    private val timeProvider: TimeProvider = mock()

    private val model: Model = Model(commentHolder, commentSorter, fileStorage, timeProvider)

    private var expectedList: MutableList<String> = mutableListOf()

    @After
    fun tearDown() {
        expectedList.clear()
    }


    @Test
    fun getAllComments_returnEmptyList() {
        `when`(commentHolder.getInputStringList).thenReturn(emptyList())

        val result: List<String> = commentHolder.getInputStringList

        assertEquals("getAllComments_returnEmptyList - NOT OK", expectedList, result)
    }

    @Test
    fun getAllComments_returnNotEmptyList() {
        `when`(commentHolder.getInputStringList).thenReturn(listOf("test", "2"))
        val expectedList: List<String> = listOf("test", "2")

        val result: List<String> = commentHolder.getInputStringList

        assertEquals("getAllComments_returnNotEmptyList - NOT OK", expectedList, result)
    }

    @Test
    fun getAllComments_ReturnEmptyString_WithSeparator() {
        `when`(commentHolder.getInputStringList).thenReturn(emptyList())

        val result: String = model.getAllComment()

        val expected = ""
        assertEquals(
            "getAllComments_ReturnEmptyString_WithSeparator - NOT OK",
            expected,
            result
        )
    }

    @Test
    fun getAllComments_ReturnNotEmptyString_WithSeparator() {
        `when`(commentHolder.getInputStringList).thenReturn(listOf("test", "2"))
        expectedList.add("test")
        expectedList.add("2")

        val result: String = model.getAllComment()

        assertEquals(
            "getAllComments_ReturnNotEmptyString_WithSeparator - NOT OK",
            expectedList.joinToString("\n"), // "test\n2"
            result
        )
    }

    @Test
    fun addToList_checkSameArgs() {
        model.addToList("test")

        verify(commentHolder).addStringToList("test")
    }

    @Test
    fun isListEmpty_EmptyList_returnTrue() {
        `when`(commentHolder.getInputStringList).thenReturn(emptyList())

        assert(model.isListEmpty())
    }

    @Test
    fun isListEmpty_NotEmptyList_returnFalse() {
        `when`(commentHolder.getInputStringList).thenReturn(listOf("test", "2"))

        assert(!model.isListEmpty())
    }

    @Test
    fun isListCanSort_EmptyList() {
        `when`(commentHolder.getInputStringList).thenReturn(emptyList())

        val expected = false
        val result: Boolean = model.isListCanSort()

        assert(expected == result)
    }

    @Test
    fun isListCanSort_OneElem_false() {
        `when`(commentHolder.getInputStringList).thenReturn(listOf("test"))

        val expected = false
        val result: Boolean = model.isListCanSort()

        assert(expected == result)
    }

    @Test
    fun isListCanSort_TwoElem_true() {
        `when`(commentHolder.getInputStringList).thenReturn(listOf("0", "1"))

        val expected = true
        val result: Boolean = model.isListCanSort()

        assert(expected == result)
    }

    @Test
    fun clearStringList_invokeCommentHolderClearStringList() {
        model.clearStringList()

        verify(commentHolder).clearStringList()
    }

    @Test
    fun getSortedString_MergeSortTypeEmptyList_invokeMergeSort() {
        `when`(commentHolder.getInputStringList).thenReturn(emptyList())

        model.getSortedString(SortType.MERGE)

        verify(commentSorter, times(1)).getMergeSortedList(commentHolder.getInputStringList)
        verify(commentSorter, never()).getBubbleSortedList(commentHolder.getInputStringList)
    }

    @Test
    fun getSortedString_MergeSortTypeNotEmptyList_invokeMergeSort() {
        `when`(commentHolder.getInputStringList).thenReturn(listOf("test", "2"))

        model.getSortedString(SortType.MERGE)

        verify(commentSorter, times(1)).getMergeSortedList(commentHolder.getInputStringList)
        verify(commentSorter, never()).getBubbleSortedList(commentHolder.getInputStringList)
    }

    @Test
    fun getSortedString_BubbleSortTypeEmptyList_invokeBubbleSort() {
        `when`(commentHolder.getInputStringList).thenReturn(emptyList())

        model.getSortedString(SortType.BUBBLE)

        verify(commentSorter, never()).getMergeSortedList(commentHolder.getInputStringList)
        verify(commentSorter, times(1)).getBubbleSortedList(commentHolder.getInputStringList)
    }

    @Test
    fun getSortedString_BubbleSortTypeNotEmptyList_invokeBubbleSort() {
        `when`(commentHolder.getInputStringList).thenReturn(listOf("test", "2"))

        model.getSortedString(SortType.BUBBLE)

        verify(commentSorter, never()).getMergeSortedList(commentHolder.getInputStringList)
        verify(commentSorter, times(1)).getBubbleSortedList(commentHolder.getInputStringList)
    }

    @Test
    fun getSortedString_EmptyListMergeSort_returnEmptyString() {
        `when`(commentHolder.getInputStringList).thenReturn(emptyList())

        val result: String = model.getSortedString(SortType.MERGE)

        val expected = ""
        assertEquals(expected, result)
    }

    @Test
    fun getSortedString_NotEmptyListMergeSort_returnStringWithSeparator() {
        `when`(commentSorter.getMergeSortedList(anyList())).thenReturn(
            listOf(
                "test",
                "2"
            )
        )

        val result: String = model.getSortedString(SortType.MERGE)

        val expectedResult = "test\n2"
        assertEquals(expectedResult, result)
    }

    @Test
    fun getSortedString_EmptyListBubbleSort_returnEmptyString() {
        `when`(commentHolder.getInputStringList).thenReturn(emptyList())

        val result: String = model.getSortedString(SortType.BUBBLE)
        val expected = ""

        assertEquals(expected, result)
    }

    @Test
    fun getSortedString_NotEmptyListBubbleSort_returnStringWithSeparator() {
        `when`(commentSorter.getBubbleSortedList(anyList())).thenReturn(
            listOf(
                "test",
                "2"
            )
        )
        val expected = "test\n2"

        val result: String = model.getSortedString(SortType.BUBBLE)

        assertEquals(expected, result)
    }

    @Test
    fun initSortData_MergeSort_returnObjectWithArgs() {
        val startTime: Long = 1
        val endTime: Long = 1
        `when`(model.getCurrentTime()).thenReturn(startTime)
        `when`(commentSorter.getMergeSortedList(anyList())).thenReturn(listOf("test"))

        val testObj = SortedStringWithTimeStamps(startTime, "test", endTime)

        val resultObj: SortedStringWithTimeStamps = model.initSortData()

        assert(testObj == resultObj)
    }

    @Test
    fun initSortData_MergeSort_returnObjectWithDiffArgs() {
        val startTime: Long = 2
        val endTime: Long = 4
        `when`(model.getCurrentTime()).thenReturn(startTime).thenReturn(1)
        `when`(commentSorter.getMergeSortedList(anyList())).thenReturn(listOf("test"))

        val testObj = SortedStringWithTimeStamps(startTime, "test", endTime)

        val resultObj: SortedStringWithTimeStamps = model.initSortData()

        assert(testObj == resultObj)
    }

    @Test
    fun initSortData_MergeSort_invokeGetTime2times() {
        model.initSortData()

        verify(timeProvider, times(2)).getTime()
    }

    @Test
    fun readFile_invokeReadFile() {
        model.readFile()

        verify(fileStorage).readFile()
    }

    @Test
    fun readFile_emptyFile_addEmptyList() {
        val expected = ""
        `when`(fileStorage.readFile()).thenReturn(emptyList())

        model.readFile()

        val result:String = model.getAllComment()

        assertEquals(expected, result)
    }

    @Test
    fun writeFile_EmptyString_returnFalse() {
        `when`(commentHolder.getInputStringList).thenReturn(emptyList())

        val result: Boolean = model.writeFile()

        assert(!result)
    }

    @Test
    fun addListFromFile_invokeAddFromFileToList() {
        model.addListFromFile(anyList())

        verify(commentHolder).addFromFileToList(anyList())
    }

    @Test
    fun addListFromFile_invokeAddFromFileToListWithSameArgs() {
        val expected:List<String> = listOf("test", "2")
        model.addListFromFile(expected)

        verify(commentHolder).addFromFileToList(expected)
    }
}