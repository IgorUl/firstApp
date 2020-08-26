package com.example.firstapp.data

import android.icu.text.DateFormat
import android.icu.text.SimpleDateFormat
import java.util.*

class TimeProvider {

    fun getTime(): Long =
        System.currentTimeMillis()

    fun parseDateToString(dateInMillis: Long): String {
        val date = Date(dateInMillis)
        val timeFormat: DateFormat = SimpleDateFormat(TIME_PATTERN, Locale.getDefault())
        return timeFormat.format(date)
    }

    companion object{
        private const val TIME_PATTERN = "HH:mm:ss.SSS"
    }
}