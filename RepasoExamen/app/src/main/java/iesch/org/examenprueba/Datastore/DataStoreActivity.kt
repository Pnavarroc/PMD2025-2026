package iesch.org.examenprueba.Datastore

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import iesch.org.examenprueba.HomeActivity
import iesch.org.examenprueba.databinding.ActivityDatastoreBinding
import kotlinx.coroutines.launch

class DataStoreActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDatastoreBinding
    private lateinit var pref: PreferencesManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDatastoreBinding.inflate(layoutInflater)
        setContentView(binding.root)

        pref = PreferencesManager(this)

        observarCambios()
        configurarEventos()
    }

    private fun configurarEventos() {

        binding.btnGuardarNombre.setOnClickListener {
            val nombre = binding.etNombre.text.toString()
            if (nombre.isNotEmpty()) {
                lifecycleScope.launch {
                    pref.guardarNombre(nombre)
                }
            }
        }

        binding.btnGuardarEdad.setOnClickListener {
            val edadStr = binding.etEdad.text.toString()

            if (edadStr.isNotEmpty()) {
                val edad = edadStr.toIntOrNull()

                if (edad != null) {
                    lifecycleScope.launch {
                        pref.guardarEdad(edad)
                    }
                } else {
                    Toast.makeText(this, "Edad inválida", Toast.LENGTH_SHORT).show()
                }
            }
        }

        binding.swModDark.setOnCheckedChangeListener { _, isChecked ->
            lifecycleScope.launch {
                pref.guardarModoOscuro(isChecked)
            }
        }

        binding.swModNotificaciones.setOnCheckedChangeListener { _, isChecked ->
            lifecycleScope.launch {
                pref.guardarNotificaciones(isChecked)
            }
        }

        binding.btnVolver.setOnClickListener {
            startActivity(Intent(this, HomeActivity::class.java))
            finish()
        }
    }

    private fun observarCambios() {
        lifecycleScope.launch {
            pref.leerDatos.collect { datos ->

                binding.tvNombreGuardado.text = "Nombre: ${datos.nombre}"
                binding.tvEdadGuardada.text = "Edad: ${datos.edad}"
                binding.tvModoOscuro.text =
                    "Modo Oscuro: ${if (datos.modoOscuro) "Activado" else "Desactivado"}"
                binding.tvNotificaciones.text =
                    "Notificaciones: ${if (datos.notificaciones) "Si" else "No"}"

                binding.swModDark.isChecked = datos.modoOscuro
                binding.swModNotificaciones.isChecked = datos.notificaciones
            }
        }
    }
}
