package com.niranjankhatri.kotlinapp.persistingdata.copy

import android.content.Context
import android.net.Uri
import org.apache.commons.io.IOUtils
import java.io.File
import java.io.InputStream
import java.util.concurrent.Executor

class ProviderFileManager(
    private val context: Context,
    private val executor: Executor,
    private val fileToUriMapper: FileToUriMapper
) {
    fun writeStream(
        name: String,
        inputStream : InputStream
    ){
        executor.execute {
            val fileToSave = File(getDocsFolder(), name)
            val outputStrem = context.contentResolver.openOutputStream(
                fileToUriMapper.getUriFromFile(
                    context,
                    fileToSave
                ), "rw"
            )
            IOUtils.copy(inputStream, outputStrem)
        }
    }

    private fun getDocsFolder(): File{
        val folder = File(context.getExternalFilesDir(null), "docs")
        if (!folder.exists()){
            folder.mkdirs()
        }
        return folder
    }

    fun writeStreamFromUri(name: String, inputStream: InputStream, uri: Uri){
        executor.execute {
            val outputStream = context.contentResolver.openOutputStream(uri, "rw")
            IOUtils.copy(inputStream, outputStream)
        }
    }
}