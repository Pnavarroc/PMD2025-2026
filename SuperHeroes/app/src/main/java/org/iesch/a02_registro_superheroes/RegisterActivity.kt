package org.iesch.a02_registro_superheroes

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.FileProvider
import androidx.core.graphics.drawable.toBitmap
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import org.iesch.a02_registro_superheroes.Detalle.DetalleHeroeActivity
import org.iesch.a02_registro_superheroes.Modelo.SuperHeroe
import org.iesch.a02_registro_superheroes.databinding.ActivityRegisterBinding
import java.io.File

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding

    //Vamos a crear una variable que va a manejar el resultado de haber hecho una foto

    private lateinit var heroImage: ImageView
    private var heroBitmap: Bitmap?=null;
    //Cambiamos el TakePicturePreview por takePicture
    private var picturePath="";
    private val getContent =registerForActivityResult(ActivityResultContracts.TakePicture()){
        //Esto nos va adevolver un objeto de tipo bitMap(Que es para las fotos)
        //Ahora en lugar de un bitmas nos va a devolver un booleano si la toma de la foto es exitosa
        success ->
        if ( success && picturePath.isNotEmpty()){
            //Cualquier imagen del directorio de imagenes la podemos convertir en un objeto bitmap
            heroBitmap = BitmapFactory.decodeFile(picturePath);
            // Pintamos la imagen en el cuadradito
            heroImage.setImageBitmap(heroBitmap);
        }
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
        //Ahora aquí debemos crear un path temporal para guardar la imagen
        val imageFile =createImageFile()
        //Ahora ya tenemos el File, pero lo que necesitamos es el uri
        //FileProvider lo que hace es compartir el file con otras aplicaciones de forma segura.
        val uri = FileProvider.getUriForFile(
            this,
            "${applicationContext.packageName}.provider",
            imageFile
        )

        //Como estamos por encima de la SDK 24 obtendremos el uri a través de FileProvider
        //Ahora le pasamos el uri a la funcion launcher
        getContent.launch(uri)


    }

    private fun createImageFile() : File{
        //Esta funcion crea un file y del file recuperamos el uri que es la direccion de la foto que acabamos de hacer.
        val  fileName = "superhero_image"
        //Esto sera el directorio donde vamos a almacenar la imagen. Por defecto es DIRECTORY_PICTURES.
        val fileDirectory = getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        //Creamos nuestro file y aqui nos pide el nombre del file, el formato y el directorio.
        val imageFile = File.createTempFile(fileName,".jpg",fileDirectory)
        //Ahora ya podemos guardar el path de la variable real.
        picturePath=imageFile.absolutePath
        return imageFile
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
        //Pasamos solamente el picturePath
        intent.putExtra(DetalleHeroeActivity.FOTO_KEY,picturePath)

        startActivity(intent)
    }
}