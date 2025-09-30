package org.iesch.a02_registro_superheroes

import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.graphics.drawable.toBitmap
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import org.iesch.a02_registro_superheroes.Detalle.DetalleHeroeActivity
import org.iesch.a02_registro_superheroes.Modelo.SuperHeroe
import org.iesch.a02_registro_superheroes.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding

    //Vamos a crear una variable que va a manejar el resultado de haber hecho una foto

    private lateinit var heroImage: ImageView
    private var heroBitmap: Bitmap?=null;
    private val getContent =registerForActivityResult(ActivityResultContracts.TakePicturePreview()){
        //Esto nos va adevolver un objeto de tipo bitMap(Que es para las fotos)
        bitmap ->
            heroBitmap=bitmap
            heroImage.setImageBitmap(heroBitmap)
    }


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
            //6- Me creo un objeto super heroe y se lo envio a la funcion irADetalle
            val SuperHeroe = SuperHeroe(superHeroName,alterEgo,bio,power)
            irADetalleHeroe(SuperHeroe)
        }

        //10-
        heroImage = binding.superheroImage
        binding.superheroImage.setOnClickListener {
            openCamera()
        }
    }

    private fun openCamera() {
        //11 - Abrimos la camara llamando al getContent launch
        getContent.launch(null)

    }

    private fun irADetalleHeroe(superHeroe: SuperHeroe){
        //El intent debe tener muy claro dezsde donde se le llama y a donde va
        val intent = Intent(this, DetalleHeroeActivity::class.java)
        //Añado los valores al intent con la funcion putExtra
        /*
        *   intent.putExtra(DetalleHeroeActivity.HERO_NAME,superHeroName)
        intent.putExtra(DetalleHeroeActivity.ALTER_EGO,alterEgo)
        intent.putExtra(DetalleHeroeActivity.BIO,bio)
        intent.putExtra(DetalleHeroeActivity.POWER,power)*/
        intent.putExtra(DetalleHeroeActivity.SUPERHEROE_KEY,superHeroe)
        //12 - Aqui añado el objeto bitmap al intent

        intent.putExtra(DetalleHeroeActivity.FOTO_KEY,heroImage.drawable.toBitmap())

        startActivity(intent)
    }
}