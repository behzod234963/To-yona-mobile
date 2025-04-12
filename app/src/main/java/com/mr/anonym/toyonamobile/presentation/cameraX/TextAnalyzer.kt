package com.mr.anonym.toyonamobile.presentation.cameraX

import android.content.Context
import android.widget.Toast
import androidx.annotation.OptIn
import androidx.camera.core.ExperimentalGetImage
import androidx.camera.core.ImageProxy
import androidx.compose.runtime.Composable
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.text.TextRecognition
import com.google.mlkit.vision.text.TextRecognizer
import com.google.mlkit.vision.text.TextRecognizerOptionsInterface
import com.google.mlkit.vision.text.latin.TextRecognizerOptions

@OptIn(ExperimentalGetImage::class)
fun analyzeImageForText(
    context: Context,
    imageProxy: ImageProxy,
    onResult:(String)-> Unit,
){
    val mediaImage = imageProxy.image
    if (mediaImage != null){
        val image = InputImage.fromMediaImage(mediaImage,imageProxy.imageInfo.rotationDegrees)

        val recognizer = TextRecognition.getClient(TextRecognizerOptions.Builder().build())
        recognizer.process(image)
            .addOnSuccessListener { visionText->
                val text = visionText.text
                onResult(text)
            }
            .addOnFailureListener { exception ->
                Toast.makeText(context, "${exception.message}", Toast.LENGTH_SHORT).show()
            }
            .addOnCompleteListener {
                imageProxy.close()
            }
    }else{
        imageProxy.close()
    }
}