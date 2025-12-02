package iesch.org.examenprueba

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import iesch.org.examenprueba.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding
    private lateinit var auth: FirebaseAuth
    private val db = FirebaseFirestore.getInstance()

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

        // 1️⃣ CREAR USUARIO EN AUTH
        auth.createUserWithEmailAndPassword(email, pass)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {

                    val userId = auth.currentUser?.uid ?: return@addOnCompleteListener

                    // 2️⃣ OBJETO QUE SE GUARDARÁ EN FIRESTORE
                    val nuevoUsuario = hashMapOf(
                        "id" to userId,
                        "email" to email,
                        "creado" to System.currentTimeMillis()
                    )

                    // 3️⃣ GUARDAR EN LA COLECCIÓN "usuarios"
                    db.collection("usuarios")
                        .document(userId)
                        .set(nuevoUsuario)
                        .addOnSuccessListener {
                            Toast.makeText(this, "Usuario registrado 👍", Toast.LENGTH_SHORT).show()
                            startActivity(Intent(this, LoginActivity::class.java))
                            finish()
                        }
                        .addOnFailureListener {
                            Toast.makeText(this, "Error guardando en Firestore", Toast.LENGTH_SHORT).show()
                        }

                } else {
                    Toast.makeText(this, "Error al registrar", Toast.LENGTH_SHORT).show()
                }
            }
    }
}
