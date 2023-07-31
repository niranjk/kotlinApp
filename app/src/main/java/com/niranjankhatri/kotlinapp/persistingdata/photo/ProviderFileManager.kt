package com.niranjankhatri.kotlinapp.persistingdata.photo

import android.content.ContentResolver
import android.content.ContentValues
import android.content.Context
import android.net.Uri
import org.apache.commons.io.IOUtils
import java.io.File
import java.util.concurrent.Executor

class ProviderFileManager(
    private val context: Context,
    private val fileHelper: FileHelper,
    private val contentResolver: ContentResolver,
    private val executor: Executor,
    private val mediaContentHelper: MediaContentHelper
) {

    fun generatePhotoUri(time: Long): FileInfo{
        val name = "img_$time.jpg"
        val file = File(
            context.getExternalFilesDir(fileHelper.getPictureFolder()),
            name
        )
        return FileInfo(
            fileHelper.getUriFromFile(file),
            file,
            name,
            fileHelper.getPictureFolder(),
            "image/jpeg"
        )
    }

    fun generateVideoUri(time: Long): FileInfo{
        val name = "video_$time.mp4"
        val file = File(
            context.getExternalFilesDir(fileHelper.getVideosFolder()),
            name
        )
        return FileInfo(
            fileHelper.getUriFromFile(file),
            file,
            name,
            fileHelper.getVideosFolder(),
            "video/mp4"
        )
    }

    fun insertImageToStore(fileInfo: FileInfo?){
        fileInfo?.let {
            insertToStore(
                fileInfo,
                mediaContentHelper.getImageContentUri(),
                mediaContentHelper.generateImageContentValues(it)
            )
        }
    }

    fun insertVideoToStore(fileInfo: FileInfo?){
        fileInfo?.let {
            insertToStore(
                fileInfo,
                mediaContentHelper.getVideoContentUri(),
                mediaContentHelper.generateVideoContentValues(it)
            )
        }
    }

    private fun insertToStore(fileInfo: FileInfo,
                              imageContentUri: Uri,
                              generateImageContentValues: ContentValues) {
        executor.execute {
            val insertedUri = contentResolver.insert(imageContentUri, generateImageContentValues )
            insertedUri?.let {
                val inputStream = contentResolver.openInputStream(fileInfo.uri)
                val outputStream = contentResolver.openOutputStream(insertedUri)
                IOUtils.copy(inputStream, outputStream)
            }
        }
    }

}