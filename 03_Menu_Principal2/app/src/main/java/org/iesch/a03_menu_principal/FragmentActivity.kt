package org.iesch.a03_menu_principal

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import org.iesch.a03_menu_principal.databinding.ActivityFragmentBinding
import org.iesch.a03_menu_principal.fragmentos.HomeFragment
import org.iesch.a03_menu_principal.fragmentos.ProfileFragment
import org.iesch.a03_menu_principal.fragmentos.SettingsFragment

class FragmentActivity : AppCompatActivity() {
    private  lateinit var binding: ActivityFragmentBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFragmentBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        //Muestro un fragmento por defecto.
        replaceFrament(HomeFragment())

        binding.bottomNavigationView.setOnItemSelectedListener {
            when(it.itemId){
                R.id.menu_inicio -> {
                    replaceFrament(HomeFragment())
                    true
                }
                R.id.menu_perfil -> {
                    replaceFrament(ProfileFragment())
                    true
                }
                R.id.menu_herramientas -> {
                    replaceFrament(SettingsFragment())
                    true
                }
                else -> false
            }
        }
    }

    //Me creo una función para remplazar los fragments
    private fun replaceFrament(fragment: Fragment){
        //Creo las variables para manejar los fragmentos
        val fragmentManager= supportFragmentManager
        //Creo la transacción
        val fragmentTransaction=fragmentManager.beginTransaction()
        //Reemplazamos el fragment que haya
        fragmentTransaction.replace(R.id.frameLayout,fragment)
        //Confirmamos la transacción
        fragmentTransaction.commit()
    }
}