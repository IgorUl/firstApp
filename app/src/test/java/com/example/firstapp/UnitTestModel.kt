package com.example.firstapp

import com.example.firstapp.data.Model
import org.junit.Assert.*
import org.junit.Test

class UnitTestModel {

    private val model: Model = Model()

    @Test
    fun testAddToList() {
        model.addToList("test")
        assert(model.getComment().contains("test"))
    }


    @Test
    fun testDateDifference() {
        val start: Long = Model().getCurrentTime()
        val end:Long = Model().getCurrentTime()
        val difference: Boolean = Model().getDateDifference(end, start) >= 0
        assertEquals("DateDifference - NOT OK", true, difference)
    }
}