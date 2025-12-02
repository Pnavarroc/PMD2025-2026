package iesch.org.practica06

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.userProfileChangeRequest
import iesch.org.practica06.databinding.ActivityRegisterBinding
import iesch.org.practica06.theme.ThemeManager

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()

        binding.btnRegistroFirebase.setOnClickListener {
            createAccount()
        }


    }

    private fun createAccount() {
        val nombre = binding.editNombre.text.toString().trim()
        val email = binding.editEmail.text.toString().trim()
        val password = binding.editPassword.text.toString().trim()

        if (nombre.isEmpty() || email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Rellena todos los campos", Toast.LENGTH_SHORT).show()
            return
        }

        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {

                    val user = auth.currentUser


                    val profileUpdates = userProfileChangeRequest {
                        displayName = nombre
                    }

                    user?.updateProfile(profileUpdates)?.addOnCompleteListener {

                        Toast.makeText(
                            this,
                            "Cuenta creada correctamente. Inicia sesión",
                            Toast.LENGTH_SHORT
                        ).show()

                        startActivity(Intent(this, LoginActivity::class.java))
                        finish()
                    }

                } else {
                    Toast.makeText(this, "Error: ${task.exception?.message}", Toast.LENGTH_LONG).show()
                }
            }
    }
}
