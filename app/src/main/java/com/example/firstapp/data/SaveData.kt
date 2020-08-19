package com.example.firstapp.data


import android.os.Environment
import java.io.*


class SaveData {

    private val file = File(Environment.getExternalStorageDirectory().path,"/Download/kek.txt")

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

    fun readFile(): List<String> {
        return if (file.exists())
        File(file.path).readLines().toList()
        else emptyList()
    }
}