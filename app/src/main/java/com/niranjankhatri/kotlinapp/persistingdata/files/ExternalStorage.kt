package com.niranjankhatri.kotlinapp.persistingdata.files

import android.content.Context
import android.os.Environment

/***
 * Reading and writing external storage requires user permission for reading
 * and writing.
 */
class ExternalStorage {

    fun methodsExternalStorage(context: Context){
        val file = context.getExternalFilesDir( Environment.DIRECTORY_MOVIES)
        val cachDir = context.externalCacheDir
    }
}