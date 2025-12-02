package iesch.org.practicarformulariosfoto

import android.graphics.BitmapFactory
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import iesch.org.practicarformulariosfoto.databinding.ActivityDetalleBinding

class DetalleActivity : AppCompatActivity() {

    companion object{


        const val PERSONA_KEY= "persona_p"

        const val FOTO_KEY= "foto"
    }
    lateinit var binding: ActivityDetalleBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityDetalleBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val bundle=intent.extras!!
        //8 - Recibimos el Objeto superheroe del intent
        val persona=if (android.os.Build.VERSION.SDK_INT>=android.os.Build.VERSION_CODES.TIRAMISU){
            //Para versiones 33 o superiores
            intent.getParcelableExtra(PERSONA_KEY, Persona::class.java)

        }else{
            @Suppress("DEPRECATION")
            intent.getParcelableExtra<Persona>(PERSONA_KEY)
        }
        //val bitmap =bundle.getParcelable<Bitmap>(FOTO_KEY)!!
        //Eliminamos el bitmap y obtenemos el String del directorio de ese bitmap
        val bitmapDirectory = bundle.getString(FOTO_KEY)
        val bitmap= BitmapFactory.decodeFile(bitmapDirectory)




        binding.tvNombreResultado.text= persona?.nombre ?: "No hay nombre";
        binding.tvResultApellidos.text=persona?.apellidos ?:"No hay apellidos";



        //14 - Asigno la imagen a la imageview
        if ( bitmap!=null ){
            binding.imageView.setImageBitmap(bitmap)
        }
    }
}