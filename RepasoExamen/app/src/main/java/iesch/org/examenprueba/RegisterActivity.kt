package iesch.org.examenprueba

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.auth.FirebaseAuth
import iesch.org.examenprueba.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()

        binding.btnRegistrar.setOnClickListener {
            register()
        }
    }

    private fun register() {
        val email = binding.etEmail.text.toString()
        val pass = binding.etPass.text.toString()

        if (email.isEmpty() || pass.isEmpty()) {
            Toast.makeText(this, "Completa los campos", Toast.LENGTH_SHORT).show()
            return
        }

        auth.createUserWithEmailAndPassword(email, pass)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Toast.makeText(this, "Cuenta creada", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this, LoginActivity::class.java))
                    finish()
                } else {
                    Toast.makeText(this, "Error: ${task.exception?.message}", Toast.LENGTH_LONG).show()
                }
            }
    }
}