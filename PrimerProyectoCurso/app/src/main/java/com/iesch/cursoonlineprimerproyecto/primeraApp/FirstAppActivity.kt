package com.iesch.cursoonlineprimerproyecto.primeraApp

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.iesch.cursoonlineprimerproyecto.R

class FirstAppActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_first_app)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val btnStart= findViewById<Button>(R.id.btnStart)

        val  etName = findViewById<EditText>(R.id.etName)




        btnStart.setOnClickListener {
            val name =etName.text.toString()
            if (name.isNotEmpty()){
                Log.i("Loquesea","Boton Pulsado ${etName.text.toString()}")

            }
        }

    }


}