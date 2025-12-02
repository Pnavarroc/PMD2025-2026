package iesch.org.examenprueba

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.auth.FirebaseAuth
import iesch.org.examenprueba.Datastore.DataStoreActivity
import iesch.org.examenprueba.api.ApiActivity
import iesch.org.examenprueba.databinding.ActivityHomeBinding
import iesch.org.examenprueba.firestore.FirestoreActivity

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()

        // 1️⃣ MOSTRAR BIENVENIDA
        val userEmail = auth.currentUser?.email ?: "Usuario"
        binding.tvBienvenida.text = "Bienvenido: $userEmail"

        // 2️⃣ BOTÓN API
        binding.btnApi.setOnClickListener {
            startActivity(Intent(this, ApiActivity::class.java))
        }

        // 3️⃣ BOTÓN FIRESTORE
        binding.btnDataStore.setOnClickListener {
            startActivity(Intent(this, DataStoreActivity::class.java))
        }

        // 4️⃣ CERRAR SESIÓN
        binding.btnLogout.setOnClickListener {
            auth.signOut()
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
    }
}