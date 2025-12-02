package iesch.org.practica06


import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import iesch.org.practica06.databinding.ActivityPreguntasBinding
import iesch.org.practica06.model.Preguntas
import iesch.org.practica06.R
import iesch.org.practica06.ResultadoActivity

class PreguntasActivity: AppCompatActivity() {
    private lateinit var  binding: ActivityPreguntasBinding
    private lateinit var preguntas: List<Preguntas>
    private var numPregunta=0



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityPreguntasBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val textoComun = getString(R.string.pregunta1)
        val yoQueSe = getString(R.string.y_yoquese)

        preguntas = listOf(
            Preguntas(
                textoPregunta = textoComun,
                equacion = "10y = 70",
                opciones = listOf("y = 7", "y = 23", yoQueSe),
                opcionCorrecta = 0
            ),
            Preguntas(
                textoPregunta = textoComun,
                equacion = "5y = 20",
                opciones = listOf("y = 2", "y = 4", "y = 6"),
                opcionCorrecta = 1
            ),
            Preguntas(
                textoPregunta = textoComun,
                equacion = "3y = 9",
                opciones = listOf("y = 3", "y = 2", "y = 5"),
                opcionCorrecta = 0
            )
        )




        numPregunta= intent.getIntExtra("siguientePregunta",0)
        mostrarPregunta()

        binding.btnEnviar.setOnClickListener {
            val opcionSeleccionada = binding.radioGroup.checkedRadioButtonId

            if (opcionSeleccionada== -1){
                Toast.makeText(this,getString(R.string.toast_no_opcion), Toast.LENGTH_LONG).show()
            }else{
                val numSeleccionada = when(opcionSeleccionada){
                    binding.radBtnOpcion1.id->0
                    binding.radBtnOpcion2.id->1
                    binding.radBtnOpcion3.id->2
                    else -> -1
                }
                val esCorrecta =numSeleccionada==preguntas[numPregunta].opcionCorrecta
                val intent = Intent(this, ResultadoActivity::class.java)
                intent.putExtra("esCorrecto", esCorrecta)
                intent.putExtra("numPregunta", numPregunta)
                startActivity(intent)

            }




        }

    }
    private fun mostrarPregunta() {
        val pregunta = preguntas[numPregunta]

        binding.tvNumPreguntas.text="${numPregunta +1}/${preguntas.size}"
        binding.tvPregunta.text=pregunta.textoPregunta
        binding.tvPregunta11.text=pregunta.equacion
        binding.radBtnOpcion1.text=pregunta.opciones[0]
        binding.radBtnOpcion2.text=pregunta.opciones[1]
        binding.radBtnOpcion3.text=pregunta.opciones[2]
        binding.radioGroup.clearCheck()

    }
}