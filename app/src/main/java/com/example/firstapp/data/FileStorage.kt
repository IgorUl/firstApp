package com.example.firstapp.data


import android.content.Context
import java.io.BufferedWriter
import java.io.File
import java.io.FileNotFoundException
import java.io.IOException


class FileStorage(context: Context) {

    private var file: File = File(context.filesDir, FILE_NAME)



    fun writeFile(string: String): Boolean {
        try {
            file.createNewFile()
            val writer: BufferedWriter = file.bufferedWriter()
            writer.use {
                writer.write(string)
                return true
            }
        } catch (e: FileNotFoundException) {
            e.printStackTrace()
            return false
        } catch (e: IOException) {
            e.printStackTrace()
            return false
        }
    }

    fun readFile(): List<String> =
        if (file.exists()) {
            File(file.path).readLines().toList()
        } else {
            emptyList()
        }

    companion object {
        private const val FILE_NAME = "inputString.txt"
    }
}