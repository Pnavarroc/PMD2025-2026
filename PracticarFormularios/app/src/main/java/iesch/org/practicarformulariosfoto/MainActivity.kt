package iesch.org.practicarformulariosfoto

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.os.Environment
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.FileProvider
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import iesch.org.practicarformulariosfoto.databinding.ActivityMainBinding
import java.io.File

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding


    //Vamos a crear una variable que va a manejar el resultado de haber hecho una foto

    private lateinit var personaImage: ImageView
    private var personaBitmap: Bitmap?=null;
    //Cambiamos el TakePicturePreview por takePicture
    private var picturePath="";



    private val getContent =registerForActivityResult(ActivityResultContracts.TakePicture()){
        //Esto nos va adevolver un objeto de tipo bitMap(Que es para las fotos)
        //Ahora en lugar de un bitmap nos va a devolver un booleano si la toma de la foto es exitosa
            success ->
        if ( success && picturePath.isNotEmpty()){
            //Cualquier imagen del directorio de imagenes la podemos convertir en un objeto bitmap
            personaBitmap = BitmapFactory.decodeFile(picturePath);
            // Pintamos la imagen en el cuadradito
            personaImage.setImageBitmap(personaBitmap);
        }
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.btnGuardar.setOnClickListener {
            val nombrePersona = binding.etNombre.text.toString()
            val apellidospersona = binding.etApellidos.text.toString()
            val telefonoPersona = binding.etTelefono.text.toString()

            //Me creo un objeto persona y se lo envio a la funcion ir a detalle
            val persona = Persona(nombrePersona,apellidospersona,telefonoPersona)
            irAdetalle(persona)
        }

        personaImage = binding.ivFotoUsuario
        binding.ivFotoUsuario.setOnClickListener {
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
        val  fileName = "ivFotoUsuario"
        //Esto sera el directorio donde vamos a almacenar la imagen. Por defecto es DIRECTORY_PICTURES.
        val fileDirectory = getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        //Creamos nuestro file y aqui nos pide el nombre del file, el formato y el directorio.
        val imageFile = File.createTempFile(fileName,".jpg",fileDirectory)
        //Ahora ya podemos guardar el path de la variable real.
        picturePath=imageFile.absolutePath
        return imageFile
    }

    private fun irAdetalle(persona: Persona){
        val intent = Intent(this, DetalleActivity::class.java)


        intent.putExtra(DetalleActivity.PERSONA_KEY,persona)
        //12 - Aqui añado el objeto bitmap al intent
        //Pasamos solamente el picturePath
        intent.putExtra(DetalleActivity.FOTO_KEY,picturePath)

        startActivity(intent)
    }
}