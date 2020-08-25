package com.example.firstapp.data

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import org.junit.Test
import org.mockito.Mockito.`when`

class UnitTestModel {

    private val commentHolder: CommentHolder = mock()
    private val commentSorter: CommentSorter = mock()
    private val fileStorage: FileStorage = mock()
    private val timeProvider: TimeProvider = mock()

    private val model: Model = Model(commentHolder,commentSorter, fileStorage, timeProvider)

    private val expectedList: MutableList<String> = mutableListOf()

    @Test
    fun getAllComment() {
        `when`(commentHolder.getInputStringList).thenReturn(emptyList())

        model.getAllComment()

        val result: List<String> = commentHolder.getInputStringList

        assert(expectedList == result)
    }

    @Test
    fun clearStringList() {
        model.clearStringList()

        verify(commentHolder).clearStringList()
    }

    @Test
    fun isListCanSort_EmptyList() {
        `when`(commentHolder.getInputStringList).thenReturn(emptyList())

        val expected = false
        val result:Boolean = model.isListCanSort()

        assert(expected == result)
    }

    @Test
    fun isListCanSort_OneElem() {
        `when`(commentHolder.getInputStringList).thenReturn(listOf("test"))

        val expected = false
        val result:Boolean = model.isListCanSort()

        assert(expected == result)
    }

    @Test
    fun isListCanSort_TwoElem() {
        `when`(commentHolder.getInputStringList).thenReturn(listOf("0", "1"))

        val expected = true
        val result:Boolean = model.isListCanSort()

        assert(expected == result)
    }
}