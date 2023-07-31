package com.niranjankhatri.kotlinapp.dogapp.storage.filesystem

import android.content.Context
import org.apache.commons.io.IOUtils
import java.io.File
import java.io.InputStream

class ProviderFileHandler(
    private val context: Context,
    private val fileToUriMapper: FileToUriMapper
) {
    fun writeStream(name: String, inputStream: InputStream){
        val fileToSave = File(context.externalCacheDir, name)
        val outputStrem = context.contentResolver.openOutputStream(
            fileToUriMapper.getUriForFile(context, fileToSave),
            "rw"
        )
        IOUtils.copy(inputStream, outputStrem)
    }
}