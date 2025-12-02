package iesch.org.apirickymorti

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import iesch.org.apirickymorti.adapter.Adapter
import iesch.org.apirickymorti.databinding.ActivityMainBinding
import iesch.org.apirickymorti.model.Character
import iesch.org.apirickymorti.model.RetrofitClient
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
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

        binding.rvPersonajes.layoutManager = LinearLayoutManager(this)

        cargarDatos()

    }

    private fun cargarDatos() {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val respuesta = RetrofitClient.api.getCharacters()

                runOnUiThread {
                    binding.rvPersonajes.adapter =
                        Adapter(respuesta.results) { p ->
                            irADetalle(p)
                        }
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    private fun irADetalle(personaje: Character) {
        val intent = Intent(this, DetalleActivity::class.java)
        intent.putExtra("personaje", personaje)
        startActivity(intent)
    }
}