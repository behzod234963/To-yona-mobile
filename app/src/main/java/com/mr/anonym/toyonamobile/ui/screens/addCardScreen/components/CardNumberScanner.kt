package com.mr.anonym.toyonamobile.ui.screens.addCardScreen.components

import android.content.Context
import androidx.compose.runtime.Composable
import com.mr.anonym.toyonamobile.presentation.cameraX.CameraPreviewView
import com.mr.anonym.toyonamobile.presentation.cameraX.analyzeImageForText

@Composable
fun CardNumberScanner(
    context: Context,
    isFlashOn: Boolean,
    onCardNumberFound:(String)-> Unit
) {

    CameraPreviewView(
        isFlashOn = isFlashOn
    ) { imageProxy ->
        analyzeImageForText(
            context = context,
            imageProxy = imageProxy,
        ) { text ->
            val cardNumberRegex = Regex("""\b\d{4} \d{4} \d{4} \d{4}\b""")
            val match = cardNumberRegex.find(text)
            match?.let {
                onCardNumberFound(it.value)
            }
        }
    }
}