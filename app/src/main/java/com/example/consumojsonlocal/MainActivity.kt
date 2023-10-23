package com.example.consumojsonlocal

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.consumojsonlocal.databinding.ActivityMainBinding
import com.example.consumojsonlocal.models.User
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader


class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        consumoRaw()
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
            binding.txtTexto.text = json;
            // Ahora 'json' contiene el contenido del archivo JSON.
        } catch (e: IOException) {
            // Manejar excepciones aquí
        }
        return emptyList()
    }
}