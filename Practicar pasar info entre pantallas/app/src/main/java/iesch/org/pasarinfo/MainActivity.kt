package iesch.org.pasarinfo

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import iesch.org.pasarinfo.databinding.ActivityMainBinding

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


        binding.btnPasarPantalla2.setOnClickListener {
        val nombre = binding.etNombre.text.toString()
            if (nombre.isNotEmpty()){

                val intent = Intent(this, Pantalla2Activity::class.java)

                intent.putExtra("Nombre",nombre)
                startActivity(intent);
                finish()

            }else {
                //Si falta alguna variable sacamos un toast.
                Toast.makeText(this, "Debes de introducir el nombre", Toast.LENGTH_LONG).show()
            }
        }

    }
}