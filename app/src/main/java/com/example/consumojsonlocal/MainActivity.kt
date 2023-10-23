package com.example.consumojsonlocal

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.consumojsonlocal.databinding.ActivityMainBinding
import com.example.consumojsonlocal.models.User
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.lang.reflect.Type


class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.txtTexto.text = consumoRaw().size.toString()
    }

    fun consumoRaw(): List<User> {
        val res = resources

        val filePath = "res/raw/data.json"
        val inputStream = this.javaClass.classLoader.getResourceAsStream(filePath)
        try {
            val reader = BufferedReader(InputStreamReader(inputStream))
            val jsonString = StringBuilder()
            var line: String?
            while (reader.readLine().also { line = it } != null) {
                jsonString.append(line)
            }
            val json = jsonString.toString()


             val listType: Type? = object : TypeToken<List<User>>() {}.type
             return  Gson().fromJson(json, listType)

        } catch (e: IOException) {
            // Manejar excepciones aquí
        }
        return emptyList()
    }
}