package iesch.org.practica06


import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import iesch.org.practica06.PreguntasActivity
import iesch.org.practica06.databinding.ActivityPreguntasResultadoBinding
import iesch.org.practica06.R

class ResultadoActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPreguntasResultadoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityPreguntasResultadoBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        val esCorrecto = intent.getBooleanExtra("esCorrecto", false)
        val numPregunta = intent.getIntExtra("numPregunta", 0)

        if (esCorrecto) {
            binding.tvResultado.text = getString(R.string.correcto)
        } else {
            binding.tvResultado.text = getString(R.string.incorrecto)
        }

        if (numPregunta < 2) { // quedan preguntas
            binding.btnSiguiente.text = getString(R.string.siguiente)
            binding.btnSiguiente.setOnClickListener {
                val intent = Intent(this, PreguntasActivity::class.java)
                intent.putExtra("siguientePregunta", numPregunta + 1)
                startActivity(intent)
            }
        } else { // ya terminó el quiz
            binding.btnSiguiente.text = getString(R.string.finalizar)
            binding.btnSiguiente.setOnClickListener {
                // Al pulsar, mostramos el mensaje final en la misma pantalla
                binding.tvResultado.text = getString(R.string.texto_final)
                binding.btnSiguiente.text = getString(R.string.empezar_de_0)

                // Y cambiamos el comportamiento del botón
                binding.btnSiguiente.setOnClickListener {
                    val intent = Intent(this, PreguntasActivity::class.java)
                    intent.putExtra("siguientePregunta", 0)
                    startActivity(intent)
                }

            }
        }
    }
}