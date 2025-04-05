package com.mr.anonym.toyonamobile.presentation.utils

import android.content.ContentValues
import android.content.Context
import android.graphics.pdf.PdfDocument
import android.os.Build
import android.os.Environment
import android.provider.MediaStore
import androidx.annotation.RequiresApi
import java.io.File
import java.io.FileOutputStream

@RequiresApi(Build.VERSION_CODES.Q)
fun saveFileToMemoryWithMediaStore(context: Context, fileName: String,pdfBytes: ByteArray){

    val path = Environment.DIRECTORY_DOWNLOADS

    val contentValues = ContentValues().apply {
        put(MediaStore.Downloads.DISPLAY_NAME,"$fileName.pdf")
        put(MediaStore.Downloads.MIME_TYPE,"application/pdf")
        put(MediaStore.Downloads.IS_PENDING,1)
    }
    val contentResolver = context.contentResolver
    val collection = MediaStore.Downloads.getContentUri(MediaStore.VOLUME_EXTERNAL_PRIMARY,)
    val itemUri = contentResolver.insert(collection,contentValues)
    itemUri?.let {uri ->
        contentResolver.openOutputStream(uri)?.use {outputStream ->
            outputStream.write(pdfBytes)
        }
        contentValues.clear()
        contentValues.put(MediaStore.Downloads.IS_PENDING,0)
        contentResolver.update(uri,contentValues,null,null)
    }
}
fun saveFileToMemoryWithDefault(context:Context, fileName: String, pdfBytes: ByteArray){

    try {
        val path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)
        if (!path.exists()) path.mkdirs()
        val file = File(path,"$fileName.pdf")
        val fileOutputStream = FileOutputStream(file)
        fileOutputStream.write(pdfBytes)
        fileOutputStream.flush()
        fileOutputStream.close()

    }catch (e:Exception){

    }
}