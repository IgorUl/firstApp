package com.example.firstapp.data

import kotlin.random.Random

class CommentHolder {

    val getInputStringList: List<String>
        get() = inputStringList

    private val inputStringList = mutableListOf<String>()
    var isSorted: Boolean = false

    fun addStringToList(inputString: String) {
        inputStringList.add(inputString)
        isSorted = false
    }

    fun clearStringList() {
        inputStringList.clear()
    }

    private fun generateRandomComment(): String {

        val charPool : List<Char> = ('a'..'z') + ('A'..'Z') + ('0'..'9')

        return (0..Random.nextInt(5, 20))
            .map { i -> Random.nextInt(0, charPool.size) }
            .map(charPool::get)
            .joinToString("")
    }

    fun addTenRandomCommentToList() {
        for (i: Int in 1..10) {
            addStringToList(generateRandomComment())
        }
    }
}