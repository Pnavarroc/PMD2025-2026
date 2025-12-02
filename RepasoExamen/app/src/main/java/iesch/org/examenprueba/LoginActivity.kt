package iesch.org.examenprueba

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.auth.FirebaseAuth
import iesch.org.examenprueba.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()

        // Si ya hay usuario logueado → saltamos a Home
        val currentUser = auth.currentUser
        if (currentUser != null) {
            startActivity(Intent(this, HomeActivity::class.java))
            finish()
        }

        binding.btnIniciarSesion.setOnClickListener {
            login()
        }

        binding.tvIrARegistrar.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }
    }

    private fun login() {
        val email = binding.etEmail.text.toString()
        val pass = binding.etPass.text.toString()

        if (email.isEmpty() || pass.isEmpty()) {
            Toast.makeText(this, "Completa los campos", Toast.LENGTH_SHORT).show()
            return
        }

        auth.signInWithEmailAndPassword(email, pass)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    startActivity(Intent(this, HomeActivity::class.java))
                    finish()
                } else {
                    Toast.makeText(this, "Error en inicio de sesión", Toast.LENGTH_SHORT).show()
                }
            }
    }
}