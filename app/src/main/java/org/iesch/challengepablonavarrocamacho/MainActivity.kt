package org.iesch.challengepablonavarrocamacho

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
import org.iesch.challengepablonavarrocamacho.databinding.ActivityMainBinding
import java.io.File

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var imagen: ImageView
    private var imageBitmap: Bitmap?=null;
    private var picturePath="";


    private val getContent =registerForActivityResult(ActivityResultContracts.TakePicture()){
        //Esto nos va adevolver un objeto de tipo bitMap(Que es para las fotos)
        //Ahora en lugar de un bitmap nos va a devolver un booleano si la toma de la foto es exitosa
            success ->
        if ( success && picturePath.isNotEmpty()){
            //Cualquier imagen del directorio de imagenes la podemos convertir en un objeto bitmap
            imageBitmap = BitmapFactory.decodeFile(picturePath);
            // Pintamos la imagen en el cuadradito
            imagen.setImageBitmap(imageBitmap);
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



        imagen = binding.btnAbrirCamara
        binding.btnAbrirCamara.setOnClickListener {
            openCamera()
        }

        binding.btnIrACategoriasDetectadas.setOnClickListener {
            irAPantalla2()
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
        val  fileName = "imagen"
        //Esto sera el directorio donde vamos a almacenar la imagen. Por defecto es DIRECTORY_PICTURES.
        val fileDirectory = getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        //Creamos nuestro file y aqui nos pide el nombre del file, el formato y el directorio.
        val imageFile = File.createTempFile(fileName,".jpg",fileDirectory)
        //Ahora ya podemos guardar el path de la variable real.
        picturePath=imageFile.absolutePath
        return imageFile
    }

    private fun irAPantalla2(){
        val intent = Intent(this, ActividadesDetectadasActivity::class.java)


        intent.putExtra(ActividadesDetectadasActivity.FOTO_KEY,picturePath)
        startActivity(intent)
    }
}