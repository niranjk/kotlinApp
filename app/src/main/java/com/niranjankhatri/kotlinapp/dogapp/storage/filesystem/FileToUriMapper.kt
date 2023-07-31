package com.niranjankhatri.kotlinapp.dogapp.storage.filesystem

import android.content.Context
import android.net.Uri
import androidx.core.content.FileProvider
import java.io.File

class FileToUriMapper {

    fun getUriForFile(context: Context, file: File): Uri {
        return FileProvider.getUriForFile(
            context,
            "com.niranjankhatri.kotlinapp.remote_media_provider",
            file
        )
    }
}