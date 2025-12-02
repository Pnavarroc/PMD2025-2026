package iesch.org.apirickymorti

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.squareup.picasso.Picasso
import iesch.org.apirickymorti.databinding.ActivityDetalleBinding
import iesch.org.apirickymorti.model.Character

class DetalleActivity : AppCompatActivity() {
    lateinit var binding: ActivityDetalleBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityDetalleBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val personaje = intent.getParcelableExtra<Character>("personaje")

        if (personaje != null) {
            binding.tvNombreDetalle.text = personaje.name
            binding.tvDetalles.text = personaje.status

            Picasso.get()
                .load(personaje.image)
                .into(binding.ivFotoDetalle)
        }
    }
}