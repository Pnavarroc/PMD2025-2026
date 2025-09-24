package org.iesch.a02_registro_superheroes

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import org.iesch.a02_registro_superheroes.Detalle.DetalleHeroeActivity
import org.iesch.a02_registro_superheroes.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding
    //Al pulsar el boton quiero ir a la otra pantalla lo nunca antes visto!!! :)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.btnGuardar.setOnClickListener {
            // Nos creamos las variables necesarias para pasarlas al indent
            val superHeroName = binding.etHeroName.text.toString()
            val alterEgo = binding.etAlterEgo.text.toString()
            val bio = binding.etBio.text.toString()
            val power = binding.rbPower.rating
            irADetalleHeroe(superHeroName,alterEgo,bio,power)
        }
    }

    private fun irADetalleHeroe(superHeroName: String,alterEgo: String, bio: String, power: Float){
        //El intent debe tener muy claro dezsde donde se le llama y a donde va
        val intent = Intent(this, DetalleHeroeActivity::class.java)

        //Añado los valores al intent con la funcion putExtra
        intent.putExtra("heroName",superHeroName)
        intent.putExtra("alterEgo",alterEgo)
        intent.putExtra("bio",bio)
        intent.putExtra("power",power)



        startActivity(intent)
    }
}