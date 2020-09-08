package com.example.firstapp.data

import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Test

class CommentHolderUnitTest {

    private var expectedList: List<String> =
        listOf("test")
    private val expectedEmptyListSize = 0
    private val expectedNotEmptyListSize = 1
    private val commentHolder = CommentHolder()


    @After
    fun tearDown() {
        commentHolder.clearStringList()
    }

    @Test
    fun addStringToList_EmptyList_addOneElem() {

        commentHolder.addStringToList("test")

        assertEquals(expectedList, commentHolder.getInputStringList)
    }

    @Test
    fun addStringToList_NotEmptyList_addOneElem() {
        val expectedList: List<String> = listOf("test", "2")
        commentHolder.addStringToList("test")

        commentHolder.addStringToList("2")

        assertEquals(expectedList, commentHolder.getInputStringList)
    }

    @Test
    fun clearStringList_NotEmptyList_clearList() {
        commentHolder.addStringToList("test")
        commentHolder.addStringToList("2")

        if (commentHolder.getInputStringList.size == 2) {
            commentHolder.clearStringList()
            assertEquals(emptyList<String>(), commentHolder.getInputStringList)
        } else {
            assert(false)
        }
    }

    @Test
    fun clearStringList_EmptyList_ClearList() {
        commentHolder.clearStringList()

        assertEquals(emptyList<String>(), commentHolder.getInputStringList)
    }

    @Test
    fun addFromFileToList_addEmptyList_emptyList() {
        commentHolder.addFromFileToList(emptyList())

        val result:List<String> = commentHolder.getInputStringList

        assertEquals(emptyList<String>(), result)
    }

    @Test
    fun addFromFileToList_addNotEmptyList_addAllElem() {
        commentHolder.addFromFileToList(expectedList)

        val result:List<String> = commentHolder.getInputStringList

        assertEquals(expectedList, result)
    }

    @Test
    fun addRandomCommentsToList_EmptyListAddTenComments_addTenElem() {
        val expectedListSize = 10

        commentHolder.addRandomCommentsToList(10)
        assertEquals(expectedListSize, commentHolder.getInputStringList.size)
    }

    @Test
    fun addRandomCommentsToList_NotEmptyListAddTenComments_addTenElem() {
        commentHolder.addStringToList("test")
        val expectedListSize = 11

        commentHolder.addRandomCommentsToList(10)
        assertEquals(expectedListSize, commentHolder.getInputStringList.size)
    }

    @Test
    fun addRandomCommentsToList_EmptyListAddZeroComments_nothingAddToList() {
        val expectedListSize = 0
        commentHolder.addRandomCommentsToList(0)

        assertEquals(expectedListSize, commentHolder.getInputStringList.size)

    }

    @Test
    fun addRandomCommentsToList_NotEmptyListAddZeroComments_nothingAddToList() {
        val expectedListSize = 1
        commentHolder.addStringToList("test")

        commentHolder.addRandomCommentsToList(0)

        assertEquals(expectedListSize, commentHolder.getInputStringList.size)
    }

    @Test
    fun addRandomCommentsToList_EmptyListAddNegative_nothingAddToList() {
        commentHolder.addRandomCommentsToList(-4)

        assertEquals(expectedEmptyListSize, commentHolder.getInputStringList.size)
    }

    @Test
    fun addRandomCommentsToList_NotEmptyListAddNegative_nothingAddToList() {
        commentHolder.addStringToList("test")

        commentHolder.addRandomCommentsToList(-4)

        assertEquals(expectedNotEmptyListSize, commentHolder.getInputStringList.size)
    }
}