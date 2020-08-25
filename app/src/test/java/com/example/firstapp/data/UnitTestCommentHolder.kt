package com.example.firstapp.data

import org.junit.Assert.assertEquals
import org.junit.Test

class UnitTestCommentHolder {

    private val expectedList: MutableList<String> =
        mutableListOf("test")
    private val commentHolder = CommentHolder()

    @Test
    fun addStringToList() {
        commentHolder.addStringToList("test")

        assertEquals(expectedList, commentHolder.getInputStringList)
    }

    @Test
    fun clearStringList() {
        commentHolder.clearStringList()
        expectedList.clear()

        assertEquals(expectedList, commentHolder.getInputStringList)
    }

    @Test
    fun addRandomCommentToList() {
        val expectedListSize = 10

        commentHolder.addRandomCommentToList(10)
        assertEquals(expectedListSize, commentHolder.getInputStringList.size)
    }


}