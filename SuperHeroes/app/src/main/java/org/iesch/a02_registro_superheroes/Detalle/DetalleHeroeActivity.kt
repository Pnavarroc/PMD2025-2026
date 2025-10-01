package org.iesch.a02_registro_superheroes.Detalle

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import org.iesch.a02_registro_superheroes.Modelo.SuperHeroe
import org.iesch.a02_registro_superheroes.R
import org.iesch.a02_registro_superheroes.databinding.ActivityDetalleHeroeBinding

class DetalleHeroeActivity : AppCompatActivity() {

    //3 - Para no cometer equivocaciones en las keys, me creo un companion object
    //un companion object es un objeto que pertenece a una clase de kotlin y permite definir miembros estaticos.
    companion object{
        /*const val  HERO_NAME ="heroName";
        const val ALTER_EGO= "alterEgo";
        const val BIO= "bio";
        const val POWER = "power";*/

        const val SUPERHEROE_KEY= "super_heroe_p"

        const val FOTO_KEY= "foto"
    }
    private lateinit var binding: ActivityDetalleHeroeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding= ActivityDetalleHeroeBinding.inflate(layoutInflater);
        setContentView(binding.root);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val bundle=intent.extras!!
        //8 - Recibimos el Objeto superheroe del intent
        val superHeroe=if (android.os.Build.VERSION.SDK_INT>=android.os.Build.VERSION_CODES.TIRAMISU){
            //Para versiones 33 o superiores
             intent.getParcelableExtra(SUPERHEROE_KEY, SuperHeroe::class.java)

        }else{
            @Suppress("DEPRECATION")
            intent.getParcelableExtra<SuperHeroe>(SUPERHEROE_KEY)
        }
        //val bitmap =bundle.getParcelable<Bitmap>(FOTO_KEY)!!
        //Eliminamos el bitmap y obtenemos el String del directorio de ese bitmap
        val bitmapDirectory = bundle.getString(FOTO_KEY)
        val bitmap= BitmapFactory.decodeFile(bitmapDirectory)




        //1 - Vamos a recibir los parametros del Intent
        //Un objeto Bundle es un contenedor de datos que permite almacenar y transportar multiples valores, entre Activitys o fragmentos.
        /*val bundle = intent.extras!!;
        val superHeroName = bundle.getString(HERO_NAME) ?: "No hay nombre";
        val alterEgo = bundle.getString(ALTER_EGO) ?: "No hay alter ego";
        val bio = bundle.getString(BIO) ?: "No hay bio";
        val power = bundle.getFloat(POWER);*/
        //2 - Rellenamos con los campos que hemos recibido del indent.

        binding.tvHeroNameResult.text= superHeroe?.nombre ?: "No hay nombre";
        binding.tvAlterEgoResult.text=superHeroe?.alterEgo ?:"No hay alter ego";
        binding.tvBioResult.text=superHeroe?.bio ?:"No hay  bio";
        binding.rbResultado.rating=superHeroe?.power ?:0f;


        //14 - Asigno la imagen a la imageview
        if ( bitmap!=null ){
            binding.imageView.setImageBitmap(bitmap)
        }
    }
}