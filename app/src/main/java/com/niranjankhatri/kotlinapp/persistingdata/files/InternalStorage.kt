package com.niranjankhatri.kotlinapp.persistingdata.files

import android.content.Context
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.IOException

/***
 * Internal Storage requires no permissions from the user.
 *
 */
class InternalStorage{

    // OKIO
    fun internalStorageMethods(context: Context){
        val dataDirectory = context.dataDir // root folder of your app sandbox
        val filesDir = context.filesDir // folder for app files
        val cacheDir = context.cacheDir
        val directory = context.getDir("name", Context.MODE_PRIVATE) // private mode
    }

    fun readAFile(context: Context){
        val cacheDir = context.cacheDir
        val fileToReadFrom = File(cacheDir, "your-file-name.text")
        val size = fileToReadFrom.length().toInt()
        val bytes = ByteArray(size)
        val tmpBuff = ByteArray(size)
        val fis = FileInputStream(fileToReadFrom)
        try {
            var read = fis.read(bytes, 0, size)
            if (read < size){
                var remain = size - read
                while (remain > 0){
                    read = fis.read(tmpBuff, 0, remain)
                    System.arraycopy(tmpBuff, 0, bytes, size - remain, read)
                    remain -= read
                }
            }
        } catch (e: IOException){
            throw  e
        } finally {
            fis.close()
        }
    }

    fun writeAFile(context: Context){
        val byteToWrite = ByteArray(100)
        val cacheDir = context.cacheDir
        val fileToWriteIn = File(cacheDir, "your-file-name-write.text")
        try {
            if (!fileToWriteIn.exists()){
                fileToWriteIn.createNewFile()
            }
            val fos = FileOutputStream(fileToWriteIn)
            fos.write(byteToWrite)
            fos.close()
        } catch (e: java.lang.Exception){
            e.printStackTrace()
        }
    }
}