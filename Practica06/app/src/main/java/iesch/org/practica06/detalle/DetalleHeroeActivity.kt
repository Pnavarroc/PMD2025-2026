package iesch.org.practica06.detalle


import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Build
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import iesch.org.practica06.PaginaPrincipalActivity
import iesch.org.practica06.databinding.ActivityDetalleHeroeBinding
import iesch.org.practica06.model.SuperHeroe
import iesch.org.practica06.R
import iesch.org.practica06.RegisterSuperActivity
import kotlin.jvm.java

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

        supportActionBar?.title = "Detalle Superheroe"

        val bundle=intent.extras!!
        //8 - Recibimos el Objeto superheroe del intent
        val superHeroe=if (Build.VERSION.SDK_INT>= Build.VERSION_CODES.TIRAMISU){
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

        binding.buttonVolverARegistro.setOnClickListener {
            irARegisterActivity()
        }
        binding.buttonvolverAMenu.setOnClickListener {
            irAMenuPrincipal()
        }


    }

    private fun irARegisterActivity() {
        val irARegister = Intent(this, RegisterSuperActivity::class.java)
        startActivity(irARegister)
        finish()
    }
}

private fun DetalleHeroeActivity.irAMenuPrincipal() {
    val irAMenu = Intent(this, PaginaPrincipalActivity::class.java)
    startActivity(irAMenu)
    finish()
}
