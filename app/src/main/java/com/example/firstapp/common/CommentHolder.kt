package com.example.firstapp.common

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
}
