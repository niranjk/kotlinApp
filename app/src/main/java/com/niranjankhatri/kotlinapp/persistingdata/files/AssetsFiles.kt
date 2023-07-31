package com.niranjankhatri.kotlinapp.persistingdata.files

import android.content.Context

/***
 * Comman assets usage:
 * 1. for custom fonts.
 *
 */
class AssetsFiles {

    fun openAssetsFiles(context: Context){
        // AssetManager class offers the ability to look up assets files and read
        // them
        val assetManager = context.assets
        val root = ""
        val files = assetManager.list(root)
        files?.forEach {
            // to read the file info
            val inputStream = assetManager.open(root + it)
        }
    }
}