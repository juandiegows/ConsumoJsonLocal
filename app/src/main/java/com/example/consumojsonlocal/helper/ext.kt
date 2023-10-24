package com.example.consumojsonlocal.helper

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Base64
import java.io.ByteArrayOutputStream

fun String.toBitmap(): Bitmap? {
    try {
        val byteData = Base64.decode(this, Base64.DEFAULT)
        return BitmapFactory.decodeByteArray(byteData, 0, byteData.size)
    } catch (e: Exception) {
        e.printStackTrace()
    }
    return null
}

fun Bitmap.toString(): String {
    val byteArrayOutputStream = ByteArrayOutputStream()
    this.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream)
    val byteArray = byteArrayOutputStream.toByteArray()
    return Base64.encodeToString(byteArray, Base64.DEFAULT)
}