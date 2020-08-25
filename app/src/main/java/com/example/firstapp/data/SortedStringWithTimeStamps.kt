package com.example.firstapp.data

class SortedStringWithTimeStamps(
    var startTime: Long,
    var sortedString: String,
    var endTime: Long
) {
    var timeDifference: Long = endTime - startTime
}
