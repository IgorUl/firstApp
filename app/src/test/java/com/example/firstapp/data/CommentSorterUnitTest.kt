package com.example.firstapp.data

import org.junit.Test

import org.junit.Assert.*

class CommentSorterUnitTest {
    private val list: MutableList<String> =
        mutableListOf("1", "two", " ", " 2!0", "check", "solo", "UP", "UpFirst", "$%#!@")
    private val expectedList: MutableList<String> =
        mutableListOf(" ", " 2!0", "$%#!@", "1", "check", "solo", "two", "UP", "UpFirst")
    private val singleItemList: MutableList<String> =
        mutableListOf("single")
    private val expectedSingleItemList: MutableList<String> =
        mutableListOf("single")
    private val emptyList: List<String> = emptyList()

    private val commentSorter = CommentSorter()

    @Test
    fun getMergeSortedList_nonEmptyList_matchesExpected() {
        val sortedList: List<String> = commentSorter.getMergeSortedList(list)

        assertEquals("MergeSort with non empty list - NOT OK", expectedList, sortedList)
    }

    @Test
    fun getMergeSorting_singleItemList_matchesExpected() {
        val sortedList: List<String> = commentSorter.getMergeSortedList(singleItemList)

        assertEquals("MergeSort with single item list - NOT OK", expectedSingleItemList, sortedList)
    }

    @Test
    fun getMergeSorting_emptyList_matchesExpected() {
        val sorterList: List<String> = commentSorter.getMergeSortedList(emptyList)

        assertEquals("MergeSort empty list - NOT OK", emptyList<String>(), sorterList)
    }

    @Test
    fun getBubbleSortedList_nonEmptyList_matchesExpected() {
        val sortedList: List<String> = commentSorter.getBubbleSortedList(list)

        assertEquals("BubbleSort with non empty list - NOT OK", expectedList, sortedList)
    }

    @Test
    fun getBubbleSorting_SingleItemList_matchesExpected() {
        val sortedList: List<String> = commentSorter.getBubbleSortedList(singleItemList)

        assertEquals(
            "BubbleSort with single item list - NOT OK",
            expectedSingleItemList,
            sortedList
        )
    }

    @Test
    fun getBubbleSorting_EmptyList_matchesExpected() {
        val sortedList: List<String> = commentSorter.getBubbleSortedList(emptyList)

        assertEquals("BubbleSort with empty list - NOT OK", emptyList<String>(), sortedList)
    }
}
