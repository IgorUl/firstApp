package com.example.firstapp.data


import android.content.Context
import java.io.BufferedWriter
import java.io.File
import java.io.FileNotFoundException


class SaveData(context: Context) {

    private var file: File = File(context.filesDir, "inputString.txt")

    fun writeFile(string: String) {

        try {
            file.createNewFile()
            val writer: BufferedWriter = file.bufferedWriter()
            writer.write(string)
            writer.close()
        } catch (e: FileNotFoundException) {
            e.printStackTrace()
        }
    }

    fun readFile(): MutableList<String> {
        return if (file.exists())
        File(file.path).readLines().toMutableList()
        else mutableListOf()
    }
}