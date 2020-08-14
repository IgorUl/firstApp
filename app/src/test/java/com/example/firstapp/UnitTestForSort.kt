package com.example.firstapp

import com.example.firstapp.Data.CommentSorter
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class UnitTestForSort {

    @Test
    fun testMergeSorting() {
        val list: MutableList<String> =
            mutableListOf("1", "two", " ", " 2!0", "check", "solo", "UP", "UpFirst", "$%#!@")
        val sortedList: List<String> = CommentSorter().getMergeSortedList(list)
        val expected: MutableList<String> =
            mutableListOf(" ", " 2!0", "$%#!@", "1", "check", "solo", "two", "UP", "UpFirst")
        assertEquals("MergeSort - NOT OK", expected, sortedList)
    }

    @Test
    fun testMergeSortingEmptyString() {
        val list: MutableList<String> =
            mutableListOf("")
        val sortedList: List<String> = CommentSorter().getMergeSortedList(list)
        val expected: MutableList<String> =
            mutableListOf("")
        assertEquals("MergeSort - NOT OK", expected, sortedList)
    }

    @Test
    fun testBubbleSorting() {
        val list: MutableList<String> =
            mutableListOf("1", "two", " ", " 2!0", "check", "solo", "UP", "UpFirst", "$%#!@")
        val sortedList: List<String> = CommentSorter().getBubbleSortedList(list)
        val expected: MutableList<String> =
            mutableListOf(" ", " 2!0", "$%#!@", "1", "check", "solo", "two", "UP", "UpFirst")
        assertEquals("BubbleSort - NOT OK", expected, sortedList)
    }
}
