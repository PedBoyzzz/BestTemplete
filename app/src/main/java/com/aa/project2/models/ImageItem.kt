package com.aa.project2.models

import android.graphics.Bitmap
import java.io.File

data class ImageItem(
    val image: Bitmap,
    val name:String,
    val file:File
)
