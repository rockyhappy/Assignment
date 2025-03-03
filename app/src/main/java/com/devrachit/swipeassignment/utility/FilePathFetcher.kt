package com.devrachit.swipeassignment.utility

import android.content.Context
import android.database.Cursor
import android.net.Uri
import android.provider.MediaStore

object FilePathFetcher {

    fun getPath(context: Context, uri: Uri): String? {
        var cursor: Cursor? = null
        return try {
            val projection = arrayOf(MediaStore.Images.Media.DATA)
            cursor = context.contentResolver.query(uri, projection, null, null, null)
            val columnIndex = cursor!!.getColumnIndexOrThrow(MediaStore.Images.Media.DATA)
            cursor.moveToFirst()
            cursor.getString(columnIndex)
        } finally {
            cursor?.close()
        }
    }
}