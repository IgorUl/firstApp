package com.example.firstapp.data

import kotlin.random.Random

class CommentHolder {

    val getInputStringList: List<String>
        get() = inputStringList

    private var inputStringList: MutableList<String> = mutableListOf()

    fun addStringToList(inputString: String) {
        inputStringList.add(inputString)
    }

    fun clearStringList() {
        inputStringList.clear()
    }

    fun addFromFileToList(list: List<String>) {
        clearStringList()
        inputStringList.addAll(list)
    }

    private fun generateRandomComment(): String {
        val charPool: List<Char> = ('a'..'z') + ('A'..'Z') + ('!'..'/') + ('0'..'9')

        return (0..Random.nextInt(5, 20))
            .map { Random.nextInt(0, charPool.size) }
            .map(charPool::get)
            .joinToString("")
    }

    fun addRandomCommentsToList(commentCount: Int) {
        for (i: Int in 1..commentCount) {
            addStringToList(generateRandomComment())
        }
    }
}