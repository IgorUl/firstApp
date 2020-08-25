package com.example.firstapp.data

import org.junit.Test

import org.junit.Assert.*

class UnitTestCommentSorter {
    private val list: MutableList<String> =
        mutableListOf("1", "two", " ", " 2!0", "check", "solo", "UP", "UpFirst", "$%#!@")
    private val expectedList: MutableList<String> =
        mutableListOf(" ", " 2!0", "$%#!@", "1", "check", "solo", "two", "UP", "UpFirst")
    private val emptyList: MutableList<String> =
        mutableListOf("")
    private val expectedEmptyList: MutableList<String> =
        mutableListOf("")

    @Test
    fun getMergeSortedList_nonEmptyList_matchesExpected() {
        val sortedList: List<String> = CommentSorter().getMergeSortedList(list)
        assertEquals("MergeSort with non empty list - NOT OK", expectedList, sortedList)
    }

    @Test
    fun testMergeSorting_EmptyList_matchesExpected() {
        val sortedList: List<String> = CommentSorter().getMergeSortedList(emptyList)
        assertEquals("MergeSort Empty list - NOT OK", expectedEmptyList, sortedList)
    }

    @Test
    fun testMergeSorting_NoNull() {
        val sortedList: List<String> = CommentSorter().getMergeSortedList(list)
        assertNotNull(sortedList)
    }

    @Test
    fun testBubbleSorting_nonEmptyList_matchesExpected() {
        val sortedList: List<String> = CommentSorter().getBubbleSortedList(list)
        assertEquals("BubbleSort with non empty list - NOT OK", expectedList, sortedList)
    }

    @Test
    fun testBubbleSorting_EmptyList_matchesExpected() {
        val sortedList: List<String> = CommentSorter().getBubbleSortedList(emptyList)
        assertEquals("BubbleSort with empty list - NOT OK", expectedEmptyList, sortedList)
    }

    @Test
    fun testBubbleSortingNoNull() {
        val sortedList: List<String> = CommentSorter().getBubbleSortedList(list)
        assertNotNull(sortedList)
    }
}
