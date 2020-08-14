package com.example.firstapp

import com.example.firstapp.mvp.model.Model
import org.junit.Assert.*
import org.junit.Test

class UnitTestModel {

    @Test
    fun testDateDifference() {

        val start: Long = Model().getCurrentTime()
        val end:Long = Model().getCurrentTime()
        val difference: Boolean = Model().getDateDifference(end, start) >= 0
        assertEquals("DateDifference - NOT OK", true, difference)
    }


}