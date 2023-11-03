package com.example.consumojsonlocal

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.consumojsonlocal.API.CustomRetrofit
import com.example.consumojsonlocal.adapter.UserAdapter
import com.example.consumojsonlocal.databinding.ActivityMainBinding
import com.example.consumojsonlocal.helper.toBitmap
import com.example.consumojsonlocal.models.User
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import org.json.JSONArray
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStream
import java.io.InputStreamReader
import java.lang.reflect.Type


class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
var text = "hola".substring(0, 3)
     var t =    JSONArray(consumoRaw()).toString()
       var data = JSONArray(t)
        data.getJSONObject(0).get
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
        binding.apply {
            spinnner.layoutManager = LinearLayoutManager(this@MainActivity)
            btnraw.setOnClickListener {
                spinnner.adapter = UserAdapter(consumoRaw()){ user ->
                    if (user.Photo != null) {
                        binding.imgTool.setImageBitmap(user.Photo.toBitmap())
                    }
                    binding.txtNameTool.text = "${user.FirstName} ${user.LastName}"
                }


            }
            btnAlmacenamiento.setOnClickListener {
                consumoAlmacenamiento()
            }
            btnAPI.setOnClickListener {
                connsumoAPIRetrofit()
            }
        }

    }

    var openFileLauncher: ActivityResultLauncher<Intent> = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val data: Intent? = result.data
            val uri: Uri? = data?.data
            try {
                val inputStream: InputStream? = contentResolver.openInputStream(uri!!)
                val reader = BufferedReader(InputStreamReader(inputStream))
                val json = reader.readText()

                val listType: Type? = object : TypeToken<List<User>>() {}.type
                var list: List<User> = Gson().fromJson(json, listType)
                binding.spinnner.adapter = UserAdapter(list){ user ->
                    if (user.Photo != null) {
                        binding.imgTool.setImageBitmap(user.Photo.toBitmap())
                    }
                    binding.txtNameTool.text = "${user.FirstName} ${user.LastName}"
                }


            } catch (e: IOException) {


                e.printStackTrace()

            }

        }
    }

    fun consumoAlmacenamiento() {

        val intent = Intent(Intent.ACTION_OPEN_DOCUMENT)
        intent.addCategory(Intent.CATEGORY_OPENABLE)
        intent.type = "*/*" /// Esto permite seleccionar cualquier tipo de archivo
        openFileLauncher.launch(intent)
    }

    fun connsumoAPIRetrofit() {
        CustomRetrofit.service.getUsers().enqueue(object : Callback<List<User>> {
            override fun onResponse(call: Call<List<User>>, response: Response<List<User>>) {
                if (response.isSuccessful) {
                    binding.spinnner.adapter = UserAdapter(response.body()!!) { user ->
                        if (user.Photo != null) {
                            binding.imgTool.setImageBitmap(user.Photo.toBitmap())
                        }
                        binding.txtNameTool.text = "${user.FirstName} ${user.LastName}"
                    }
                }
            }

            override fun onFailure(call: Call<List<User>>, t: Throwable) {
                t.printStackTrace()
            }
        })
    }

    fun consumoRaw(): List<User> {
        val res = resources

        val filePath = "res/raw/data.json"
        val inputStream = this.javaClass.classLoader.getResourceAsStream(filePath)
        try {
            val reader = BufferedReader(InputStreamReader(inputStream))
            val json = reader.readText()
            val listType: Type? = object : TypeToken<List<User>>() {}.type
            return Gson().fromJson(json, listType)

        } catch (e: IOException) {
            // Manejar excepciones aquí
        }
        return emptyList()
    }
}