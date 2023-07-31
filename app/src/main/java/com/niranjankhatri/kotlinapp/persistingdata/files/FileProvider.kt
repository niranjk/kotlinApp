package com.niranjankhatri.kotlinapp.persistingdata.files

import android.content.Context
import android.os.Environment

/***
 * Useful in organizing the file and folder structure of your application.
 *
 * It allows you to specify an XML file, in which you can define how your files
 * should be split between internal and external storage if you choose to do so.
 *
 * It also gives you the ability to grant access to other apps to your files
 * by hiding the path and generating a unique URI to identify and query your file.
 *
 */
class FileProvider {

    fun methods(context: Context){
        val file_path = context.filesDir
        val cache_path = context.cacheDir
        val external_path = Environment.getExternalStorageDirectory()
        val external_files_path = context.getExternalFilesDir(null)
        val external_cache_path = context.externalCacheDir
        val external_media_path = context.externalMediaDirs
    }
}