package com.mr.anonym.toyonamobile.presentation.managers

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.graphics.BitmapFactory
import android.graphics.Paint
import android.graphics.pdf.PdfDocument
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import com.mr.anonym.toyonamobile.R
import androidx.core.graphics.scale
import com.mr.anonym.domain.model.MonitoringModel
import java.io.ByteArrayOutputStream

@RequiresApi(Build.VERSION_CODES.Q)
fun pdfGenerator(context: Context,activity: Activity, fileName: String, model: MonitoringModel){

    val permissionController = PermissionController(context)
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

    if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.Q){
        if (ActivityCompat.checkSelfPermission(
            context,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
        ) == PackageManager.PERMISSION_GRANTED ||
            ActivityCompat.checkSelfPermission(
                context, Manifest.permission.READ_EXTERNAL_STORAGE
            ) == PackageManager.PERMISSION_GRANTED
        ){
            saveFileToMemoryWithDefault(fileName, pdfBytes = pdfByteArray)
        }else{
            permissionController.requestExternalStoragePermission(activity)
        }
    }else{
        saveFileToMemoryWithMediaStore(context,fileName,pdfByteArray)
    }
}