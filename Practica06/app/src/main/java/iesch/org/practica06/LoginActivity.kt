package iesch.org.practica06

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.messaging.FirebaseMessaging
import com.google.firebase.remoteconfig.ktx.remoteConfig
import com.google.firebase.remoteconfig.remoteConfigSettings
import iesch.org.practica06.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var auth: FirebaseAuth

    private val googleLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)

            try {
                val account = task.getResult(ApiException::class.java)
                val credential = GoogleAuthProvider.getCredential(account.idToken, null)

                auth.signInWithCredential(credential)
                    .addOnCompleteListener { taskLogin ->
                        if (taskLogin.isSuccessful) {

                            saveFcmToken()
                            startActivity(Intent(this, PaginaPrincipalActivity::class.java))
                            finish()

                        } else {
                            Toast.makeText(this, "Error al iniciar sesión con Google", Toast.LENGTH_LONG).show()
                        }
                    }

            } catch (e: ApiException) {
                Toast.makeText(this, "Error Google: ${e.message}", Toast.LENGTH_LONG).show()
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {


        val remoteConfig = Firebase.remoteConfig
        remoteConfig.setConfigSettingsAsync(
            remoteConfigSettings {
                minimumFetchIntervalInSeconds = 0  // Cambia tema al instante
            }
        )

        val temaLogin = remoteConfig.getString("tema_login")

        when (temaLogin) {
            "oscuro" -> setTheme(R.style.Theme_LoginOscuro)
            else -> setTheme(R.style.Theme_LoginClaro)
        }

        super.onCreate(savedInstanceState)


        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        remoteConfig.fetchAndActivate().addOnSuccessListener {
            val nuevoTema = remoteConfig.getString("tema_login")

            if (nuevoTema != temaLogin) {
                recreate() // Recarga la pantalla con el nuevo tema
            }
        }


        auth = FirebaseAuth.getInstance()

        // Si ya ha iniciado sesión  ir al menú principal
        val currentUser = auth.currentUser
        if (currentUser != null) {
            startActivity(Intent(this, PaginaPrincipalActivity::class.java))
            finish()
            return
        }


        binding.btnLoginFirebase.setOnClickListener { doLogin() }
        binding.btnLoginGoogle.setOnClickListener { loginWithGoogle() }

        binding.txtIrRegistro.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }

        binding.tvOlvidastePassword.setOnClickListener { resetPassword() }


        FirebaseMessaging.getInstance().token
            .addOnSuccessListener { token ->
                Log.d("FCM_TOKEN", "Token directo: $token")
            }
    }


    private fun doLogin() {
        val email = binding.editEmail.text.toString().trim()
        val password = binding.editPassword.text.toString().trim()

        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Rellena todos los campos", Toast.LENGTH_SHORT).show()
            return
        }

        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {

                    saveFcmToken()
                    startActivity(Intent(this, PaginaPrincipalActivity::class.java))
                    finish()

                } else {
                    Toast.makeText(this, "Error: ${task.exception?.message}", Toast.LENGTH_LONG).show()
                }
            }
    }


    private fun resetPassword() {
        val email = binding.editEmail.text.toString().trim()

        if (email.isEmpty()) {
            Toast.makeText(this, "Escribe tu email para recuperar la contraseña", Toast.LENGTH_SHORT).show()
            return
        }

        auth.sendPasswordResetEmail(email)
            .addOnSuccessListener {
                Toast.makeText(this, "Correo enviado", Toast.LENGTH_LONG).show()
            }
            .addOnFailureListener {
                Toast.makeText(this, "Error: ${it.message}", Toast.LENGTH_LONG).show()
            }
    }


    private fun saveFcmToken() {
        FirebaseMessaging.getInstance().token.addOnSuccessListener { token ->

            val user = auth.currentUser ?: return@addOnSuccessListener
            val email = user.email ?: return@addOnSuccessListener

            FirebaseFirestore.getInstance()
                .collection("usuarios")
                .document(email)
                .set(mapOf("token" to token))

            Log.d("FCM_TOKEN", "Token guardado: $token")
        }
    }


    private fun loginWithGoogle() {
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()

        val googleSignInClient = GoogleSignIn.getClient(this, gso)
        googleLauncher.launch(googleSignInClient.signInIntent)
    }
}
