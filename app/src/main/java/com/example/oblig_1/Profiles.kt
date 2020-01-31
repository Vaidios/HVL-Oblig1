package com.example.oblig_1

import android.graphics.Bitmap

class Profile(name: String, picID: Int? = null, pictureBitmap: Bitmap? = null) {
    val name: String = name
    val picture: Int? = picID
    val pictureBitmap: Bitmap? = pictureBitmap
}