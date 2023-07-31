package com.niranjankhatri.kotlinapp.persistingdata.copy

import android.content.res.AssetManager

class AssetFileManager(
    private val assetManager: AssetManager
) {
    fun getMyAppFileInputStream() = assetManager.open("my-app-file.txt")
}