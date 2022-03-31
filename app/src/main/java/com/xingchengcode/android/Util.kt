package com.xingchengcode.android

import android.content.Context
import android.graphics.Bitmap
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File
import java.io.FileOutputStream

suspend fun saveBitmap(context: Context, filename: String, bitmap: Bitmap): File? {
    return withContext(Dispatchers.IO) {
        try {
            val dir = context.cacheDir ?: return@withContext null
            val file = File(dir, filename)
            if (!dir.exists()) dir.mkdirs()
            val success = bitmap.compress(Bitmap.CompressFormat.JPEG, 100, FileOutputStream(file))
            if (success) return@withContext file
        } catch (e: Exception) {
            e.printStackTrace()
        }
        null
    }
}
