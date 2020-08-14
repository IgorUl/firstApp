package com.example.firstapp.Data

import java.io.*


class Data {


    private val fileName: String = "inputString"
    private val file = File("/sdcard/Download/kek.txt")

    fun writeFile(string: String) {
        try {
            file.createNewFile()
            val writer = file.bufferedWriter()
            writer.write(string)
            writer.close()
        } catch (e: FileNotFoundException) {
            e.printStackTrace()
        }
    }

    fun readFile(): String {
        return if (file.exists())
        File(file.path).readLines().toString()
        else ""
    }
}