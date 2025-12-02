package iesch.org.practica06

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import iesch.org.practica06.dataStore.UsuarioDataStore
import iesch.org.practica06.databinding.ActivityUsuarioBinding
import kotlinx.coroutines.launch
import iesch.org.practica06.R
class UsuarioActivity : AppCompatActivity() {

    lateinit var usuarioDataStore: UsuarioDataStore
    lateinit var binding: ActivityUsuarioBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityUsuarioBinding.inflate(layoutInflater)
        setContentView(binding.root)
        /*ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }*/

        usuarioDataStore = UsuarioDataStore(this)

        lifecycleScope.launch {
            usuarioDataStore.nombre.collect { nombre ->
                binding.tvNombreGuardado.text =
                    "Nombre: ${nombre ?: "No establecido"}"
            }
        }
        lifecycleScope.launch {
            usuarioDataStore.edad.collect { edad ->
                binding.tvEdadGuardada.text =
                    "Edad: ${edad ?: "No establecida"}"
            }
        }
        lifecycleScope.launch {
            usuarioDataStore.modoOscuro.collect { darkMode ->
                binding.tvModoOscuro.text =
                    "Modo Oscuro: ${if (darkMode) "Activado" else "Desactivado"}"
                binding.swModDark.isChecked = darkMode
            }
        }

        lifecycleScope.launch {
            usuarioDataStore.notificaciones.collect { notificaciones ->
                binding.tvNotificaciones.text =
                    "Notificaciones: ${if (notificaciones) "Activadas" else "Desactivadas"}"
                binding.swModNotificaciones.isChecked = notificaciones
            }
        }


        binding.btnGuardarNombre.setOnClickListener {
            val nombre = binding.etNombre.text.toString()
            lifecycleScope.launch {
                usuarioDataStore.guardarNombre(nombre)
            }
        }


        binding.btnGuardarEdad.setOnClickListener {
            val edad = binding.etEdad.text.toString()
            lifecycleScope.launch {
                usuarioDataStore.guardarEdad(edad)
            }
        }

        binding.swModDark.setOnCheckedChangeListener { _, isChecked ->
            lifecycleScope.launch {
                usuarioDataStore.guardarModoOscuro(isChecked)
            }
        }


        binding.swModNotificaciones.setOnCheckedChangeListener { _, isChecked ->
            lifecycleScope.launch {
                usuarioDataStore.guardarNotificaciones(isChecked)
            }
        }


        binding.btnVolver.setOnClickListener {
            startActivity(Intent(this, PaginaPrincipalActivity::class.java))
            finish()
        }
    }
}
