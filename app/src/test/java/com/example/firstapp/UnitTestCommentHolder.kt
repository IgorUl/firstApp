package com.example.firstapp

import com.example.firstapp.data.CommentHolder
import junit.framework.Assert.assertEquals
import org.junit.Test

class UnitTestCommentHolder {

    private val expectedList: MutableList<String> =
        mutableListOf("test")
    private val commentHolder = CommentHolder()

    @Test
    fun testAddStringToList() {
        commentHolder.addStringToList("test")

        assertEquals(expectedList, commentHolder.getInputStringList)
    }

    @Test
    fun testClearStringList() {
        commentHolder.clearStringList()
        expectedList.clear()

        assertEquals(expectedList, commentHolder.getInputStringList)
    }

    @Test
    fun testAddRandomCommentToList() {
        val expectedListSize = 10

        commentHolder.addTenRandomCommentToList()
        assertEquals(expectedListSize, commentHolder.getInputStringList.size)
    }
}