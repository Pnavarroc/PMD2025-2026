package com.iesch.calculadora

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.iesch.calculadora.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        //Vamos a poner los listeners de los botones:
        binding.buttonSum.setOnClickListener { operar("sum") }
        binding.buttonRest.setOnClickListener { operar("rest") }
        binding.buttonMulti.setOnClickListener { operar("multi") }
        binding.buttonDividir.setOnClickListener { operar("div") }

    }
    private fun operar(tipo: String) {

        //Declaramos los numeros que cogemos del editText
        val num1 = binding.etNum1.text.toString().toDoubleOrNull();
        val num2 = binding.etNum2.text.toString().toDoubleOrNull();

        if (num1==null||num2==null){
            Toast.makeText(this,"Tienes que introducir valores en los campos", Toast.LENGTH_LONG).show();
            return;
        }

        val resul = when (tipo){
            "sum"-> num1+num2;
            "rest"-> num1 - num2;
            "multi"-> num1 * num2;
            "div"-> {
                if (num2==0.0){
                    Toast.makeText(this,"No se puede dividir entre 0", Toast.LENGTH_LONG).show();
                }else num1/num2;
            }
            else -> 0
        }
        binding.TvResultado.text="El resultado de la operacion es: $resul"
    }
}