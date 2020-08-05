package com.example.firstapp

import java.util.*

class StringSorter {


    fun getMergeSortedList(list: List<String>): List<String> {
        if (list.size <= 1) {
            return list
        }

        val middle = list.size / 2
        val leftHalf = list.subList(0, middle)
        val rightHalf = list.subList(middle, list.size)

        return getMergeList(getMergeSortedList(leftHalf), getMergeSortedList(rightHalf))
    }

    private fun getMergeList(left: List<String>, right: List<String>): List<String> {
        var indexLeft = 0
        var indexRight = 0
        val newList: MutableList<String> = mutableListOf()

        while (indexLeft < left.count() && indexRight < right.count()) {
            if (isAlphabetOrder(left, right, indexLeft, indexRight)) {
                newList.add(left[indexLeft])
                indexLeft++
                addToNewList(left, newList, indexLeft)
            } else {
                newList.add(right[indexRight]) //TODO вынести одинаковый код // я не знаю как это сделать
                indexRight++

            }
        }
        addToNewList(left, newList, indexLeft)
        addToNewList(right, newList, indexRight)
        return newList
    }

    private fun isAlphabetOrder(
        left: List<String>,
        right: List<String>,
        indexLeft: Int,
        indexRight: Int
    ): Boolean =
        left[indexLeft].toLowerCase(Locale.getDefault()) <= right[indexRight].toLowerCase(Locale.getDefault())

    private fun addToNewList(list: List<String>, newList: MutableList<String>, index: Int) {
        var i = index
        while (i < list.size) {
            newList.add(list[i])
            i++
        }
    }

    fun getBubbleSortedList(list: MutableList<String>): List<String> {
        var goodPairCounter = 0
        var i = 0

        while (true) {
            if (list[i] > list[i + 1]) {
                swapStrings(list, i)
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

    private fun swapStrings(list: MutableList<String>, index: Int) {
        val tmp = list[index]
        list[index] = list[index + 1]
        list[index + 1] = tmp
    }

}