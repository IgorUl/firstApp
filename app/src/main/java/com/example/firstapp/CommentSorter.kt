package com.example.firstapp

import java.util.*

class CommentSorter {

    fun getMergeSortedList(list: List<String>): List<String> {
        if (list.size <= 1) {
            return list
        }

        val middle: Int = list.size / 2
        val leftHalf: List<String> = list.subList(0, middle)
        val rightHalf: List<String> = list.subList(middle, list.size)

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
            } else {
                newList.add(right[indexRight])
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
        var i: Int = index
        while (i < list.size) {
            newList.add(list[i])
            i++
        }
    }

    fun getBubbleSortedList(list: List<String>): List<String> {
        var goodPairCounter = 0
        var i = 0
        val newList: MutableList<String> = mutableListOf()
        newList.addAll(list)

        while (true) {
            if (newList[i] > newList[i + 1]) {
                swapStrings(newList, i)
                goodPairCounter = 0
            } else {
                goodPairCounter++
            }
            i++
            if (i == newList.size - 1) {
                i = 0
            }
            if (goodPairCounter == newList.size - 1) {
                break
            }
        }
        return newList
    }

    private fun swapStrings(list: MutableList<String>, index: Int) {
        val tmp: String = list[index]
        list[index] = list[index + 1]
        list[index + 1] = tmp
    }
}