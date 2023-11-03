package com.example.consumojsonlocal.helper

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Base64
import com.example.consumojsonlocal.models.User
import org.json.JSONArray
import org.json.JSONObject
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

fun List<User>.toJson() : String {
    return JSONArray(this).toString()
}

fun MutableList<User>.toJsonM() : String {
    var mutableList:MutableList<String> = mutableListOf()
    this.forEach {
        var json = JSONObject()
        json.put("id", it.Id)
        json.put("FirstName", it.FirstName)
        json.put("LastName", it.LastName)
        json.put("Description", it.Description)
        json.put("Photo", it.Photo)
        var t = json.toString().replace('\\', ' ')
        mutableList.add(t)
    }
    return JSONArray(mutableList).toString()
}
fun String.toListJD():List<User>{
    var users = mutableListOf<User>()

    var array = JSONArray(this)
    for(i  in 0 until  array.length() ){
        var objecto = JSONObject(array.getString(0))
        users.add(User(objecto.getLong("id"),objecto.getString("FirstName"),objecto.getString("LastName"),objecto.getString("Description"),objecto.getString("Photo") ))
    }
return users
}
fun String.removeLastChar():String {
    return  this.substring(0, this.length)
}
fun Bitmap.toString(): String {
    val byteArrayOutputStream = ByteArrayOutputStream()
    this.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream)
    val byteArray = byteArrayOutputStream.toByteArray()
    return Base64.encodeToString(byteArray, Base64.DEFAULT)
}