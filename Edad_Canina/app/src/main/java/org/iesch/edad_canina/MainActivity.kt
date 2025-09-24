package org.iesch.edad_canina

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        //1º - Tomamos el control de todos los elementos de UI
        val resultadoTexto=findViewById<TextView>(R.id.tvRespuesta)
        val calcularBoton =findViewById<Button>(R.id.btnCalcular)
        val editarEdad = findViewById<EditText>(R.id.etedad)
        //2º - Los botones tienen la propiedad seOnClickListener al pulsarlo
        calcularBoton.setOnClickListener {
            //Aqui va el código de lo que queremos hacer al pulsar el boton de calcular.
            val edadString = editarEdad.text.toString();
           if (edadString.isEmpty()){
               //3º - Me creo un mensaje de tipo Toast
               Toast.makeText(this,R.string.toastText, Toast.LENGTH_LONG).show()
           }else{
               val edadInt = edadString.toInt();

               val edadPerro = edadInt*7
               var resultado = getString(R.string.resulta_format,edadPerro)
               resultadoTexto.text = resultado
           }
        }


    }


}
