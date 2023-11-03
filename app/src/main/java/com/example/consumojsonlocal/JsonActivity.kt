package com.example.consumojsonlocal

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.consumojsonlocal.databinding.ActivityJsonBinding
import com.example.consumojsonlocal.helper.toJson
import com.example.consumojsonlocal.helper.toJsonM
import com.example.consumojsonlocal.helper.toListJD
import com.example.consumojsonlocal.models.User

class JsonActivity : AppCompatActivity() {
    lateinit var binding: ActivityJsonBinding

    var mutableList = mutableListOf(
        User(1, "Samuel", "", "", ""),
        User(2, "Jeider", "", "", ""),
        User(3, "Dainer", "", "", ""),
        User(4, "Juan Diego", "", "", ""),
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityJsonBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {

            btnLeer.setOnClickListener {
             var text = mutableList.toJsonM()
               txtTexto.text = text
                Toast.makeText(this@JsonActivity, text, Toast.LENGTH_SHORT).show()
            }
            btnPasar.setOnClickListener {
                try {
                    Toast.makeText(this@JsonActivity, txtTexto.text.toString().toListJD().size.toString(), Toast.LENGTH_SHORT).show()
                }catch (e:Exception){
                    Toast.makeText(this@JsonActivity, "revise que sea formato JSON", Toast.LENGTH_SHORT).show()
                }
            }
        }

    }
}