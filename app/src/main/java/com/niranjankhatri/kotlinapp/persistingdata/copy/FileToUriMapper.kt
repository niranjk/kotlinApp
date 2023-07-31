package com.niranjankhatri.kotlinapp.persistingdata.copy

import android.content.Context
import android.net.Uri
import androidx.core.content.FileProvider
import java.io.File

class FileToUriMapper {

    fun getUriFromFile(context: Context, file: File): Uri{
        return FileProvider.getUriForFile(
            context,
            "com.niranjankhatri.kotlinapp.files",
            file
        )
    }
}