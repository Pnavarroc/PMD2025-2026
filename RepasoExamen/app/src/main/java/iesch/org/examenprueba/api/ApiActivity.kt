package iesch.org.examenprueba.api

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import iesch.org.examenprueba.api.adapter.PizzaAdapter
import iesch.org.examenprueba.api.model.Pizza
import iesch.org.examenprueba.api.model.RetroFitClient
import iesch.org.examenprueba.databinding.ActivityApiBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ApiActivity : AppCompatActivity() {

    private lateinit var binding: ActivityApiBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityApiBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recyclerPizzas.layoutManager = LinearLayoutManager(this)

        cargarPizzas()
    }

    private fun cargarPizzas() {

        RetroFitClient.api.getMenu().enqueue(object : Callback<List<Pizza>> {

            override fun onResponse(
                call: Call<List<Pizza>>,
                response: Response<List<Pizza>>
            ) {
                if (response.isSuccessful) {

                    val lista = response.body() ?: emptyList()

                    binding.recyclerPizzas.adapter =
                        PizzaAdapter(lista) { pizza ->
                            Toast.makeText(
                                this@ApiActivity,
                                "Has pulsado: ${pizza.name}",
                                Toast.LENGTH_SHORT
                            ).show()
                        }

                } else {
                    Toast.makeText(this@ApiActivity, "Error en API", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<List<Pizza>>, t: Throwable) {
                Toast.makeText(this@ApiActivity, "Error de conexión", Toast.LENGTH_SHORT).show()
            }
        })
    }
}
