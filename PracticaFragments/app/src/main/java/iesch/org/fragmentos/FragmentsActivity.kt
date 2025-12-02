package iesch.org.fragmentos

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import iesch.org.fragmentos.databinding.ActivityFragmentsBinding

class FragmentsActivity : AppCompatActivity() {
    lateinit var binding: ActivityFragmentsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding= ActivityFragmentsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        // Fragment por defecto
        mostrarFragment(Fragment1())

        // Cambiar fragment según botón del menú inferior
        binding.bottomNav.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_frag1 -> mostrarFragment(Fragment1())
                R.id.nav_frag2 -> mostrarFragment(Fragment2())
                R.id.nav_frag3 -> mostrarFragment(Fragment3())
            }
            true
        }
    }

    private fun mostrarFragment(frag: androidx.fragment.app.Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(binding.contenedorFragmentos.id, frag)
            .commit()
    }
}