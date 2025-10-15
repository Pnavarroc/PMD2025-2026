package org.iesch.a03_menu_principal.apirazas

import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.iesch.a03_menu_principal.R
import org.iesch.a03_menu_principal.apirazas.Model.ApiService
import org.iesch.a03_menu_principal.apirazas.adapter.DogAdapter
import org.iesch.a03_menu_principal.databinding.ActivityRazasApiBinding
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


//Como voy a usar el searchView necesito decirselo al activity
class RazasApiActivity : AppCompatActivity(), SearchView.OnQueryTextListener {
    lateinit var binding: ActivityRazasApiBinding

    //
    private lateinit var adapterDog: DogAdapter
    private val dogimages = mutableListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding= ActivityRazasApiBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        //
        binding.svDogs.setOnQueryTextListener(this)

        //- Creamos una instancia de retrofit
        //Creamos un métpdp que inicie el RecyclerVeiw
        initRecyclerView()

    }

    private fun initRecyclerView() {
        //hemos de crear el adaptador
        adapterDog = DogAdapter(dogimages)
        binding.rvDogs.layoutManager = LinearLayoutManager(this)
        binding.rvDogs.adapter= adapterDog

    }

    private fun getRetrofit(): Retrofit{
        return Retrofit.Builder()
            .baseUrl("https://dog.ceo/api/breed/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    //Hasta aqui tpdo lo de retrofit


    //- Implemento la funcion de buscar por razas.

    private fun buscarPorRaza(raza : String){
        CoroutineScope(Dispatchers.IO).launch{
            //To do lo que se ejecute aqui se esta ejecutando en un hilo secundario.
            val call=getRetrofit().create<ApiService>(ApiService::class.java).getPerrosPorRaza("$raza/images")
            val puppies = call.body()
            //Estoy en un hilo secundario y para pintar la respuesta necesito volver al hilo principal
            //Lo hare mediante el runOnUIThread
            runOnUiThread {
                //Como el if pintara un Toast o el recycler lo metemos en el hilo principal
                if (call.isSuccessful){
                    //Mostraremos el recyclerView
                    //To do el código que se ejecute aqui lo hará en el hilo principal
                    //Almacenamos en una variable la imagenes
                    val imagenes = puppies?.images ?: emptyList()
                    //Primero borro to do lo que tengamos anteriormente y añado los datos recibidos
                    dogimages.clear()
                    dogimages.addAll(imagenes)
                    //Avisamos al adaptador de que han habido cambios
                    adapterDog.notifyDataSetChanged()

                }else {
                    //Mostraremos un error en un Toast
                    showError()
                }
                hideKeyBoard()

            }


        }
    }

    private fun showError(){
        Toast.makeText(this,"Ha ocurrido un error", Toast.LENGTH_SHORT).show()
    }
    private fun hideKeyBoard(){
        //Ocultamos el teclado
        val imm =getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(binding.main.windowToken,0)
    }


    //Implementamos las dos funciones y las completamos
    override fun onQueryTextSubmit(query: String?): Boolean {
        //Cuando pulsamos buscar, se llamará a este mét odo
        if (!query.isNullOrEmpty()){
            buscarPorRaza(query.lowercase())
        }
        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        //Este mét odo nos avisará cada vez que el textp cambie, y no se hace nda
        return true
    }



}