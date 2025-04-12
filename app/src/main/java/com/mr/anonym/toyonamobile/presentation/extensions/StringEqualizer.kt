package com.mr.anonym.toyonamobile.presentation.extensions

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp

@Composable
fun String.stringEqualizer(): String{

    val context = LocalContext.current
    val configuration = LocalConfiguration.current
    val density = LocalDensity.current

    val screenWidth = with(density){ configuration.screenWidthDp.dp }

    return if ( screenWidth.value < 370.0 ){
        this.take(17) + "..."
    }else{
        this.take(22) + "..."
    }
}