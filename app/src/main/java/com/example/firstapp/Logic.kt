package com.example.firstapp

class Logic {


    fun mergeSort(list: List<String>): List<String> {
        if (list.size <= 1) {
            return list
        }

        val middle = list.size / 2
        val left = list.subList(0,middle)
        val right = list.subList(middle,list.size)

        return merge(mergeSort(left), mergeSort(right))
    }
    fun merge(left: List<String>, right: List<String>): List<String>  {
        var indexLeft = 0
        var indexRight = 0
        val newList : MutableList<String> = mutableListOf()

        while (indexLeft < left.count() && indexRight < right.count()) {
            if (left[indexLeft] <= right[indexRight]) {
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
}