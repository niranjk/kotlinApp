package com.niranjankhatri.kotlinapp.persistingdata.files

import android.app.Activity
import android.content.Intent

/***
 * SAF or Storage Access Framework is useful when:
 * 1. you app requires a user to process a file saved on a device by another app
 * (photos and videos)
 * 2. you want to save a file on a device and give a choice of where to save the
 * file and the name of the file
 * 3. you want to offer the files your application uses to other apps ...
 *
 *
 * It is useful becoz :
 * 1. app will avoid read an write permission and still write and access external
 * storage.
 *
 */
val PICK_FILE_REQUEST_CODE = 1

class StorageAccessFramework {

    fun pickFile(activity: Activity){
        // it work based on intents
        val intent = Intent(Intent.ACTION_OPEN_DOCUMENT)
        intent.addCategory(Intent.CATEGORY_OPENABLE)
        intent.type = "*/*" // set the mime type of the files to display
        activity.startActivityForResult(
            intent,
            PICK_FILE_REQUEST_CODE
        )
    }
}