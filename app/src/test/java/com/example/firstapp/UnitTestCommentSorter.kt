package com.example.firstapp

import com.example.firstapp.data.CommentSorter
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
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
    fun testMergeSorting() {
        val sortedList: List<String> = CommentSorter().getMergeSortedList(list)
        assertEquals("MergeSort - NOT OK", expectedList, sortedList)
    }

    @Test
    fun testMergeSortingEmptyString() {
        val sortedList: List<String> = CommentSorter().getMergeSortedList(emptyList)
        assertEquals("MergeSort - NOT OK", expectedEmptyList, sortedList)
    }

    @Test
    fun testMergeSortingNoNull() {
        val sortedList: List<String> = CommentSorter().getMergeSortedList(list)
        assertNotNull(sortedList)
    }

    @Test
    fun testBubbleSorting() {
        val sortedList: List<String> = CommentSorter().getBubbleSortedList(list)
        assertEquals("BubbleSort - NOT OK", expectedList, sortedList)
    }

    @Test
    fun testBubbleSortingEmptyString() {
        val sortedList: List<String> = CommentSorter().getBubbleSortedList(emptyList)
        assertEquals("MergeSort - NOT OK", expectedEmptyList, sortedList)
    }

    @Test
    fun testBubbleSortingNoNull() {
        val sortedList: List<String> = CommentSorter().getBubbleSortedList(list)
        assertNotNull(sortedList)
    }
}
