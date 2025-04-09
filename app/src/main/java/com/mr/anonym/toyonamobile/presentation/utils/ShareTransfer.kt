package com.mr.anonym.toyonamobile.presentation.utils

import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.graphics.Paint
import android.graphics.pdf.PdfDocument
import android.os.Environment
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.Toast
import androidx.core.content.FileProvider
import androidx.core.graphics.scale
import com.mr.anonym.domain.model.MonitoringModel
import com.mr.anonym.toyonamobile.R
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream

fun shareTransfer(context: Context, fileName: String, model: MonitoringModel){

    val pdfDocument = PdfDocument()
    val byteArrayOutputStream = ByteArrayOutputStream()
    val pageInfo = PdfDocument.PageInfo.Builder(595,842,1).create()
    val page = pdfDocument.startPage(pageInfo)
    val canvas = page.canvas
    val paint = Paint()

    val iconBitmap = BitmapFactory.decodeResource(context.resources, R.drawable.ic_check)
    val iconScale = iconBitmap.scale(70, 70)
    canvas.drawBitmap(iconScale,250f,30f,paint)

//    Event Name
    paint.textSize = 24f
    paint.textAlign = Paint.Align.CENTER
    paint.isFakeBoldText = true
    canvas.drawText("${model.eventOwnerName} ${model.eventOwnerLastName} ${model.eventName}",300f,140f,paint)

//    Date
    paint.textSize = 18f
    paint.textAlign = Paint.Align.LEFT
    paint.isFakeBoldText = false
    canvas.drawText("${context.getString(R.string.time_of_the_event)} ${model.dateTime}",20f,180f,paint)

//    Field
    paint.textSize = 18f
    paint.textAlign = Paint.Align.LEFT
    paint.isFakeBoldText = false
    canvas.drawText("${context.getString(R.string.amount)} : ${model.amount}",20f,210f,paint)
    canvas.drawText("${context.getString(R.string.commission)} : 500",20f,240f,paint)
    canvas.drawText("${context.getString(R.string.sender_card)} : ${model.senderCardNumber}",20f,270f,paint)
    canvas.drawText("${context.getString(R.string.sender_name)} : ${model.senderCardHolder}",20f,300f,paint)
    canvas.drawText("${context.getString(R.string.receiver_card)} : ${model.receiverCardNumber}",20f,330f,paint)
    canvas.drawText("${context.getString(R.string.receiver_name)} : ${model.receiverCardHolder}",20f,360f,paint)
    canvas.drawText("${context.getString(R.string.status)} : ${model.transferStatus}",20f,390f,paint)
    pdfDocument.finishPage(page)
    pdfDocument.writeTo(byteArrayOutputStream)
    pdfDocument.close()
    val pdfByteArray = byteArrayOutputStream.toByteArray()

    try {
        val path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)
        if (!path.exists()) path.mkdirs()
        val file = File(path,"$fileName.pdf")
        val fileOutputStream = FileOutputStream(file)
        fileOutputStream.write(pdfByteArray)
        fileOutputStream.flush()
        fileOutputStream.close()

        Log.d("FileIoLogging", "shareTransfer: ${context.packageName}")
        val uri = FileProvider.getUriForFile(
            context,
            "${context.packageName}.provider",
            file
        )
        val shareIntent = Intent(Intent.ACTION_SEND).apply {
            type = "application/pdf"
            putExtra(Intent.EXTRA_STREAM,uri)
            addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
        }

        val chooser = Intent.createChooser(shareIntent, context.getString(R.string.share))
        context.startActivity(chooser)

        Handler(Looper.getMainLooper()).postDelayed({
            if (file.exists()){
                file.delete()
            }
        },300000)
    }catch (e:Exception){
        Log.d("FileIoLogging", "shareTransfer: ${e.message}")
    }
}