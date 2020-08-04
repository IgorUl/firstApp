package com.example.firstapp

import java.util.*

class Sort {


    fun mergeSort(list: List<String>): List<String> {
        if (list.size <= 1) {
            return list
        }

        val middle = list.size / 2
        val left = list.subList(0, middle)
        val right = list.subList(middle, list.size)

        return merge(mergeSort(left), mergeSort(right))
    }

    fun merge(left: List<String>, right: List<String>): List<String> {
        var indexLeft = 0
        var indexRight = 0
        val newList: MutableList<String> = mutableListOf()

        while (indexLeft < left.count() && indexRight < right.count()) {
            if (left[indexLeft].toLowerCase(Locale.ROOT) <= right[indexRight].toLowerCase(Locale.ROOT)) {
                newList.add(left[indexLeft])
                indexLeft++
            } else {
                newList.add(right[indexRight])
                indexRight++
            }
        }

        while (indexLeft < left.size) {
            newList.add(left[indexLeft])
            indexLeft++
        }

        while (indexRight < right.size) {
            newList.add(right[indexRight])
            indexRight++
        }
        return newList
    }

    fun bubbleSort(list: MutableList<String>): List<String> {
        var goodPairCounter = 0
        var i = 0

        while (true) {
            if (list[i] > list[i + 1]) {
                val tmp = list[i]
                list[i] = list[i + 1]
                list[i + 1] = tmp
                goodPairCounter = 0
            } else {
                goodPairCounter++
            }
            i++
            if (i == list.size - 1) {
                i = 0
            }
            if (goodPairCounter == list.size - 1) {
                break
            }
        }
        return list
    }

}