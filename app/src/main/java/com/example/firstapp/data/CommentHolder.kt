package com.example.firstapp.data

import kotlin.random.Random

class CommentHolder {

    val getInputStringList: List<String>
        get() = inputStringList

    private var inputStringList: MutableList<String> = mutableListOf()
    var isSorted: Boolean = false

    fun addStringToList(inputString: String) {
        inputStringList.add(inputString)
        isSorted = false
    }

    fun clearStringList() {
        inputStringList.clear()
    }

    fun addFromFileToList(list: MutableList<String>) {
        inputStringList.clear()
        inputStringList.addAll(list)
    }

    private fun generateRandomComment(): String {
        val charPool: List<Char> = ('a'..'z') + ('A'..'Z') + ('!'..'/') + ('0'..'9')

        return (0..Random.nextInt(5, 20))
            .map { Random.nextInt(0, charPool.size) }
            .map(charPool::get)
            .joinToString("")
    }

    fun addTenRandomCommentToList() {
        for (i: Int in 1..10) {
            addStringToList(generateRandomComment())
        }
    }
}