package iesch.org.practica06

import iesch.org.practica06.dataStore.LoginDataStore
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.ktx.Firebase
import com.google.firebase.remoteconfig.ktx.remoteConfig
import iesch.org.practica06.databinding.ActivityPaginaPrincipalBinding
import iesch.org.practica06.fragmentos.MenuFragmentosActivity
import iesch.org.practica06.peliculas.ListaPeliculasActivity
import iesch.org.practica06.settings.SettingsActivity
import kotlinx.coroutines.launch

class PaginaPrincipalActivity : AppCompatActivity() {

    private lateinit var loginDataStore: LoginDataStore
    private lateinit var binding: ActivityPaginaPrincipalBinding

    override fun onCreate(savedInstanceState: Bundle?) {


        super.onCreate(savedInstanceState)


        binding = ActivityPaginaPrincipalBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //datastore
        loginDataStore = LoginDataStore(this)

        // mostrar nombre
        val user = FirebaseAuth.getInstance().currentUser
        val nombre = user?.displayName ?: "usuario"
        binding.tvBienvenida.text = "Hola, $nombre"

        //logout
        binding.btnLogout.setOnClickListener {
            FirebaseAuth.getInstance().signOut()

            lifecycleScope.launch {
                startActivity(Intent(this@PaginaPrincipalActivity, LoginActivity::class.java))
                finish()
            }
        }


        binding.btnFragments.setOnClickListener { irAMenuFragments() }
        binding.btnRazas.setOnClickListener { irARazasActivity() }
        binding.btnEdadCanina.setOnClickListener { irAEdadCaninaActivity() }
        binding.btnSuperHeroes.setOnClickListener { irASuperHeroesActivity() }
        binding.btnQuizzes.setOnClickListener { irAPreguntasActivity() }
        binding.icCalculadora.setOnClickListener { irACalculadoraActivity() }
        binding.btnSettings.setOnClickListener { irASettings() }
        binding.btnPeliculas.setOnClickListener { irAPeliculas() }
        binding.btnMapas.setOnClickListener { irAmapas() }
        binding.btnDataStore.setOnClickListener { irAUsuarioActivity() }

    }

    private fun irAUsuarioActivity() { startActivity(Intent(this, UsuarioActivity::class.java)) }
    private fun irAmapas() { startActivity(Intent(this, MapasActivity::class.java)) }
    private fun irASettings() { startActivity(Intent(this, SettingsActivity::class.java)) }
    private fun irARazasActivity() { startActivity(Intent(this, RazasApiActivity::class.java)) }
    private fun irAEdadCaninaActivity() { startActivity(Intent(this, EdadCaninaActivity::class.java)) }
    private fun irASuperHeroesActivity() { startActivity(Intent(this, RegisterSuperActivity::class.java)) }
    private fun irAPreguntasActivity() { startActivity(Intent(this, PreguntasActivity::class.java)) }
    private fun irACalculadoraActivity() { startActivity(Intent(this, CalculadoraActivity::class.java)) }
    private fun irAPeliculas() { startActivity(Intent(this, ListaPeliculasActivity::class.java)) }
    private fun irAMenuFragments() { startActivity(Intent(this, MenuFragmentosActivity::class.java)) }
}
